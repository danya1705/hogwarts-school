CREATE TABLE car
(
    id    INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price MONEY
);

CREATE TABLE person
(
    id         INTEGER PRIMARY KEY,
    name       TEXT,
    age        INTEGER,
    permission BOOLEAN,
    car_id     INTEGER
);

SELECT student.name, student.age, faculty.name FROM student
LEFT JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, student.age, faculty.name FROM student
LEFT JOIN faculty ON student.faculty_id = faculty.id
RIGHT JOIN avatar ON student.id = avatar.student_id;
