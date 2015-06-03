package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.libs.MimeTypes;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public class TravelMedia extends Content {

    
	@Lob
    @Required
    @MaxSize(10000)
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

    public void setFile(byte[] file) throws FileNotFoundException {
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
