package io.simpolor.mybatis.mapper;

import io.simpolor.mybatis.repository.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

	Long selectStudentCountMapper();

	List<Student> selectAllStudentMapper();

	Student selectStudentMapper(Long studentId);

	Integer insertStudentMapper(Student student);

	Integer updateStudentMapper(Student student);

	Integer deleteStudentMapper(Long studentId);
	
	@Select("SELECT COUNT(*) FROM student")
	Long selectAllCount();
}
