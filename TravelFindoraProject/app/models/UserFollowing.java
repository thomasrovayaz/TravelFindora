package models;

import javax.persistence.*;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class UserFollowing {
    @Id
    @GeneratedValue
    private int userFollowingId;

    @ManyToOne
    @JoinColumn(name = "follower")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed")
    private User followed;

    public int getUserFollowingId() {
        return userFollowingId;
    }
}
