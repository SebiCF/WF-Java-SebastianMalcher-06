
--get all students where class_id = 2 (= "1b")
SELECT * FROM `students` WHERE `fk_class_id` = 2

--get all students and their respective class where class = "1b"
SELECT students.student_id, students.student_name, students.student_surname, classes.class_id, classes.class_name
FROM students
INNER JOIN `classes` on classes.class_id = students.fk_class_id
WHERE classes.class_name = "1b"




