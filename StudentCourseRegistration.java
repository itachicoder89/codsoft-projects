import java.util.*;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public boolean registerStudent(String studentID) {
        if (registeredStudents.size() < capacity && !registeredStudents.contains(studentID)) {
            registeredStudents.add(studentID);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentID) {
        if (registeredStudents.contains(studentID)) {
            registeredStudents.remove(studentID);
            return true;
        }
        return false;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode) && course.registerStudent(studentID)) {
                registeredCourses.add(courseCode);
                return true;
            }
        }
        return false;
    }

    public boolean removeCourse(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode) && course.removeStudent(studentID)) {
                registeredCourses.remove(courseCode);
                return true;
            }
        }
        return false;
    }
}

public class StudentCourseRegistration {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        // Adding sample courses
        courses.add(new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of programming", 50, Arrays.asList("Mon 9-11 AM", "Wed 9-11 AM")));
        courses.add(new Course("MATH202", "Calculus II", "Advanced calculus topics", 40, Arrays.asList("Tue 2-4 PM", "Thu 2-4 PM")));
        courses.add(new Course("PHY303", "Physics Fundamentals", "Basic principles of physics", 30, Arrays.asList("Mon 1-3 PM", "Wed 1-3 PM")));
        courses.add(new Course("CHEM101", "Chemistry Basics", "Introduction to chemistry", 60, Arrays.asList("Tue 9-11 AM", "Thu 9-11 AM")));
        courses.add(new Course("ENG202", "English Literature", "Classic literature and analysis", 35, Arrays.asList("Mon 3-5 PM", "Wed 3-5 PM")));

        // Adding students with names and IDs
        students.add(new Student("S001", "Ashish"));
        students.add(new Student("S002", "Abhishek"));
        students.add(new Student("S003", "Dhiraj"));
        students.add(new Student("S004", "Anika"));
        students.add(new Student("S005", "Khargosh"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nOptions:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Display Student Information");
            System.out.println("3. Register for a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableCourses(courses);
                    break;
                case 2:
                    displayStudentInformation(students);
                    break;
                case 3:
                    registerForCourse(scanner, students, courses);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 4);

        scanner.close();
    }

    private static void displayAvailableCourses(List<Course> courses) {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course.getCode() + " - " + course.getTitle());
        }
    }

    private static void displayStudentInformation(List<Student> students) {
        System.out.println("\nStudent Information:");
        for (Student student : students) {
            System.out.println(student.getStudentID() + " - " + student.getName());
        }
    }

    private static void registerForCourse(Scanner scanner, List<Student> students, List<Course> courses) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        Student student = findStudentByID(studentID, students);

        if (student != null) {
            System.out.print("Enter Course Code to Register: ");
            String courseCode = scanner.next();
            Course course = findCourseByCode(courseCode, courses);

            if (course != null) {
                boolean registrationSuccess = student.registerCourse(courseCode, courses);
                if (registrationSuccess) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed. The course may be full or already registered.");
                }
            } else {
                System.out.println("Course not found with code: " + courseCode);
            }
        } else {
            System.out.println("Student not found with ID: " + studentID);
        }
    }

    private static Student findStudentByID(String studentID, List<Student> students) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
