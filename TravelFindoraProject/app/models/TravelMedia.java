package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.libs.MimeTypes;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by thomas on 25/05/15.
 */
@Entity
public abstract class TravelMedia extends Content {


    @Lob
    @Required
    @MaxSize(10000)
    private String description;
    
    @Required
    public Blob file;
    
    @Required
    public String fileName;
    
    @Required
    public String contentType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public File getFile() {
    	if(file != null)
        return file.getFile();
    	else return null;
    	
      
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

