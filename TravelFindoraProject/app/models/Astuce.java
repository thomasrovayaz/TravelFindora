package models;

import javax.persistence.Entity;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class Astuce extends Content {
    private String text;
    private long latitude;
    private long longitude;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
