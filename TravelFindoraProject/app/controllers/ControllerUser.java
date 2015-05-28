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
        if (!Security.isConnected()) error(401,"Unauthorized");
    }

    public static boolean connect(String email, String password) {
        List<User> users = User.find("byEmailAndPassword", email, password).fetch();
        return users.size() == 1;
    }

    public static void index() {
        render();
    }

    /**gestion des contents */
    public static void formContent(String type) {
        int travelId = 4;
        int findoraId = 3;
        if (type.equals("story")) {
            renderTemplate("ControllerUser/formContentStory.html", travelId, findoraId);
        } else if (type.equals("place")) {
            renderTemplate("ControllerUser/formContentPlace.html", travelId, findoraId);
        }
    }

    public static void formEditContent(String type, int contentId) {
        int travelId = 4;
        int findoraId = 3;
        if (type.equals("story")) {
            TravelStory travelStory = (TravelStory) TravelStory.find("byContentId", contentId).fetch().get(0);
            renderTemplate("ControllerUser/formContentStory.html", travelId, findoraId, travelStory);
        } else if (type.equals("place")) {
            TravelPlace travelPlace = (TravelPlace) TravelPlace.find("byContentId", contentId).fetch().get(0);
            renderTemplate("ControllerUser/formContentPlace.html", travelId, findoraId, travelPlace);
        }
    }

    public static void addContentStory(int contentId, int travelId, int findoraId, String storyText) {
        Findora findora = (Findora) Findora.find("byFindoraId", findoraId).fetch().get(0);
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();

        if (!UserTools.checkTravelFindoraUser(findora, travel, user)) {
            error(401, "Not allowed to add content in this story. " + user.getEmail());
        }

        if (contentId != -1) {
            editContentStory(contentId, storyText);
            return;
        }

        TravelStory travelStory = new TravelStory();
        travelStory.setStory(storyText);
        travelStory.setDateCreation(new Date());

        travelStory.setTravel(travel);
        travelStory.setFindora(findora);
        travelStory.setUser(user);
        travelStory.save();
        travel.getContents().add(travelStory);
        travel.save();
        findora.getContents().add(travelStory);
        findora.save();
        user.getContents().add(travelStory);
        findora.save();
    }

    public static void editContentStory(int contentId, String storyText) {
        User user = User.find("byEmail", Security.connected()).first();
        TravelStory travelStory = (TravelStory) TravelStory.find("byContentId", contentId).fetch().get(0);
        if (travelStory.getUser().getUserId() == user.getUserId()) {
            travelStory.setStory(storyText);
            travelStory.setDateModification(new Date());
            travelStory.save();
        } else {
            error(401, "Not allowed to edit this content " + user.getEmail() + ".");
        }
    }

    public static void addContentPlace(int contentId, int travelId, int findoraId, String description, String latitude, String longitude) {
        Findora findora = (Findora) Findora.find("byFindoraId", findoraId).fetch().get(0);
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();

        if (!UserTools.checkTravelFindoraUser(findora, travel, user)) {
            error(401, "Not allowed to add content in this story. " + user.getEmail());
        }

        if (contentId != -1) {
            editContentPlace(contentId, description, latitude, longitude);
            return;
        }

        TravelPlace travelPlace = new TravelPlace();
        travelPlace.setDescription(description);
        travelPlace.setLatitude(Double.parseDouble(latitude));
        travelPlace.setLongitude(Double.parseDouble(longitude));
        travelPlace.setDateCreation(new Date());

        travelPlace.setTravel(travel);
        travelPlace.setFindora(findora);
        travelPlace.save();
        travel.getContents().add(travelPlace);
        travel.save();
        findora.getContents().add(travelPlace);
        findora.save();
        user.getContents().add(travelPlace);
        findora.save();
    }

    public static void editContentPlace(int contentId, String description, String latitude, String longitude) {
        User user = User.find("byEmail", Security.connected()).first();
        TravelPlace travelPlace = (TravelPlace) TravelPlace.find("byContentId", contentId).fetch().get(0);
        if (travelPlace.getUser().getUserId() == user.getUserId()) {
            travelPlace.setDescription(description);
            travelPlace.setLatitude(Double.parseDouble(latitude));
            travelPlace.setLongitude(Double.parseDouble(longitude));
            travelPlace.setDateModification(new Date());
            travelPlace.save();
        } else {
            error(401, "Not allowed to edit this content " + user.getEmail() + ".");
        }
    }

    /**gestion des commentaires*/
    public static void formEditComment(int commentId) {
        Commentaire commentaire = (Commentaire) Commentaire.find("byCommentaireId", commentId).fetch().get(0);
        renderTemplate("ControllerUser/formComment.html", commentaire);
    }

    public static void formComment() {
        int contentId = 6;
        render(contentId);
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
            commentaire.setDateModification(new Date());
            commentaire.save();
        } else {
            error(401, "Not allowed to content in this story " + user.getEmail() + ".");
        }
    }

    public static void addCommentContent(int contentId, int commentId, String comment) {
        if (commentId != -1) {
            editComment(commentId, comment);
            return;
        }
        Content content = (Content) Content.find("byContentId", contentId).fetch().get(0);
        CommentaireContent commentaireContent = new CommentaireContent();
        commentaireContent.setContent(content);
        commentaireContent.setText(comment);

        User user = User.find("byEmail", Security.connected()).first();
        commentaireContent.setUser(user);
        commentaireContent.setDateCreation(new Date());

        commentaireContent.save();
    }

    public static void addCommentTravel(int travelId, String comment) {
        Travel travel = (Travel) Content.find("byTravelId", travelId).fetch().get(0);
        CommentaireTravel commentaireTravel = new CommentaireTravel();
        commentaireTravel.setTravel(travel);
        commentaireTravel.setText(comment);

        User user = User.find("byEmail", Security.connected()).first();
        commentaireTravel.setUser(user);
        commentaireTravel.setDateCreation(new Date());

        commentaireTravel.save();
    }
}
