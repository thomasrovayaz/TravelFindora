package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

/**
 *  Created by thomas on 25/05/15.
 */
@Entity
public abstract class Commentaire extends GenericModel {
    @Id
    @GeneratedValue
    private int commentaireId;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="USER_USERID")
    @Required
    private User user;

    @Lob
    @Required
    @MaxSize(10000)
    private String text;
    
    @Required
    private Date date; //voir avec jpa si l'objet java.util.Date passe

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCommentaireId() {
        return commentaireId;
    }
}
