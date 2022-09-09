package cz.martin;

import cz.martin.models.Course;
import cz.martin.models.Post;


public class CourseService {
    private CourseRepository courseRepository = new CourseRepository();
    private Course course;

    public CourseService() {
        course = courseRepository.loadCourse();
    }

    public void addPost(Post post) {
        this.course.addPost(post);
        this.courseRepository.saveCourse(this.course);
    }

    public boolean removePost(Post post) {
        boolean status =  this.course.removePost(post);
        this.courseRepository.saveCourse(this.course);
        return status;
    }

    public Course getCourse() {
        return course;
    }
}
