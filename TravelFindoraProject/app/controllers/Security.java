package controllers;

import models.User;

public class Security extends Secure.Security {
	
   static void storeCurrentUrl() {
      System.out.println("storeCurrentUrl()");
      flash.put("url", "GET".equals(request.method) ? request.url : "/");
   }

    static boolean authenticate(String username, String password) {
        return ControllerUser.connect(username, password);
    }

}
