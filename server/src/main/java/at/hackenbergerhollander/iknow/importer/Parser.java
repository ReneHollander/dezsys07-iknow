package at.hackenbergerhollander.iknow.importer;

import at.hackenbergerhollander.iknow.data.article.Article;
import at.hackenbergerhollander.iknow.util.CountingThreadFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Parser {

    private static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newInstance();
    private static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    private final InputStream inputStream;
    private Consumer<Article> articleConsumer;
    private boolean multithread;

    private ThreadPoolExecutor parsePool;

    public Parser(InputStream inputStream, int workerCount, boolean multithread, Consumer<Article> articleConsumer) throws XMLStreamException, TransformerConfigurationException {
        this.inputStream = inputStream;
        this.articleConsumer = articleConsumer;
        this.multithread = multithread;
        if (this.multithread) {

            this.parsePool = new ThreadPoolExecutor(workerCount, workerCount,
                    0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(workerCount, true),
                    new CountingThreadFactory("ParserThread %d"), new ThreadPoolExecutor.CallerRunsPolicy());
        }
    }

    public void start() throws XMLStreamException, TransformerException, IOException, ParseException {
        XMLStreamReader streamReader = XML_INPUT_FACTORY.createXMLStreamReader(this.inputStream);
        Transformer transformer = TRANSFORMER_FACTORY.newTransformer();
        if (streamReader.hasNext()) {
            streamReader.next();
        }
        while (true) {
            if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT && "page".equals(streamReader.getLocalName())) {
                StringWriter writer = new StringWriter();
                transformer.transform(new StAXSource(streamReader), new StreamResult(writer));
                Work work = new Work(writer.toString());
                if (multithread) {
                    parsePool.execute(work);
                } else {
                    work.run();
                }
            } else if (streamReader.hasNext()) {
                streamReader.next();
            } else {
                streamReader.close();
                break;
            }
        }
        if (multithread) {
            try {
                this.parsePool.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException ignored) {
            }
            this.parsePool.shutdown();
        }
    }

    private Article parse(String xml) throws XMLStreamException {

        XMLStreamReader streamReader = XML_INPUT_FACTORY.createXMLStreamReader(new StringReader(xml));

        StringBuilder fData = new StringBuilder();
        Article article = new Article();

        boolean revision = false;

        while (streamReader.hasNext()) {
            streamReader.next();

            if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                String qName = streamReader.getLocalName();
                if ("revision".equals(qName)) {
                    revision = true;
                } else if ("redirect".equals(qName)) {
                    article.setRedirect(streamReader.getAttributeValue(0));
                    article.setText(null);
                }
                fData.setLength(0);
                fData.trimToSize();
            } else if (streamReader.getEventType() == XMLStreamReader.END_ELEMENT) {
                String qName = streamReader.getLocalName();
                if ("ns".equals(qName)) {
                    int ns = Integer.parseInt(fData.toString());
                    if (ns != 0) {
                        return null;
                    }
                } else if (!revision && "id".equals(qName)) {
                    article.setId(Integer.parseInt(fData.toString()));
                } else if ("title".equals(qName)) {
                    article.setTitle(fData.toString());
                } else if ("text".equals(qName)) {
                    if (article.getRedirect() == null) {
                        article.setText(fData.toString());
                    }
                } else if ("revision".equals(qName)) {
                    revision = false;
                }
                fData.setLength(0);
                fData.trimToSize();
            } else if (streamReader.getEventType() == XMLStreamReader.CHARACTERS) {
                fData.append(streamReader.getText());
            }
        }
        return article;
    }

    private class Work implements Runnable {
        private String xml;

        public Work(String xml) {
            this.xml = xml;
        }

        @Override
        public void run() {
            try {
                Article article = parse(xml);
                if (article != null) {
                    articleConsumer.accept(article);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
