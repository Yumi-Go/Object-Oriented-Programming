# Object-Oriented-Programming
[2021-22] Object Oriented Programming module

I developed an application that stores student records at MTU. These records consist of information about each student and information about the modules they have completed and the grades they received. This allows the university to stay informed about student performance. I used JavaFX for GUI (without using Scene Builder at all) and used Hibernate JPA for keeping DB. Also, I used the MVC pattern and a package structure to reflect the MVC pattern.

This application allows users to:
  1. Use the GUI to add a new Student to the database (each student should have Name, ID, and DateOfBirth attributes)
  2. Use the GUI to remove an existing Student from the database
  3. Select a student in the database and edit their details (Name, ID, or DateOfBirth)
  4. Update a Student’s record to show the modules they completed and the grades they attained in each module
  5. If a Student is removed from the database their module information should also be removed
  6. Search for a particular student in the database and display that student’s information and their module results
  7. Display all of the modules where a student attained a first-class honor (grade equal to or greater than 70%) – based on a database query
  8. Display all of the students in the system
  9. Quit
