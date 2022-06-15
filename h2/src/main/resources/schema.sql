CREATE TABLE `student` (
  `student_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '학생 아이디',
  `name` VARCHAR(50) NOT NULL COMMENT '이름',
  `grade` INT(11) NULL DEFAULT NULL COMMENT '학년',
  `age` INT(11) NULL DEFAULT NULL COMMENT '나이',
  PRIMARY KEY (`student_id`)
);
