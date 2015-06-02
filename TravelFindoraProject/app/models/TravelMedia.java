package models;

import javax.persistence.Entity;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class TravelMedia extends Content {
    private String description;
    public byte[] file;
    public String fileName;
    public String contentType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
