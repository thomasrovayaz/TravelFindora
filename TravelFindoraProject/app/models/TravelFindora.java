package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelFindora extends GenericModel {
    @Id
    @GeneratedValue
    private int travelFindoraId;

    @ManyToOne
    @JoinColumn(name = "travel")
    private Travel travel;

    @ManyToOne
    @JoinColumn(name = "findora")
    private Findora findora;

    public int getTravelFindoraId() {
        return travelFindoraId;
    }
}
