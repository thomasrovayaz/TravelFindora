package models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class TravelStory extends Content {
	
	@Lob
    @Required
    @MaxSize(10000)
    private String story;

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
