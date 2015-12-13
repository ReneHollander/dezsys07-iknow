package at.hackenbergerhollander.iknow.soap;

import at.hackenbergerhollander.iknow.data.article.ArticleRepository;
import at.hackenbergerhollander.iknow.data.article.GetArticleByIdRequest;
import at.hackenbergerhollander.iknow.data.article.GetArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
    public GetArticleResponse getArticle(@RequestPayload GetArticleByIdRequest request) {
        GetArticleResponse response = new GetArticleResponse();
        response.setArticle(articleRepository.findById(request.getId()));

        return response;
    }
}
