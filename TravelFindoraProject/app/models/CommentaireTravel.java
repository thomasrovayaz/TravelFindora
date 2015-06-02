package models;

import javax.persistence.*;

import play.data.validation.Required;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class CommentaireTravel extends Commentaire {

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="TRAVEL_TRAVELID")
    @Required
    private Travel travel;

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
