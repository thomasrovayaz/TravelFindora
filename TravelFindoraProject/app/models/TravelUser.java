package models;

import javax.persistence.*;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelUser {
    @Id
    @GeneratedValue
    private int travelUserId;

    @ManyToOne
    @JoinColumn(name = "traveller")
    private User traveller;

    @ManyToOne
    @JoinColumn(name = "travel")
    private Travel travel;

    public User getTraveller() {
        return traveller;
    }

    public void setTraveller(User traveller) {
        this.traveller = traveller;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public int getTravelUserId() {
        return travelUserId;
    }
}
