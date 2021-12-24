CREATE TABLE `student` (
  `student_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '학생 아이디',
  `classroom_id` INT(50) NULL DEFAULT NULL COMMENT '반 아이디',
  `name` VARCHAR(50) NOT NULL COMMENT '이름',
  `grade` INT(11) NULL DEFAULT NULL COMMENT '학년',
  `age` INT(11) NULL DEFAULT NULL COMMENT '나이',
  `created_at` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `updated_at` DATETIME NULL DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`student_id`)
);

CREATE TABLE `classroom` (
  `classroom_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '반 아이디',
  `name` VARCHAR(50) NOT NULL COMMENT '반 이름',
  `created_at` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  `updated_at` DATETIME NULL DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`classroom_id`)
);
