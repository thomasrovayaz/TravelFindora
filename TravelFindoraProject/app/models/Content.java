package models;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public abstract class Content extends GenericModel implements Comparable<Content> {
    @Id
    @GeneratedValue
    private int contentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="TRAVEL_TRAVELID")
    @Required
    private Travel travel; //a debattre User ou Travel, est ce qu'un utilisateur qui n'a pas forcément voyager peut poster sur ce findora ? Cela complexifie la gestion des travels

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="FINDORA_FINDORAID")
    @Required
    private Findora findora;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="USER_USERID")
    private User user;

    @OneToMany(mappedBy="content", fetch = FetchType.EAGER)
    private Set<CommentaireContent> commentaires;

    @OneToMany(mappedBy = "likingContent", cascade = CascadeType.ALL)
    private Set<ContentLike> likers;

    private Date dateCreation;
    private Date dateModification;

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Findora getFindora() {
        return findora;
    }

    public void setFindora(Findora findora) {
        this.findora = findora;
    }

    public Set<CommentaireContent> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<CommentaireContent> commentaires) {
        this.commentaires = commentaires;
    }

    public Set<ContentLike> getLikers() {
        return likers;
    }

    public void setLikers(Set<ContentLike> likers) {
        this.likers = likers;
    }

    public int getContentId() {
        return contentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    @Override
    public int compareTo(Content o) {
        if (getDateCreation() == null || o.getDateCreation() == null)
            return 0;
        return o.getDateCreation().compareTo(getDateCreation());
    }
}
