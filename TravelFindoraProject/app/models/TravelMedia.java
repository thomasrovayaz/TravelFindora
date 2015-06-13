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

    public Blob file;
    public String fileName;
    public String contentType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file.getFile();
    }

    public void setFile(File file) throws FileNotFoundException {
        this.file = new Blob();
        this.file.set (new FileInputStream(file), MimeTypes.getContentType(file.getName()));
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

