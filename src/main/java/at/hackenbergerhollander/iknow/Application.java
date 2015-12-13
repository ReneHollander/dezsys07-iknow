package at.hackenbergerhollander.iknow;

import at.hackenbergerhollander.iknow.data.article.Article;
import at.hackenbergerhollander.iknow.data.article.ArticleRepository;
import at.hackenbergerhollander.iknow.importer.Parser;
import at.hackenbergerhollander.iknow.util.ProgressReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;

    private static final boolean DO_IMPORT = false;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (DO_IMPORT) {
            articleRepository.deleteAll();
            InputStream inputStream = new GZIPInputStream(new BufferedInputStream(new FileInputStream(new File("/home/rene/Downloads/wikipedia_dump.xml.gz")), 1024 * 1024 * 32));
            Parser parser = new Parser(inputStream, 8, true, this::saveArticle);
            parser.start();
        }
    }

    ProgressReporter progressReporter = new ProgressReporter("Importing wikipedia articles ", 1000);

    private void saveArticle(Article article) {
        if (articleRepository != null && article != null) {
            articleRepository.save(article);
            progressReporter.update();
        }
    }
}
