

import java.sql.*;
import java.util.Scanner;

public class CourseEnrollment {

    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String user = "college";
        String password = "college123";

        try (Connection con =
                 DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to Oracle Database!");

            // Create tables if they do not already exist
            createTable(con,
                "CREATE TABLE students (" +
                "sid NUMBER PRIMARY KEY, " +
                "name VARCHAR2(50))");

            createTable(con,
                "CREATE TABLE courses (" +
                "cid NUMBER PRIMARY KEY, " +
                "cname VARCHAR2(50))");

            createTable(con,
                "CREATE TABLE enrollments (" +
                "sid NUMBER REFERENCES students(sid), " +
                "cid NUMBER REFERENCES courses(cid), " +
                "PRIMARY KEY (sid, cid))");

            int choice = 0;

            do {
                System.out.println("\n--- COURSE ENROLLMENT ---");
                System.out.println("1. Add Student");
                System.out.println("2. Create Course");
                System.out.println("3. Enroll Student");
                System.out.println("4. Display Students in Course");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    sc.nextLine();
                    continue;
                }

                choice = sc.nextInt();
                sc.nextLine();

                try {
                    switch (choice) {

                        case 1:
                            System.out.print("Student ID: ");
                            int sid = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Student name: ");
                            String name = sc.nextLine();

                            try (PreparedStatement ps =
                                     con.prepareStatement(
                                         "INSERT INTO students VALUES (?, ?)")) {
                                ps.setInt(1, sid);
                                ps.setString(2, name);
                                ps.executeUpdate();
                            }

                            System.out.println(
                                "Student added successfully!");
                            break;

                        case 2:
                            System.out.print("Course ID: ");
                            int cid = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Course name: ");
                            String cname = sc.nextLine();

                            try (PreparedStatement ps =
                                     con.prepareStatement(
                                         "INSERT INTO courses VALUES (?, ?)")) {
                                ps.setInt(1, cid);
                                ps.setString(2, cname);
                                ps.executeUpdate();
                            }

                            System.out.println(
                                "Course created successfully!");
                            break;

                        case 3:
                            System.out.print("Student ID: ");
                            int studentId = sc.nextInt();

                            System.out.print("Course ID: ");
                            int courseId = sc.nextInt();
                            sc.nextLine();

                            try (PreparedStatement ps =
                                     con.prepareStatement(
                                         "INSERT INTO enrollments VALUES (?, ?)")) {
                                ps.setInt(1, studentId);
                                ps.setInt(2, courseId);
                                ps.executeUpdate();
                            }

                            System.out.println(
                                "Enrollment successful!");
                            break;

                        case 4:
                            System.out.print("Enter course ID: ");
                            int searchId = sc.nextInt();
                            sc.nextLine();

                            try (PreparedStatement ps =
                                     con.prepareStatement(
                                         "SELECT s.sid, s.name " +
                                         "FROM students s " +
                                         "JOIN enrollments e " +
                                         "ON s.sid = e.sid " +
                                         "WHERE e.cid = ?")) {

                                ps.setInt(1, searchId);

                                try (ResultSet rs = ps.executeQuery()) {
                                    boolean found = false;

                                    while (rs.next()) {
                                        found = true;
                                        System.out.println(
                                            "Student ID: " +
                                            rs.getInt("sid") +
                                            ", Name: " +
                                            rs.getString("name"));
                                    }

                                    if (!found) {
                                        System.out.println(
                                            "No students enrolled in this course.");
                                    }
                                }
                            }
                            break;

                        case 5:
                            System.out.println("Program ended.");
                            break;

                        default:
                            System.out.println(
                                "Invalid choice. Enter 1 to 5.");
                    }

                } catch (SQLException e) {
                    if (e.getErrorCode() == 1) {
                        System.out.println(
                            "Duplicate ID or enrollment already exists!");
                    } else if (e.getErrorCode() == 2291) {
                        System.out.println(
                            "Invalid student ID or course ID!");
                    } else {
                        System.out.println(
                            "Database error: " + e.getMessage());
                    }
                }

            } while (choice != 5);

        } catch (SQLException e) {
            System.out.println(
                "Could not connect to Oracle: " + e.getMessage());
        }
    }

    // Creates a table only if it does not already exist
    private static void createTable(Connection con, String sql)
            throws SQLException {

        try (Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getErrorCode() != 955) {
                throw e;
            }
        }
    }
}
