package controllers;

import models.Findora;
import models.User;
import play.mvc.Controller;
import play.api.mvc.*;

import java.util.List;

public class Application extends Controller {

    public static void signIn(){
        render();
    }

    public static void index() {
        List<Findora> findoras = Findora.findAll();
        render(findoras);
    }

    public static void register(String firstname, String lastname, String email, String password) {
        User newUser = new User();
        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(password);

        newUser.save();

        session.put("email",email);
        Application.index();
    }

}
