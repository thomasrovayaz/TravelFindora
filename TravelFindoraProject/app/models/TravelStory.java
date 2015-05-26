package models;

import javax.persistence.Entity;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelStory extends Content {
    private String story;

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
