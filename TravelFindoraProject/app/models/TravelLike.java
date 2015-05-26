package models;

import javax.persistence.*;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelLike {
    @Id
    @GeneratedValue
    private int travelLikeId;

    @ManyToOne
    @JoinColumn(name = "likerTravel")
    private User likerTravel;

    @ManyToOne
    @JoinColumn(name = "likingTravel")
    private Travel likingTravel;

    public User getLikerTravel() {
        return likerTravel;
    }

    public void setLikerTravel(User likerTravel) {
        this.likerTravel = likerTravel;
    }

    public Travel getLikingTravel() {
        return likingTravel;
    }

    public void setLikingTravel(Travel likingTravel) {
        this.likingTravel = likingTravel;
    }

    public int getTravelLikeId() {
        return travelLikeId;
    }
}
