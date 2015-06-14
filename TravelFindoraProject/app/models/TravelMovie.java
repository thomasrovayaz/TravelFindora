package models;

import javax.persistence.Entity;

import play.data.validation.Required;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelMovie extends TravelMedia {
	
	@Required
    private int width;
	
	@Required
    private int heigth;
	
	@Required
    private long duration;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
