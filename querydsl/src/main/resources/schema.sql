CREATE TABLE `student` (
  `student_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
  `name` VARCHAR(50) NOT NULL COMMENT '이름',
  `grade` INT(11) NULL DEFAULT NULL COMMENT '학년',
  `age` INT(11) NULL DEFAULT NULL COMMENT '나이',
  PRIMARY KEY (`student_id`)
);


CREATE TABLE `tuition` (
  `tuition_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
  `student_id` INT(11) NOT NULL COMMENT '학생 번호',
  `fees` INT(11) NULL DEFAULT NULL COMMENT '회비',
  PRIMARY KEY (`tuition_id`)
);
