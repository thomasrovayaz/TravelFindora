package controllers;

import play.mvc.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import models.User;

public class Application extends Controller {

    public static void signIn(){
        render();
    }

    public static void index() {
        render();
    }

    public static void login() {
        render();
    }

    public static void authenticate(String username, String password) {
        boolean auth;
        auth = (ControllerUser.connect(username, password));
        render();
    }

    public static void register(String firstname, String lastname, String email, String password) {
    User newUser = new User();
    newUser.setFirstname(firstname);
    newUser.setLastname(lastname);
    newUser.setEmail(email);
    newUser.setPassword(password);

    newUser.save();
    }

}