package at.hackenbergerhollander.iknow.data.article;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String>, ArticleRepositoryCustom {

    public Article findById(int id);

    public Article findByTitle(String title);

}
