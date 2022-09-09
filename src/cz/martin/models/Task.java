package cz.martin.models;

import java.time.LocalDateTime;

public class Task extends Post{
    private LocalDateTime deadline;

    public Task(boolean isVisible, String title, LocalDateTime deadline) {
        super(isVisible, title);
        this.deadline = deadline;
    }

    public Task(LocalDateTime createdAt, boolean isVisible, String title, LocalDateTime deadline) {
        super(createdAt, isVisible, title);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
