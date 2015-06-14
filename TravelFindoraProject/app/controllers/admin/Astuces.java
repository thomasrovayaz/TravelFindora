package controllers.admin;

import models.User;
import controllers.CRUD;
import controllers.Security;
import play.*;
import play.mvc.*;

public class Astuces extends CRUD { 
	@Before
    static void checkAuth() {
        if (!Security.isConnected()) error(401,"Unauthorized");
        User user = User.find("byEmail", Security.connected()).first();
		if (!user.isAdmin()) error(401,"Unauthorized");

    }
}