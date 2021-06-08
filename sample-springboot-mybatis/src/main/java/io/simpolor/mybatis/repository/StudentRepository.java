package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.model.StudentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private SqlSession sqlSession;

    public long selectStudentTotalCount(){
        return sqlSession.selectOne("selectStudentTotalCount");
    }

    public List<StudentDto> selectStudentList(){
        return sqlSession.selectList("selectStudentList");
    }

    public StudentDto selectStudent(long seq){
        return sqlSession.selectOne("selectStudent", seq);
    }

    public int insertStudent(StudentDto student){
        return sqlSession.insert("insertStudent", student);
    }

    public int updateStudent(StudentDto student){
        return sqlSession.update("updateStudent", student);
    }

    public int deleteStudent(long seq){
        return sqlSession.delete("deleteStudent", seq);
    }
}
