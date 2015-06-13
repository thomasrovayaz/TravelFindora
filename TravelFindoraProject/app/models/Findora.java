package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

import java.util.Set;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class Findora extends GenericModel {
    @Id
    @GeneratedValue
    private int findoraId;
    
    @Required
    private String name;

    private double latitude;
    private double longitude;

    private double nbHab;
    private double size;

    @Lob
    @Required
    @MaxSize(10000)
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
   
    public String toString() {
        return this.getName();
    }

    public double getNbHab() {
        return nbHab;
    }

    public void setNbHab(double nbHab) {
        this.nbHab = nbHab;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
