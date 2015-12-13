package at.hackenbergerhollander.iknow.data.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Article> search(String title) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(title);
        Query query = TextQuery.queryText(criteria).sortByScore().with(new PageRequest(0, 10));
        return mongoTemplate.find(query, Article.class);
    }
}
