package models;

import javax.persistence.*;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelFindora {
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
