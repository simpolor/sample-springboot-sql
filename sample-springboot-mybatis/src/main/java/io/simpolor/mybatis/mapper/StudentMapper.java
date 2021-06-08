package io.simpolor.mybatis.mapper;

import io.simpolor.mybatis.model.StudentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

	long selectStudentTotalCountMapper();

	List<StudentDto> selectStudentListMapper();

	StudentDto selectStudentMapper(long seq);

	int insertStudentMapper(StudentDto studentDto);

	int updateStudentMapper(StudentDto studentDto);

	int deleteStudentMapper(long seq);
	
	@Select("SELECT COUNT(*) FROM student")
	int findByAllCount();
}
