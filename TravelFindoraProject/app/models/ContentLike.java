package models;

import javax.persistence.*;

/**
 * Created by thomas on 26/05/15.
 */
@Entity
public class ContentLike {
    @Id
    @GeneratedValue
    private int contentLikeId;

    @ManyToOne
    @JoinColumn(name = "likerContent")
    private User likerContent;

    @ManyToOne
    @JoinColumn(name = "likingContent")
    private Content likingContent;

    public User getLikerContent() {
        return likerContent;
    }

    public void setLikerContent(User likerContent) {
        this.likerContent = likerContent;
    }

    public Content getLikingContent() {
        return likingContent;
    }

    public void setLikingContent(Content likingContent) {
        this.likingContent = likingContent;
    }

    public int getContentLikeId() {
        return contentLikeId;
    }
}
