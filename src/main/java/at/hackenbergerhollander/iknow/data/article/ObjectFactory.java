//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.12.13 um 09:23:53 PM CET 
//


package at.hackenbergerhollander.iknow.data.article;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the at.hackenbergerhollander.iknow package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: at.hackenbergerhollander.iknow
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArticleByIdRequest }
     * 
     */
    public GetArticleByIdRequest createGetArticleByIdRequest() {
        return new GetArticleByIdRequest();
    }

    /**
     * Create an instance of {@link GetArticleResponse }
     * 
     */
    public GetArticleResponse createGetArticleResponse() {
        return new GetArticleResponse();
    }

    /**
     * Create an instance of {@link at.hackenbergerhollander.iknow.Article }
     * 
     */
    public Article createArticle() {
        return new Article();
    }

}
