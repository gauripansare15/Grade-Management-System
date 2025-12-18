import java.util.Scanner;

public class GradeManagementSystem {

    // ===== CONSTANTS =====
    private static final int MAX_STUDENTS = 100;
    private static final int SUBJECT_COUNT = 5;

    // ===== DATA STORAGE =====
    private static String[] studentNames = new String[MAX_STUDENTS];
    private static double[][] studentMarks = new double[MAX_STUDENTS][SUBJECT_COUNT];
    private static int studentCount = 0;

    private static Scanner scanner = new Scanner(System.in);

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. Calculate Averages & Grades");
            System.out.println("4. Find Top Performers");
            System.out.println("5. Generate Performance Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInt(1, 6);

            switch (choice) {
                case 1:
                    addStudentMarks();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    calculateAverages();
                    break;
                case 4:
                    findTopPerformers();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Grade Management System!");
                    break;
            }
        }
        scanner.close();
    }

    // ===== ADD STUDENT =====
    private static void addStudentMarks() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Maximum student limit reached!");
            return;
        }

        System.out.println("\n=== ADD STUDENT MARKS ===");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        studentNames[studentCount] = name;

        String[] subjects = {"Mathematics", "Science", "English", "History", "Computer"};

        for (int i = 0; i < SUBJECT_COUNT; i++) {
            System.out.print(subjects[i] + ": ");
            studentMarks[studentCount][i] = getValidMark();
        }

        studentCount++;
        System.out.println("Student added successfully!\n");
    }

    // ===== VIEW STUDENTS =====
    private static void viewAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\n=== ALL STUDENTS ===");
        System.out.printf("%-20s %-8s %-8s %-8s %-8s %-8s %-8s\n",
                "Name", "Math", "Sci", "Eng", "Hist", "Comp", "Avg");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            double avg = calculateStudentAverage(i);
            System.out.printf("%-20s %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f\n",
                    studentNames[i],
                    studentMarks[i][0], studentMarks[i][1], studentMarks[i][2],
                    studentMarks[i][3], studentMarks[i][4], avg);
        }
    }

    // ===== CALCULATE AVERAGES =====
    private static void calculateAverages() {
        if (studentCount == 0) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\n=== AVERAGES & GRADES ===");
        for (int i = 0; i < studentCount; i++) {
            double avg = calculateStudentAverage(i);
            String grade = getGrade(avg);
            System.out.println(studentNames[i] + " -> Average: " + String.format("%.2f", avg) + ", Grade: " + grade);
        }
    }

    // ===== TOP PERFORMERS =====
    private static void findTopPerformers() {
        if (studentCount == 0) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\n=== TOP PERFORMERS ===");
        double highestAvg = 0;

        for (int i = 0; i < studentCount; i++) {
            double avg = calculateStudentAverage(i);
            if (avg > highestAvg) {
                highestAvg = avg;
            }
        }

        for (int i = 0; i < studentCount; i++) {
            if (calculateStudentAverage(i) == highestAvg) {
                System.out.println(studentNames[i] + " -> " + String.format("%.2f", highestAvg));
            }
        }
    }

    // ===== REPORT =====
    private static void generateReport() {
        if (studentCount == 0) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\n=== PERFORMANCE REPORT ===");
        System.out.println("Total Students: " + studentCount);

        double[] subjectTotal = new double[SUBJECT_COUNT];
        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < SUBJECT_COUNT; j++) {
                subjectTotal[j] += studentMarks[i][j];
            }
        }

        String[] subjects = {"Math", "Science", "English", "History", "Computer"};
        System.out.println("\nSubject Averages:");
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            System.out.println(subjects[i] + ": " + String.format("%.2f", subjectTotal[i] / studentCount));
        }
    }

    // ===== HELPER METHODS =====
    private static double calculateStudentAverage(int index) {
        double sum = 0;
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            sum += studentMarks[index][i];
        }
        return sum / SUBJECT_COUNT;
    }

    private static String getGrade(double avg) {
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }

    private static int getValidInt(int min, int max) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) return value;
                System.out.print("Enter between " + min + " and " + max + ": ");
            } catch (Exception e) {
                System.out.print("Invalid input! Enter number: ");
            }
        }
    }

    private static double getValidMark() {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value >= 0 && value <= 100) return value;
                System.out.print("Marks must be 0-100. Re-enter: ");
            } catch (Exception e) {
                System.out.print("Invalid input! Enter number: ");
            }
        }
    }
}
