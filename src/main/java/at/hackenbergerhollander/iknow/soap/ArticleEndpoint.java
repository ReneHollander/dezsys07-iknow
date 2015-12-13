package at.hackenbergerhollander.iknow.soap;

import at.hackenbergerhollander.iknow.data.article.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;

@Endpoint
public class ArticleEndpoint {
    private static final String NAMESPACE_URI = "http://hackenbergerhollander.at/iknow";

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleEndpoint(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByIdRequest")
    @ResponsePayload
    public GetArticleResponse getArticleById(@RequestPayload GetArticleByIdRequest request) {
        GetArticleResponse response = new GetArticleResponse();
        Article a = articleRepository.findById(request.getId());
        ArrayList<Article> as = new ArrayList<>();
        as.add(a);
        Articles articles = new Articles();
        articles.setArticle(as);
        response.setArticle(articles);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByTitleRequest")
    @ResponsePayload
    public GetArticleResponse getArticleByTitle(@RequestPayload GetArticleByTitleRequest request) {
        GetArticleResponse response = new GetArticleResponse();
        Articles as = new Articles();
        as.setArticle(articleRepository.search(request.getTitle()));
        response.setArticle(as);

        return response;
    }
}
