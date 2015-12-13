package at.hackenbergerhollander.iknow;

import at.hackenbergerhollander.iknow.data.article.Article;

import java.util.List;

public interface ArticleRepositoryCustom {

    public List<Article> search(String title);

}
