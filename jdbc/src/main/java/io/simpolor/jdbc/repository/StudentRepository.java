package io.simpolor.jdbc.repository;

import io.simpolor.jdbc.repository.entity.Student;
import io.simpolor.jdbc.repository.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long selectCount(){

        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) ");
        query.append("FROM student ");

        return jdbcTemplate.queryForObject(query.toString(), Long.class);
    }

    public List<Student> selectList(){

        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("FROM student ");

        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);

        return jdbcTemplate.query(query.toString(), rowMapper);
    }

    public Student selectOne(Long studentId){

        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * ");
            query.append("FROM student ");
            query.append("WHERE student_id = ? ");

            return jdbcTemplate.queryForObject(
                    query.toString(),
                    new StudentMapper(),
                    new Object[]{ studentId });

        }catch (EmptyResultDataAccessException e){
            log.warn("selectOne is empty : {}", studentId, e);
        }

        return null;
    }

    public Student insert(Student student){

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO student ( name, grade, age, hobbies) ");
        query.append("VALUES ( ?, ?, ?, ? ) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query.toString(), new String[]{"student_id"});
                    ps.setString(1, student.getName());
                    ps.setInt(2, student.getGrade());
                    ps.setInt(3, student.getAge());
                    ps.setString(4,
                            !CollectionUtils.isEmpty(student.getHobbies())
                                    ? student.getHobbies().stream()
                                    .collect(Collectors.joining(","))
                                    : null);
                    return ps;

                }, keyHolder);

        Number key = keyHolder.getKey();
        student.setStudentId(key.longValue());

        return student;
    }

    public void update(Student student){

        StringBuilder query = new StringBuilder();
        query.append("UPDATE student ");
        query.append("SET name=?, grade=?, age=?, hobbies=? ");
        query.append("WHERE student_id=? ");

        jdbcTemplate.update(
                query.toString(),
                student.getName(),
                student.getGrade(),
                student.getAge(),
                !CollectionUtils.isEmpty(student.getHobbies())
                    ? student.getHobbies().stream()
                        .collect(Collectors.joining(","))
                    : null,
                student.getStudentId());
    }

    public void delete(Long studentId){

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM student ");
        query.append("WHERE student_id=? ");

        jdbcTemplate.update(query.toString(), studentId);
    }
}
