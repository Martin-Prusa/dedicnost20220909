package cz.martin.models;

import java.util.ArrayList;

public class Course {
    private String name;
    private String description;
    private ArrayList<Post> posts = new ArrayList<Post>();

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public boolean removePost(Post post) {
        return this.posts.remove(post);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
