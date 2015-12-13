package at.hackenbergerhollander.iknow.restapi;

import at.hackenbergerhollander.iknow.data.article.Article;
import at.hackenbergerhollander.iknow.data.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/articles/{id}")
    public Article article(@PathVariable("id") int id) {
        return articleRepository.findById(id);
    }

    @RequestMapping("/articles")
    public List<Article> article(@RequestParam(value = "search") String searchString) {
        return articleRepository.search(searchString);
    }

}
