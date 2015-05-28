package models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 * Created by thomas on 27/05/15.
 */
@Entity
public class TravelPlace extends Content {
	
	@Lob
    @Required
    @MaxSize(10000)
    private String description;

    @Required
    private double latitude;
	
    @Required
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
