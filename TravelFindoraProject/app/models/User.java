package models;

import play.data.validation.*;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class User extends GenericModel {
    @Id
    @GeneratedValue
    private int userId;

    @Required
    private String lastname;
    
    @Required
    private String firstname;

    private Gender gender;
    
    @Required
    @Email
    private String email;
    
    @Required
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Travel> travels;
    
    public boolean isAdmin;

    public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	//gestion des voyages
    @OneToMany(mappedBy = "traveller", cascade = CascadeType.ALL)
    private Set<TravelUser> travelsUser;

    //gestion des followers
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private Set<UserFollowing> followeds;
    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    private Set<UserFollowing> followers ;

    //gestion des likes
    @OneToMany(mappedBy = "likerTravel", cascade = CascadeType.ALL)
    private Set<TravelLike> travelLikes;
    @OneToMany(mappedBy = "likerContent", cascade = CascadeType.ALL)
    private Set<ContentLike> contentLikes;

    //gestion des commentaires
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<Commentaire> commentaires;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<Content> contents;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserFollowing> getFolloweds() {
        return followeds;
    }

    public void setFolloweds(Set<UserFollowing> followeds) {
        this.followeds = followeds;
    }

    public Set<UserFollowing> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserFollowing> followers) {
        this.followers = followers;
    }

    public Set<TravelUser> getTravelsUser() {
        return travelsUser;
    }

    public void setTravelsUser(Set<TravelUser> travelsUser) {
        this.travelsUser = travelsUser;
    }

    public Set<TravelLike> getTravelLikes() {
        return travelLikes;
    }

    public void setTravelLikes(Set<TravelLike> travelLikes) {
        this.travelLikes = travelLikes;
    }

    public Set<ContentLike> getContentLikes() {
        return contentLikes;
    }

    public void setContentLikes(Set<ContentLike> contentLikes) {
        this.contentLikes = contentLikes;
    }

    public int getUserId() {
        return userId;
    }

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
    
    public String toString() {
        return this.getEmail();
    }

    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setTravels(Set<Travel> travels) {
        this.travels = travels;
    }

    public Set<Travel> getTravels() {
        return travels;
    }
}
