package controllers;

import models.*;

/**
 * Created by thomas on 28/05/15.
 */
public class UserTools {
    public static boolean checkTravelFindoraUser(Findora findora, Travel travel, User user) {
        return !(TravelUser.find("byTravelAndTraveller", travel, user).fetch().isEmpty()
                || TravelFindora.find("byTravelAndFindora", travel, findora).fetch().isEmpty());

    }
}
