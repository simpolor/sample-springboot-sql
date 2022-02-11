INSERT INTO classroom(classroom_id, name, created_at, updated_at)
     VALUES (1, '햇님반', now(), now());

INSERT INTO parent(parent_id, father_name, mother_name)
     VALUES (1, '홍반장', '김순자');

INSERT INTO student(student_id, classroom_id, parent_id, name, grade, age, created_at, updated_at)
     VALUES (1, 1, 1, '하니', 1, 17, now(), now());

INSERT INTO student(student_id, classroom_id, parent_id, name, grade, age, created_at, updated_at)
     VALUES (2, 1, null, '홍길동', 3, 19, now(), now());

INSERT INTO favorite_food(student_id, food_name)
     VALUES (1, '치킨');

INSERT INTO favorite_food(student_id, food_name)
     VALUES (1, '피자');



