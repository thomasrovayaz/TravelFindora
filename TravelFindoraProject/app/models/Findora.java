package models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class Findora {
    @Id
    @GeneratedValue
    private int findoraId;
    private String name;
    private long latitude;
    private long longitude;
    private String description;

    @OneToMany(mappedBy = "findora", cascade = CascadeType.ALL)
    private Set<TravelFindora> travels;

    @OneToMany(mappedBy="findora", fetch = FetchType.EAGER)
    private Set<Content> contents; //a vérifier association ternaire, je ne suis pas sûr que ça passe dans jpa comme ça

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TravelFindora> getTravels() {
        return travels;
    }

    public void setTravels(Set<TravelFindora> travels) {
        this.travels = travels;
    }

    public int getFindoraId() {
        return findoraId;
    }

    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }
}
