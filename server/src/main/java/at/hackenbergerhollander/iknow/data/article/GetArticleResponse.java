//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.12.13 um 09:23:53 PM CET 
//


package at.hackenbergerhollander.iknow.data.article;

import at.hackenbergerhollander.iknow.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="article" type="{http://hackenbergerhollander.at/iknow}article"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "article"
})
@XmlRootElement(name = "getArticleResponse")
public class GetArticleResponse {

    @XmlElement(required = true)
    protected Articles article;

    /**
     * Ruft den Wert der article-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link at.hackenbergerhollander.iknow.Article }
     *     
     */
    public Articles getArticle() {
        return article;
    }

    /**
     * Legt den Wert der article-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link at.hackenbergerhollander.iknow.Article }
     *     
     */
    public void setArticle(Articles value) {
        this.article = value;
    }

}
