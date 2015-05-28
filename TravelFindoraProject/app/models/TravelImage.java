package models;

import javax.persistence.Entity;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelImage extends TravelMedia {
    private int width;
    private int heigth;

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
}
