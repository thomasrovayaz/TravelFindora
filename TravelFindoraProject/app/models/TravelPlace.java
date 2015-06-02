package models;

import javax.persistence.Entity;

/**
 * Created by thomas on 27/05/15.
 */
@Entity
public class TravelPlace extends Content {
    private String description;
    private double latitude;
    private double longitude;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
