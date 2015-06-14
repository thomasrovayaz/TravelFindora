package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class Travel extends GenericModel {
    @Id
    @GeneratedValue
    private int travelId;

    @ManyToOne
    @JoinColumn(name = "travels")
    private User author;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private Set<TravelUser> travellers; //permet le gestion de groupe (plus tard ...)

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private Set<TravelFindora> findoras;

    @OneToMany(mappedBy="travel", fetch = FetchType.EAGER)
    private Set<CommentaireTravel> commentaires;

    @OneToMany(mappedBy="travel", fetch = FetchType.EAGER)
    private Set<Content> contents;

    @OneToMany(mappedBy = "likingTravel", cascade = CascadeType.ALL)
    private Set<TravelLike> likers;

    public Set<TravelFindora> getFindoras() {
        return findoras;
    }

    public void setFindoras(Set<TravelFindora> findoras) {
        this.findoras = findoras;
    }

    public Set<CommentaireTravel> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<CommentaireTravel> commentaires) {
        this.commentaires = commentaires;
    }

    public Set<TravelUser> getTravellers() {
        return travellers;
    }

    public void setTravellers(Set<TravelUser> travellers) {
        this.travellers = travellers;
    }

    public Set<TravelLike> getLikers() {
        return likers;
    }

    public void setLikers(Set<TravelLike> likers) {
        this.likers = likers;
    }

    public int getTravelId() {
        return travelId;
    }

    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }
    public String toString() {
        return "idTravel"+this.getTravelId();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
