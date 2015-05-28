package models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class TravelMedia extends Content {
    
	@Lob
    @Required
    @MaxSize(10000)
	private String description;
	
    @Required
    private String pathOnServer;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathOnServer() {
        return pathOnServer;
    }

    public void setPathOnServer(String pathOnServer) {
        this.pathOnServer = pathOnServer;
    }
    
}
