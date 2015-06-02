package models;

import javax.persistence.*;

import play.data.validation.Required;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class CommentaireContent extends Commentaire {

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="CONTENT_CONTENTID")
    @Required
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
