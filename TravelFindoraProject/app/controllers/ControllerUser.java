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
        String email = session.get("email");

        List<User> users = User.find("byEmail", email).fetch();
        System.out.println("users.size() " + users.size());
        System.out.println("email " + email);
        if (users.size() != 1) error(401,"Unauthorized");
    }

    public static boolean connect(String email, String password) {

        List<User> users = User.find("byEmailAndPassword", email, password).fetch();
        System.out.println("users.size() " + users.size());
        System.out.println("email " + email);
        System.out.println("password " + password);
        if (users.size() != 1) return false;

        session.clear();
        session.put("email", email);

        return true;
    }

    public static void index() {
        render();
    }
}
