package at.hackenbergerhollander.iknow.importer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;
import info.bliki.wiki.dump.WikiXMLParser;
import info.bliki.wiki.filter.ITextConverter;
import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Importer implements IArticleFilter {

    private static final WikiModel MODEL = new WikiModel("${image}", "${title}");
    private static final ITextConverter CONVERTER = new PlainTextConverter();

    private File inputFile;
    private MongoCollection<BsonDocument> articles;

    public Importer(File inputFile) {
        this.inputFile = inputFile;
    }

    public void run() throws IOException, SAXException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("iknow");
        db.createCollection("articles");
        this.articles = db.getCollection("articles", BsonDocument.class);
        this.articles.createIndex(new Document("_id", 1), new IndexOptions().unique(true));
        this.articles.createIndex(new Document("title", 1), new IndexOptions().unique(true));

        WikiXMLParser wxp = new WikiXMLParser(inputFile, this);
        wxp.parse();
    }

    public static void main(String[] args) throws IOException, SAXException {
        new Importer(new File("/media/data/enwiki-20151102-pages-articles.xml.bz2")).run();
    }

    @Override
    public void process(WikiArticle article, Siteinfo siteinfo) throws SAXException {
        if (article.isMain()) {
            System.out.println("Inserting " + article.getTitle() + " with id " + article.getId() + " into db");
            BsonDocument document = new BsonDocument();
            document.put("_id", new BsonInt32(Integer.parseInt(article.getId())));
            document.put("title", new BsonString(article.getTitle()));
            document.put("text", new BsonString(MODEL.render(CONVERTER, article.getText())));
            this.articles.insertOne(document);
        }
    }
}
