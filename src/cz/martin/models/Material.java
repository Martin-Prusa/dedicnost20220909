package cz.martin.models;

import java.time.LocalDateTime;

public class Material extends Post {
    private String url;

    public Material(boolean isVisible, String title, String url) {
        super(isVisible, title);
        this.url = url;
    }

    public Material(LocalDateTime createdAt, boolean isVisible, String title, String url) {
        super(createdAt, isVisible, title);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
