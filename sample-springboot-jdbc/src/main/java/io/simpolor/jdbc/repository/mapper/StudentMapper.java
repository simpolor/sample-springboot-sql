package io.simpolor.jdbc.repository.mapper;

import io.simpolor.jdbc.model.StudentDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class StudentMapper implements RowMapper<StudentDto> {

    public StudentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentDto student = new StudentDto();

        student.setSeq(rs.getLong("seq"));
        student.setName(rs.getString("name"));
        student.setGrade(rs.getInt("grade"));
        student.setAge(rs.getInt("age"));

        String hobby = rs.getString("hobby");
        student.setHobby(Arrays.asList(hobby.split(",")));

        return student;
    }
}
