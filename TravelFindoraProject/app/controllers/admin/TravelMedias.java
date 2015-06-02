package controllers.admin;


import models.TravelMedia;
import controllers.CRUD;
import play.*;
import play.exceptions.TemplateNotFoundException;
import play.i18n.Messages;
import play.mvc.*;
 
public class TravelMedias extends CRUD {    
	public static void create(TravelMedia object) {

	    /* Get the current type of controller and test it on non-empty */
	    ObjectType type = ObjectType.get(getControllerClass());
	    notFoundIfNull(type);

	    /* We perform validation of the generated crud module form fields */
	    validation.valid(object);
	    if (validation.hasErrors()) {
	        renderArgs.put("error", Messages.get("crud.hasErrors"));
	        try {
	            render(request.controller.replace(".", "/") + "/blank.html", type, object);
	        } catch (TemplateNotFoundException e) {
	            render("CRUD/blank.html", type, object);
	        }
	    }

	    /* Save our object into db */
	    object._save();

	    /* Show messages */
	    flash.success(Messages.get("crud.created", type.modelName));
	    if (params.get("_save") != null) {
	        redirect(request.controller + ".list");
	    }
	    if (params.get("_saveAndAddAnother") != null) {
	        redirect(request.controller + ".blank");
	    }

	}
}