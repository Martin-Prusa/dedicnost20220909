package cz.martin;

import cz.martin.interfaces.IGetLine;
import cz.martin.interfaces.IGetPost;
import cz.martin.models.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CourseRepository {
    public void saveCourse(Course course) {
        try {
            BufferedWriter courseBw = new BufferedWriter(new FileWriter("course.csv"));
            courseBw.write("name;description");
            courseBw.newLine();
            courseBw.write(course.getName()+";"+course.getDescription());
            courseBw.close();

            saveFile("notifications.csv", "createdAt;isVisible;title;content", course.getPosts().stream().filter(i -> i instanceof Notification).toList(), (notification) -> notification.getCreatedAt()+";"+notification.isVisible()+";"+notification.getTitle()+";"+((Notification) notification).getContent());
            saveFile("tasks.csv", "createdAt;isVisible;title;deadline", course.getPosts().stream().filter(i -> i instanceof Task).toList(), (task) -> task.getCreatedAt()+";"+task.isVisible()+";"+task.getTitle()+";"+((Task)task).getDeadline());
            saveFile("materials.csv", "createdAt;isVisible;title;url", course.getPosts().stream().filter(i -> i instanceof Material).toList(), (material) -> material.getCreatedAt()+";"+material.isVisible()+";"+material.getTitle()+";"+((Material) material).getUrl());

        } catch (IOException ignored) {}
    }

    public Course loadCourse() {
        try {
            BufferedReader courseBr = new BufferedReader(new FileReader("course.csv"));
            courseBr.readLine();
            String[] line = courseBr.readLine().split(";");
            Course c = new Course(line[0], line[1]);
            courseBr.close();

            List<Post> list = readFile("notifications.csv", (spl) -> new Notification(LocalDateTime.parse(spl[0]),Boolean.parseBoolean(spl[1]), spl[2], spl[3]));
            list.addAll(readFile("tasks.csv",(spl) -> new Task(LocalDateTime.parse(spl[0]),Boolean.parseBoolean(spl[1]), spl[2], LocalDateTime.parse(spl[3]))));
            list.addAll(readFile("materials.csv", (spl) -> new Material(LocalDateTime.parse(spl[0]),Boolean.parseBoolean(spl[1]), spl[2], spl[3])));

            for (Post post : list.stream().sorted(Comparator.comparing(Post::getCreatedAt)).toList()) {
                c.addPost(post);
            }
            return c;
        } catch (IOException ignored) {}
        return new Course("a", "a");
    }

    private void saveFile(String file, String header, List<Post> list, IGetLine line) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(header);
        for (Post post : list) {
            bw.newLine();
            bw.write(line.getValue(post));
        }
        bw.close();
    }

    private List<Post> readFile(String file, IGetPost getPost) throws IOException {
        List<Post> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String line = br.readLine();
        while (line != null) {
            String[] spl = line.split(";");
            Post post = getPost.getPost(spl);
            list.add(post);
            line = br.readLine();
        }
        br.close();
        return list;
    }


}
