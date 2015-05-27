package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by thomas on 27/05/15.
 */
public class ControllerUser extends Controller {
    @Before
    static void checkAuth() {
        System.out.println(Security.isConnected());
        if (!Security.isConnected()) error(401,"Unauthorized");
    }

    public static boolean connect(String email, String password) {
        List<User> users = User.find("byEmailAndPassword", email, password).fetch();
        return users.size() == 1;
    }

    public static void index() {
        render();
    }
}
