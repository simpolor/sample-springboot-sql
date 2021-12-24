INSERT INTO classroom(classroom_id, name, created_at, updated_at)
     VALUES (1, '햇님반', now(), now());

INSERT INTO student(student_id, classroom_id, name, grade, age, created_at, updated_at)
     VALUES (1, 1, '하니', 1, 17, now(), now());

INSERT INTO student(student_id, classroom_id, name, grade, age, created_at, updated_at)
     VALUES (2, 1, '홍길동', 3, 19, now(), now());

