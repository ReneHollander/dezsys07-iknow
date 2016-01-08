package at.hackenbergerhollander.iknow.data.article;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Document
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "article", propOrder = {
        "id",
        "title",
        "redirect",
        "text"
})
public class Article {

    @Id
    @Indexed(unique = true)
    private int id;

    @TextIndexed
    @Indexed(unique = true)
    @XmlElement(required = true)
    private String title;

    @XmlElement(required = false)
    private String redirect;

    @XmlElement(required = false)
    private String text;

    public Article() {
        this.id = -1;
    }

    public Article(int id, String title, String redirect, String text) {
        this.id = id;
        this.title = title;
        this.redirect = redirect;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (redirect != null ? !redirect.equals(article.redirect) : article.redirect != null) return false;
        return text != null ? text.equals(article.text) : article.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (redirect != null ? redirect.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", redirect='" + redirect + '\'' +
                '}';
    }
}
