import org.junit.*;

import controllers.ControllerUser;
import controllers.Secure;

import java.util.*;

import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void createAndRetrieveUser() {
        // Create a new user and save it
        User A = new User();
        A.setEmail("bob@gmail.com");
        A.setPassword("secret");
        A.setFirstname("Bob");
        A.setLastname("Doe");
        A.save();
        
        
        // Retrieve the user with e-mail address bob@gmail.com
        User bob = User.find("byEmail", "bob@gmail.com").first();
        
        // Test 
        assertNotNull(bob);
        assertEquals("Bob", bob.getFirstname());
        assertEquals("Doe", bob.getLastname());
        assertEquals("secret", bob.getPassword());
        
    }
    
    @Test
    public void travelLikeSetTest() throws Throwable {
    	
    	//on récupère l'user 
        User bob = User.find("byEmail", "bob@gmail.com").first();
        
        //on crée un travel dont l'auteur est cet utilisateur
    	Travel t = new Travel();
    	t.setAuthor(bob);
    	t.save();
    	
    	//lui-même like son travel
    	TravelLike tl = new TravelLike();
    	tl.setLikerTravel(bob);
    	tl.setLikingTravel(t);
    	tl.save();
    	
    	Set<TravelLike> likers = new HashSet<TravelLike>();
    	likers.add(tl);
    	t.setLikers(likers);
    	t.save();
    	
    	int i = tl.getTravelLikeId();
    	
    	//on récupère le travel par id
    	TravelLike tll = (TravelLike) TravelLike.find("byTravelLikeId", i).fetch().get(0);
       
        
        //vérifier que like était fait par bob
        assertEquals(tll.getLikerTravel().getUserId(),bob.getUserId());
        //vérifier que il s'agit bien de travel liké
        assertEquals(tll.getLikingTravel().getTravelId(), t.getTravelId());

    }
    
    //idem pour les autres classes de modèle
    
}
