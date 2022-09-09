package cz.martin;

import cz.martin.models.Material;
import cz.martin.models.Notification;
import cz.martin.models.Post;
import cz.martin.models.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CourseCLI {
    private Scanner sc = new Scanner(System.in);

    private CourseService service = new CourseService();

    public void menu() {
        while (true) {
            System.out.println("-------Menu---------");
            System.out.println("1) Přídat");
            System.out.println("2) Odebrat");
            System.out.println("3) Exit");
            int num = getNumber(1, 3);
            if(num == 1) {
                add();
            } else if (num == 2) {
                remove();
            } else {
                break;
            }
        }
    }

    public void printPosts() {
        System.out.println("---------Posts-----------------");
        for (int i = 0; i < service.getCourse().getPosts().size(); i++) {
            System.out.println(i +") "+service.getCourse().getPosts().get(i).getTitle());
        }
    }

    public void add() {
        System.out.println("Zadej co chces pridat");
        System.out.println("1) Ukol");
        System.out.println("2) Oznameni");
        System.out.println("3) Material");
        int num = getNumber(1,3);
        Post c;
        System.out.println("isVisible");
        boolean isVisible = getNumber(0,1) == 1;
        System.out.println("title");
        String title = sc.nextLine();
        if(num == 1) {
            System.out.println("Zadej rok odevzdání");
            int year = getNumber(LocalDateTime.now().getYear(), 2030);
            System.out.println("Zadej měsíc odevzdání");
            int month = getNumber(1, 12);
            System.out.println("Zadej den odevzdání");
            int day = getNumber(1, 31);
            System.out.println("Zadej hodinu odevzdání");
            int hours = getNumber(0, 23);
            System.out.println("Zadej minutu odevzdání");
            int minutes = getNumber(0, 59);
            c = new Task(isVisible, title, LocalDateTime.of(year, month, day, hours, minutes));
        } else if(num == 2) {
            System.out.println("Zadej obsah oznámení");
            c = new Notification(isVisible, title, sc.nextLine());
        } else {
            System.out.println("Zadej url materiálu");
            c = new Material(isVisible, title, sc.nextLine());
        }
        service.addPost(c);
    }

    public void remove() {
        printPosts();
        System.out.println("Zadej co chces smazat");
        int num = getNumber(0, service.getCourse().getPosts().size() - 1);
        service.removePost(service.getCourse().getPosts().get(num));
    }

    private int getNumber(int from, int to) {
        while(true) {
            try {
                int num = Integer.parseInt(sc.nextLine());
                if(num <= to && num >= from) return num;
            } catch (NumberFormatException e) {}
            System.out.println("Neplatné číslo");
        }

    }
}
