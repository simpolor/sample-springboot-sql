package io.simpolor.mysql.service;

import io.simpolor.mysql.domain.Classroom;
import io.simpolor.mysql.domain.Student;
import io.simpolor.mysql.domain.StudentClassroom;
import io.simpolor.mysql.domain.StudentResponse;
import io.simpolor.mysql.repository.ClassroomRepository;
import io.simpolor.mysql.repository.StudentClassroomRepository;
import io.simpolor.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;


    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student get(long seq) {

        // return studentRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("seq : "+seq));
        // StudentClassroom studentClassroom = studentClassroomRepository.findAllByStudentIn(seq);
        StudentClassroom studentClassroom = studentClassroomRepository.findById(seq).get();

        System.out.println("getSeq : "+studentClassroom.getSeq());
        System.out.println("getOrdering : "+studentClassroom.getOrdering());
        System.out.println("getOrdering : "+studentClassroom.getOrdering());

        System.out.println("------------------------");
        List<StudentRepository.StudentsInterface> selectStudents = studentRepository.selectStudents();
        for(StudentRepository.StudentsInterface selectStudent : selectStudents){
            System.out.println("getSeq : "+selectStudent.getSeq());
            System.out.println("getName : "+selectStudent.getName());
            System.out.println("getClassRoom : "+selectStudent.getClassName());
        }

        // System.out.println("getStudent : "+studentClassroom.getStudent());
        // System.out.println("getClassroom : "+studentClassroom.getClassroom());

        return null;
    }

    public void register(Student student, List<String> classrooms) {

        studentRepository.save(student);

        for(String s : classrooms){
            Classroom classroom = new Classroom();
            classroom.setClassName(s);
            classroomRepository.save(classroom);

            StudentClassroom studentClassroom = new StudentClassroom();
            studentClassroom.setStudent(student);
            studentClassroom.setClassroom(classroom);

            studentClassroomRepository.save(studentClassroom);
        }
    }

    public void modify(Student student) {

        studentRepository.findById(student.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("seq : "+student.getSeq()));

        studentRepository.save(student);
    }

    public void delete(long seq) {

        studentRepository.deleteById(seq);
    }

}
