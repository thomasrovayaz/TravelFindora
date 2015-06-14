package controllers;

import models.*;
import play.mvc.Controller;

import java.util.*;
public class Application extends Controller {

    public static void signIn(){
        render();
    }


    public static void test() {
        render();
    }

    public static void index() {
        User user = User.find("byEmail", Security.connected()).first();
        ArrayList<Content> contents = new ArrayList<Content>();
        /*Query q = em.createNativeQuery("select * " +
                "from User u " +
                "join UserFollowing uf on u=uf.follower " +
                "join User u2 on uf.followed=u2 " +
                "join Content c on c.USER_USERID=u2.userId " +
                "where u=" + user);*/
        for (UserFollowing userFollowing : user.getFolloweds()) {
            contents.addAll(userFollowing.getFollowed().getContents());
        }

        Collections.sort(contents);
        for (Content content : contents) {
            System.out.println(content);
        }
        render(user, contents);
    }

    public static void explore() {
        User user = User.find("byEmail", Security.connected()).first();
        List<Findora> findoras = Findora.findAll();
        render(user, findoras);
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

    session.put("email",email);
    Secure.login();
    Application.index();
    }

    public static void search(String name){
        User user = User.find("byEmail", Security.connected()).first();
        Findora f = Findora.find("byName", name).first();

        if(f!=null){
            Set<TravelFindora> tfs = f.getTravels();
            List <Travel> ts = new ArrayList<Travel>();
            for(TravelFindora t: tfs){
                ts.add(t.getTravel());
            }
            Set<Integer> userIds = new HashSet<Integer>();
            //   Set<Integer> likersIds = new HashSet<Integer>();
            List<Commentaire> com = new ArrayList<Commentaire>();

            for(Travel t: ts){
                for(TravelUser tu: t.getTravellers())
                    userIds.add(tu.getTraveller().getUserId());
                com.addAll(t.getCommentaires());

                //   	for(TravelLike tl: t.getLikers())
                //   		likersIds.add(tl.getLikerTravel().getUserId());
            }
            int nbUsers = userIds.size();
            //  int likes = likersIds.size();

            Collections.sort(com, new Comparator() {
                public int compare(Object A, Object B) {
                    return ((Commentaire)A).getDateCreation()
                            .compareTo(((Commentaire)B).getDateCreation());
                }
            });
            Set<Content> con = f.getContents();
            render(f, nbUsers, con, user);
        }
        else render(user);
    }

    public static void searchVal(String query){
        search(query);
    }

    public static void showTravel(int travelId) {
        User user = User.find("byEmail", Security.connected()).first();
        Travel travel = (Travel) Travel.find("byTravelId", travelId).fetch().get(0);
        boolean is = !TravelUser.find("byTravellerAndTravel", user, travel).fetch().isEmpty();
        System.out.println(is);
        renderTemplate("Application/travel.html", travel, user, is);
    }
}
