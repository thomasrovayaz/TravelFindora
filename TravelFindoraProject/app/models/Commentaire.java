package models;

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
    private User user;

    private String text;
    private Date dateCreation;
    private Date dateModification;

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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getCommentaireId() {
        return commentaireId;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }
}
