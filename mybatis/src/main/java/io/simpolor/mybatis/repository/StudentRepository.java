package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final SqlSession sqlSession;

    public long selectStudentCount(){
        return sqlSession.selectOne("selectStudentCount");
    }

    public List<Student> selectAllStudent(){
        return sqlSession.selectList("selectAllStudent");
    }

    public Student selectStudent(Long studentId){
        return sqlSession.selectOne("selectStudent", studentId);
    }

    public int insertStudent(Student student){
        return sqlSession.insert("insertStudent", student);
    }

    public int updateStudent(Student student){
        return sqlSession.update("updateStudent", student);
    }

    public int deleteStudent(Long studentId){
        return sqlSession.delete("deleteStudent", studentId);
    }
}
