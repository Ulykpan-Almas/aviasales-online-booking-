package kz.javaee.db;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

public class Comments {

    private Long id ;
    private Users user;
    private Blogs blog;
    private String comment;
    private Timestamp postDate;

    public Comments(Long id, Users user, Blogs blog, String comment, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.blog = blog;
        this.comment = comment;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Blogs getBlog() {
        return blog;
    }

    public void setBlog(Blogs blog) {
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
