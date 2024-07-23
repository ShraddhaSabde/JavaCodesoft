import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;

    Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    boolean isFull() {
        return enrolled >= capacity;
    }

    void enroll() {
        if (!isFull()) {
            enrolled++;
        }
    }

    void withdraw() {
        if (enrolled > 0) {
            enrolled--;
        }
    }


    public String toString() {
        return code + ": " + title + " (" + enrolled + "/" + capacity + ") - " + description;
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void register(Course course) {
        if (!registeredCourses.contains(course) && !course.isFull()) {
            registeredCourses.add(course);
            course.enroll();
            System.out.println(name + " has been registered for " + course.title);
        } else {
            System.out.println("Cannot register for " + course.title);
        }
    }

    void drop(Course course) {
        if (registeredCourses.remove(course)) {
            course.withdraw();
            System.out.println(name + " has dropped " + course.title);
        } else {
            System.out.println("Not registered for " + course.title);
        }
    }

    void listRegistered() {
        if (registeredCourses.isEmpty()) {
            System.out.println(name + " has not registered for any courses.");
        } else {
            System.out.println(name + "'s Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class Task5 {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. List My Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    listMyCourses(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 3));
        courses.add(new Course("CS102", "Data Structures", "Introduction to data structures", 2));
        courses.add(new Course("CS103", "Algorithms", "Fundamentals of algorithms", 2));
    }

    private static void initializeStudents() {
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to register for: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.register(course);
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.drop(course);
    }

    private static void listMyCourses(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.listRegistered();
    }

    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.code.equals(code)) {
                return course;
            }
        }
        return null;
    }
}
