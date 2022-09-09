package cz.martin.models;

import java.time.LocalDateTime;

public abstract class Post {
    private LocalDateTime createdAt;
    private boolean isVisible;
    private String title;

    public Post(boolean isVisible, String title) {
        this.createdAt = LocalDateTime.now();
        this.isVisible = isVisible;
        this.title = title;
    }

    public Post(LocalDateTime createdAt, boolean isVisible, String title) {
        this.createdAt = createdAt;
        this.isVisible = isVisible;
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getTitle() {
        return title;
    }
}
