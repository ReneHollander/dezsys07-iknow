package at.hackenbergerhollander.iknow.data.article;

import java.util.List;

public interface ArticleRepositoryCustom {

    public List<Article> search(String searchString);

}
