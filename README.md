# Student Course Enrollment Management System

**Student Name:** DUDIGAM BHUVAN SAI

## Project Overview

The Student Course Enrollment Management System is a Java JDBC application connected to an Oracle Database. It manages student records, college courses, and student enrollments.

The application allows users to create courses, enroll students in courses, display students enrolled in a selected course, and prevent duplicate enrollments.

## Objectives

- Manage student and course records.
- Connect Java applications to Oracle Database using JDBC.
- Enroll students in college courses.
- Display students registered for a selected course.
- Prevent duplicate enrollment in the same course.

## Technologies Used

- **Programming Language:** Java
- **Database:** Oracle Database
- **Database Connectivity:** JDBC
- **JDBC Driver:** Oracle JDBC Driver (`ojdbc`)
- **Development Environment:** Visual Studio Code

## Features

1. **Add Student:** Store student ID and name.
2. **Create Course:** Store course ID and course name.
3. **Enroll Student:** Register an existing student for an existing course.
4. **Display Enrollments:** View students enrolled in a selected course.
5. **Duplicate Prevention:** Prevent the same student from enrolling in the same course more than once.
6. **Menu-Driven Interface:** Perform operations using a simple console menu.

## Database Design

The application uses three tables:

- **STUDENTS:** Stores student IDs and names.
- **COURSES:** Stores course IDs and course names.
- **ENROLLMENTS:** Connects students with courses.

The enrollment table uses a composite primary key consisting of the student ID and course ID. This prevents duplicate enrollment records. Foreign keys maintain relationships between the tables.

## Prerequisites

- Java Development Kit (JDK)
- Oracle Database with an accessible database service
- Oracle JDBC driver (`ojdbc` JAR)
- Visual Studio Code or another Java IDE

## How to Run

1. Clone or download this repository.
2. Install Java and configure Oracle Database.
3. Download the appropriate Oracle JDBC driver for your JDK and Oracle Database.
4. Place the JDBC driver JAR in the project folder alongside `CourseEnrollment.java`.
5. Configure the database URL, username, and password in the Java source code to match your local Oracle setup.
6. Open a terminal in the project folder.

Compile the program on Windows:

```powershell
javac -cp ".;ojdbc17.jar" CourseEnrollment.java
```

Run the program:

```powershell
java -cp ".;ojdbc17.jar" CourseEnrollment
```

If your JDBC driver has a different filename, replace `ojdbc17.jar` with that filename in both commands.

## Expected Workflow

1. Run the application.
2. Add a student.
3. Create a course.
4. Enroll the student in the course.
5. Display the students enrolled in that course.
6. Attempt the same enrollment again and verify that it is rejected.

## Learning Outcomes

This project demonstrates Java database connectivity, SQL operations, relational database design, primary and foreign keys, prepared statements, exception handling, and prevention of duplicate records.

## Author

**DUDIGAM BHUVAN SAI**

Academic project — Student Course Enrollment Management System.
