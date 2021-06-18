package io.simpolor.mybatis.mapper;

import io.simpolor.mybatis.model.StudentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

	Integer selectStudentTotalCountMapper();

	List<StudentDto> selectStudentListMapper();

	StudentDto selectStudentMapper(Long seq);

	Integer insertStudentMapper(StudentDto studentDto);

	Integer updateStudentMapper(StudentDto studentDto);

	Integer deleteStudentMapper(Long seq);
	
	@Select("SELECT COUNT(*) FROM student")
	int findByAllCount();
}
