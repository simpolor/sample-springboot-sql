package io.simpolor.jdbc.repository.mapper;

import io.simpolor.jdbc.repository.entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class StudentMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        Student student = new Student();
        student.setStudentId(rs.getLong("student_id"));
        student.setName(rs.getString("name"));
        student.setGrade(rs.getInt("grade"));
        student.setAge(rs.getInt("age"));
        String hobbies = rs.getString("hobbies");
        student.setHobbies(Arrays.asList(hobbies.split(",")));

        return student;
    }
}
