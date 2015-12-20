//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.12.20 um 07:55:49 PM CET 
//


package data.article;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="article" type="{http://hackenbergerhollander.at/iknow}articles"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     *     {@link Articles }
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
     *     {@link Articles }
     *     
     */
    public void setArticle(Articles value) {
        this.article = value;
    }

}
