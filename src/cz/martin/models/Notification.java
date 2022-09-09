package cz.martin.models;

import java.time.LocalDateTime;

public class Notification extends Post{
    private String content;

    public Notification(boolean isVisible, String title, String content) {
        super(isVisible, title);
        this.content = content;
    }

    public Notification(LocalDateTime createdAt, boolean isVisible, String title, String content) {
        super(createdAt, isVisible, title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
