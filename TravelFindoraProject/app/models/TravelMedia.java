package models;

import javax.persistence.Entity;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class TravelMedia extends Content {
    private String description;
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
