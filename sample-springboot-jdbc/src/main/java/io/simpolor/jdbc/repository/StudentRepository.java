package io.simpolor.jdbc.repository;

import io.simpolor.jdbc.model.StudentDto;
import io.simpolor.jdbc.repository.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long selectCount(){
        String query = "SELECT COUNT(*) FROM student";
        int result = jdbcTemplate.queryForObject(query, Integer.class);
        return result;
    }

    public List<StudentDto> selectList(){
        String query = "SELECT * FROM student";
        RowMapper<StudentDto> rowMapper = new BeanPropertyRowMapper<>(StudentDto.class);
        List<StudentDto> result = jdbcTemplate.query(query, rowMapper);
        return result;
    }

    public StudentDto selectOne(long seq){

        try {
            String query = "SELECT * FROM student WHERE seq = ?";
            return jdbcTemplate.queryForObject(query, new Object[]{seq}, new StudentMapper());

        }catch (EmptyResultDataAccessException e){
            log.warn("selectOne is empty : {}", seq, e);
        }

        return null;
    }

    public long insert(StudentDto student){

        String query = "INSERT INTO student ( name, grade, age, hobby ) values ( ?, ?, ?, ? )";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[]{"seq"});
                    ps.setString(1, student.getName());
                    ps.setInt(2, student.getGrade());
                    ps.setInt(3, student.getAge());
                    ps.setString(4, stringJoiner(student.getHobby()));
                    return ps;
                }, keyHolder);

        Number key = keyHolder.getKey();

        return key.longValue();
    }

    public int update(StudentDto student){
        String query = "UPDATE student SET name=?, grade=?, age=?, hobby=? WHERE seq = ?";
        return jdbcTemplate.update(query, student.getName(), student.getGrade(), student.getAge(), this.stringJoiner(student.getHobby()), student.getSeq());
    }

    public int delete(long seq){
        String query = "DELETE FROM student WHERE seq = ?";
        return jdbcTemplate.update(query, seq);
    }

    public String stringJoiner(List<String> list){
        StringJoiner joiner = new StringJoiner(",");
        for (String str : list){
            joiner.add(str);
        }
        return joiner.toString();

    }
}
