package io.simpolor.mybatis.mapper;

import io.simpolor.mybatis.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

	int selectStudentTotalCountMapper();

	List<Student> selectStudentListMapper();

	Student selectStudentMapper(long seq);

	int insertStudentMapper(Student student);

	int updateStudentMapper(Student student);

	int deleteStudentMapper(long seq);
	
	@Select("SELECT COUNT(*) FROM student")
	int findByAllCount();
}
