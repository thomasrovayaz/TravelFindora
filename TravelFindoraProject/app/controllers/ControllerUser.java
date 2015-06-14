package controllers;

import controllers.admin.UserTools;
import models.*;
import play.Logger;
import play.data.Upload;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.results.Redirect;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.lang.Throwable;
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

    public static void profile() {
        User user = User.find("byEmail", Security.connected()).first();
        render(user);
    }

    public static void stories(String usermail, String findoraName) {
        User user = User.find("byEmail", Security.connected()).first();
        User user2 = User.find("byEmail", usermail).first();
        Findora findora = Findora.find("byName", findoraName).first();
        render(user, user2, findora);
    }

    /**gestion like */
    public static void likeContent(int contentId) {
        ContentLike contentLike = new ContentLike();
        Content content = (Content) Content.find("byContentId", contentId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();
        contentLike.setLikingContent(content);
        contentLike.setLikerContent(user);
        contentLike.save();
        user.getContentLikes().add(contentLike);
        user.save();
        content.getLikers().add(contentLike);
        content.save();
    }

    public static void likeTravel(int travelId) {
        TravelLike travelLike = new TravelLike();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();
        travelLike.setLikingTravel(travel);
        travelLike.setLikerTravel(user);
        travelLike.save();
        user.getTravelLikes().add(travelLike);
        user.save();
        travel.getLikers().add(travelLike);
        travel.save();
    }

    public static void dislikeContent(int contentId) {
        User user = User.find("byEmail", Security.connected()).first();
        Content content = (Content) Content.find("byContentId", contentId).fetch().get(0);
        ContentLike contentLike = (ContentLike) ContentLike.find("byLikingContentAndLikerContent", content, user).fetch().get(0);

        user.getContentLikes().remove(contentLike);
        user.save();
        content.getLikers().remove(contentLike);
        content.save();

        contentLike.delete();
    }
    public static void dislikeTravel(int travelId) {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        TravelLike travelLike = (TravelLike) TravelLike.find("byLikingTravelAndLikerTravel", travel, user).fetch().get(0);

        user.getTravelLikes().remove(travelLike);
        user.save();
        travel.getLikers().remove(travelLike);
        travel.save();

        travelLike.delete();
    }

    /**gestion des contents */
    public static void formContent(String travelId, String findoraId, String type) {
        User user = User.find("byEmail", Security.connected()).first();
        if (type.equals("story")) {
            renderTemplate("ControllerUser/formContentStory.html", travelId, findoraId, user);
        } else if (type.equals("place")) {
            renderTemplate("ControllerUser/formContentPlace.html", travelId, findoraId, user);
        } else if (type.equals("image")) {
            renderTemplate("ControllerUser/formContentImage.html", travelId, findoraId, user);
        } else if (type.equals("movie")) {
            renderTemplate("ControllerUser/formContentMovie.html", travelId, findoraId, user);
        }
    }

    public static void formEditContent(String travelId, String findoraId, String type, int contentId) {
        User user = User.find("byEmail", Security.connected()).first();
        if (type.equals("story")) {
            TravelStory travelStory = (TravelStory) TravelStory.find("byContentId", contentId).fetch().get(0);
            renderTemplate("ControllerUser/formContentStory.html", travelId, findoraId, travelStory, user);
        } else if (type.equals("place")) {
            TravelPlace travelPlace = (TravelPlace) TravelPlace.find("byContentId", contentId).fetch().get(0);
            renderTemplate("ControllerUser/formContentPlace.html", travelId, findoraId, travelPlace, user);
        } else if (type.equals("image")) {
            TravelImage travelImage = (TravelImage) TravelImage.find("byContentId", contentId).fetch().get(0);
            renderTemplate("ControllerUser/formContentImage.html", travelId, findoraId, travelImage, user);
        } else if (type.equals("movie")) {
            TravelMovie travelMovie = (TravelMovie) TravelMovie.find("byContentId", contentId).fetch().get(0);
            renderTemplate("ControllerUser/formContentMovie.html", travelId, findoraId, travelMovie, user);
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

    public static void addContentImage(int contentId, int travelId, int findoraId, String description, Upload data) throws FileNotFoundException {
        Findora findora = (Findora) Findora.find("byFindoraId", findoraId).fetch().get(0);
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();

        if (!UserTools.checkTravelFindoraUser(findora, travel, user)) {
            error(401, "Not allowed to add content in this story. " + user.getEmail());
        }

        if(!(data.getContentType().equals("image/png") || data.getContentType().equals("image/jpeg")
        		|| data.getContentType().equals("image/gif") || data.getContentType().equals("image/x-png") 
        		|| data.getContentType().equals("image/pjpeg") )){
            error(401, "Not allowed to add content of this type in the story. ");
        }
        if (contentId != -1) {
            editContentImage(contentId, description);
            return;
        }

        TravelImage travelImage = new TravelImage();
        travelImage.setDescription(description);
        travelImage.setDateCreation(new Date());
        //todo check content type
        Logger.info(data.getContentType());
        Logger.info(data.getFieldName());
        Logger.info(data.getFileName());
        travelImage.setContentType(data.getContentType());
        travelImage.setFileName(data.getFileName());
        travelImage.setFile(data.asFile());

        travelImage.setTravel(travel);
        travelImage.setFindora(findora);
        travelImage.save();
        travel.getContents().add(travelImage);
        travel.save();
        findora.getContents().add(travelImage);
        findora.save();
        user.getContents().add(travelImage);
        findora.save();
    }

    public static void editContentImage(int contentId, String description) {
        User user = User.find("byEmail", Security.connected()).first();
        TravelImage travelImage = (TravelImage) TravelImage.find("byContentId", contentId).fetch().get(0);
        if (travelImage.getUser().getUserId() == user.getUserId()) {
            travelImage.setDescription(description);
            travelImage.setDateModification(new Date());
            travelImage.save();
        } else {
            error(401, "Not allowed to edit this content " + user.getEmail() + ".");
        }
    }

    public static void addContentMovie(int contentId, int travelId, int findoraId, String description, Upload data) throws FileNotFoundException {
        Findora findora = (Findora) Findora.find("byFindoraId", findoraId).fetch().get(0);
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        User user = User.find("byEmail", Security.connected()).first();

        if (!UserTools.checkTravelFindoraUser(findora, travel, user)) {
            error(401, "Not allowed to add content in this story. " + user.getEmail());
        }

        if(!data.getContentType().equals("video/mp4")){
            error(401, "Not allowed to add content of this type in the story. ");
        }
        
        if (contentId != -1) {
            editContentMovie(contentId, description);
            return;
        }
        
        
        TravelMovie travelMovie = new TravelMovie();
        travelMovie.setDescription(description);
        travelMovie.setDateCreation(new Date());
        //todo check content type
        Logger.info(data.getContentType());
        Logger.info(data.getFieldName());
        Logger.info(data.getFileName());
        travelMovie.setContentType(data.getContentType());
        travelMovie.setFileName(data.getFileName());
        travelMovie.setFile(data.asFile());

        travelMovie.setTravel(travel);
        travelMovie.setFindora(findora);
        travelMovie.save();
        travel.getContents().add(travelMovie);
        travel.save();
        findora.getContents().add(travelMovie);
        findora.save();
        user.getContents().add(travelMovie);
        findora.save();
    }

    public static void editContentMovie(int contentId, String description) {
        User user = User.find("byEmail", Security.connected()).first();
        TravelMovie travelMovie = (TravelMovie) TravelMovie.find("byContentId", contentId).fetch().get(0);
        if (travelMovie.getUser().getUserId() == user.getUserId()) {
            travelMovie.setDescription(description);
            travelMovie.setDateModification(new Date());
            travelMovie.save();
        } else {
            error(401, "Not allowed to edit this content " + user.getEmail() + ".");
        }
    }

    /**gestion des commentaires*/
    public static void formEditComment(int commentId) {
        User user = User.find("byEmail", Security.connected()).first();
        Commentaire commentaire = (Commentaire) Commentaire.find("byCommentaireId", commentId).fetch().get(0);
        renderTemplate("ControllerUser/formComment.html", commentaire, user);
    }

    public static void formComment(int contentId) {
        User user = User.find("byEmail", Security.connected()).first();
        //todo gestion travelcomment
        render(contentId, user);
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

    /**gestion des travels*/
    /*public static void formEditTravel(int travelId) {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        renderTemplate("ControllerUser/formTravel.html", travel, user);
    }*/

    public static void formTravel() {
        User user = User.find("byEmail", Security.connected()).first();
        render(user);
    }

    public static void deleteTravel(int travelId) {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        if (travel.getAuthor().getUserId() == user.getUserId()) {
            travel.delete();

            redirect("/board");
        } else {
            error(401, "Not allowed to delete this story " + user.getEmail() + ".");
        }
    }

    public static void addFindoraTravel(int travelId, String findoraName) {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        if (travel.getAuthor().getUserId() == user.getUserId()) {
            List<Findora> findoras = Findora.find("byName", findoraName).fetch();
            Findora findora;
            if (findoras.isEmpty()) {
                findora = new Findora();
                findora.setName(findoraName);
                findora.save();
            } else {
                findora = findoras.get(0);
            }

            TravelFindora travelFindora = new TravelFindora();
            travelFindora.setFindora(findora);
            travelFindora.setTravel(travel);
            travelFindora.save();

            travel.getFindoras().add(travelFindora);
            travel.save();

            redirect("/travel/" + travel.getTravelId());
        } else {
            error(401, "Not allowed to edit this story " + user.getEmail() + ".");
        }
    }

    public static void deleteFindoraTravel(int travelId, int findoraId) {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        if (travel.getAuthor().getUserId() == user.getUserId()) {
            Findora findora = (Findora) Findora.find("byFindoraId", findoraId).fetch().get(0);
            TravelFindora travelFindora = (TravelFindora) TravelFindora.find("byTravelAndFindora", travel, findora).fetch().get(0);

            travel.getFindoras().remove(travelFindora);
            travel.save();

            findora.getTravels().remove(travelFindora);
            findora.save();

            travelFindora.delete();
        } else {
            error(401, "Not allowed to edit this story " + user.getEmail() + ".");
        }
        redirect("/board");
    }

    public static void addTravel() {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = new Travel();
        travel.setAuthor(user);

        TravelUser travelUser = new TravelUser();
        travelUser.setTravel(travel);
        travelUser.setTraveller(user);
        travel.save();

        travelUser.save();

        redirect("/travel/" + travel.getTravelId());

    public static void deleteAccount() {render();}

    public static void deleteMember() throws Throwable{
        User user = User.find("byEmail", Security.connected()).first();
        user.delete();
        session.clear();
        Application.index();
    }
}
