package at.hackenbergerhollander.iknow.rest;

import at.hackenbergerhollander.iknow.data.article.Article;
import at.hackenbergerhollander.iknow.data.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public Article addArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable("id") int id) {
        return articleRepository.findById(id);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.PUT)
    public Article putArticle(@RequestBody Article article) {
        articleRepository.delete(article.getId() + "");
        return articleRepository.save(article);
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    public Article deleteArticle(@PathVariable("id") int id) {
        Article article = articleRepository.findById(id);
        articleRepository.delete(article);
        return article;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<Article> searchArticle(@RequestParam(value = "search") String searchString) {
        return articleRepository.search(searchString);
    }

}
