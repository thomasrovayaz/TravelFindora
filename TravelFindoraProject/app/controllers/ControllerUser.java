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

    public static void formEditComment(int commentId) {
        Commentaire commentaire = (Commentaire) Commentaire.find("byCommentaireId", commentId).fetch().get(0);
        render(commentaire);
    }

    public static void formComment() {
        int contentId = 2;
        render(contentId);
    }

    public static void formContent(String type) {
        int travelId = 4;
        int findoraId = 3;
        if (type.equals("story")) {
            renderTemplate("ControllerUser/formContentStory.html", travelId, findoraId);
        }
    }

    public static void addContentStory(int travelId, int findoraId, String storyText) {
        Findora findora = (Findora) Findora.find("byFindoraId", findoraId).fetch().get(0);
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();
        if (!TravelUser.find("byTravelAndTraveller", travel, user).fetch().isEmpty()) {
            TravelStory travelStory = new TravelStory();
            travelStory.setStory(storyText);
            travelStory.setTravel(travel);
            travelStory.setFindora(findora);
            travelStory.save();
            travel.getContents().add(travelStory);
            travel.save();
            findora.getContents().add(travelStory);
            findora.save();
        } else {
            error(401, "Not allowed to content in this story " + user.getEmail() + ".");
        }
    }

    public static void deleteComment(int commentId) {
        User user = User.find("byEmail", Security.connected()).first();
        Commentaire commentaire = (Commentaire) Commentaire.find("byCommentaireId", commentId).fetch().get(0);
        if (commentaire.getUser().getUserId() == user.getUserId()) {
            commentaire.delete();
        } else {
            error(401, "Not allowed to content in this story " + user.getEmail() + ".");
        }
    }

    public static void editComment(int commentId, String comment) {
        User user = User.find("byEmail", Security.connected()).first();
        Commentaire commentaire = (Commentaire) Commentaire.find("byCommentaireId", commentId).fetch().get(0);
        if (commentaire.getUser().getUserId() == user.getUserId()) {
            commentaire.setText(comment);
            commentaire.save();
        } else {
            error(401, "Not allowed to content in this story " + user.getEmail() + ".");
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
