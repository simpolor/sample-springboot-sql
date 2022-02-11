CREATE TABLE `student` (
  `student_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '학생 아이디',
  `classroom_id` INT(50) NULL DEFAULT NULL COMMENT '반 아이디',
  `parent_id` INT(50) NULL DEFAULT NULL COMMENT '부모 아이디',
  `name` VARCHAR(50) NOT NULL COMMENT '이름',
  `grade` INT(11) NULL DEFAULT NULL COMMENT '학년',
  `age` INT(11) NULL DEFAULT NULL COMMENT '나이',
  `hobbies` VARCHAR(255) NULL DEFAULT NULL COMMENT '취미',
  `created_at` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `updated_at` DATETIME NULL DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`student_id`)
);

CREATE TABLE `favorite_food` (
  `student_id` BIGINT(20) NOT NULL COMMENT '학생 아이디',
  `food_name` VARCHAR(50) NOT NULL COMMENT '음식 이름'
);

CREATE INDEX favorite_food_index ON favorite_food (student_id);

CREATE TABLE `parent` (
  `parent_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '부모 아이디',
  `father_name` VARCHAR(50) NOT NULL COMMENT '아버지 이름',
  `mother_name` VARCHAR(50) NOT NULL COMMENT '어머니 이름',
  PRIMARY KEY (`parent_id`)
);

CREATE TABLE `classroom` (
  `classroom_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '반 아이디',
  `name` VARCHAR(50) NOT NULL COMMENT '반 이름',
  `created_at` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `updated_at` DATETIME NULL DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`classroom_id`)
);
