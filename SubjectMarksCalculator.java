import java.util.Scanner;

public class SubjectMarksCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Subject Marks Calculator!");
        System.out.println("Please enter the marks obtained (out of 100) for each subject.");

        String[] subjects = {"Math", "Science", "English", "History", "Art"};
        int[] marks = new int[subjects.length];

        for (int i = 0; i < subjects.length; i++) {
            System.out.print(subjects[i] + ": ");
            marks[i] = scanner.nextInt();

            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid input! Marks should be between 0 and 100.");
                System.exit(1);
            }
        }

        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        double percentage = (double) totalMarks / (subjects.length * 100) * 100;
        double averagePercentage = percentage / subjects.length;

        System.out.println("\nResults:");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Average Percentage: " + averagePercentage + "%");

        String grade;
        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
            System.out.println("Don't be disheartened by the low grade. Remember, every setback is a setup for a comeback!");
        }
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
