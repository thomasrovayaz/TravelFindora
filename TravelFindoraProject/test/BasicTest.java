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
    public void travelLikeTest() throws Throwable {
    	
        User bob = User.find("byEmail", "bob@gmail.com").first();

    	Travel t = new Travel();
    	t.setAuthor(bob);
    	Set<User> likers = new HashSet<User>();
    	likers.add(bob);
    	
    	t.setLikers(likers);
    	t.save();
    	int i = t.getTravelId();
    	
        Travel ttev = (Travel) Travel.find("byTravelId", i).fetch().get(0);
        assertEquals(ttev.getLikers()));
    	
        ControllerUser.likeTravel(i);
    	/*assertEquals(1, t.getLikers().size());*/
    	
    }
    
}
