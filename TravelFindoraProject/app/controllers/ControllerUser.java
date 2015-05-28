package controllers;

import models.*;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.Date;
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

    public static void formComment() {
        render();
    }

    public static void deleteComment(int commentId) {
        User user = User.find("byEmail", Security.connected()).first();
        Commentaire commentaire = (Commentaire) Commentaire.find("byCommentaireId", commentId).fetch().get(0);
        if (commentaire.getUser().getUserId() == user.getUserId()) {
            commentaire.delete();
        } else {
            error(401, "Not allowed to delete this comment");
        }
    }

    public static void addCommentContent(int contentId, String comment) {
        Content content = (Content) Content.find("byContentId", contentId).fetch().get(0);
        CommentaireContent commentaireContent = new CommentaireContent();
        commentaireContent.setContent(content);
        commentaireContent.setText(comment);

        User user = User.find("byEmail", Security.connected()).first();
        commentaireContent.setUser(user);
        commentaireContent.setDate(new Date());

        commentaireContent.save();
    }

    public static void addCommentTravel(int travelId, String comment) {
        Travel travel = (Travel) Content.find("byTravelId", travelId).fetch().get(0);
        CommentaireTravel commentaireTravel = new CommentaireTravel();
        commentaireTravel.setTravel(travel);
        commentaireTravel.setText(comment);

        User user = User.find("byEmail", Security.connected()).first();
        commentaireTravel.setUser(user);
        commentaireTravel.setDate(new Date());

        commentaireTravel.save();
    }
}
