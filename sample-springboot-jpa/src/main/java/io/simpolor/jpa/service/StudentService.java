package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentRepository;
import io.simpolor.jpa.repository.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TagService tagService;
    private final ClassroomService classroomService;
    private final StudentClassroomService studentClassroomService;
    private final TeacherService teacherService;
    private final StudentTeacherService studentTeacherService;

    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(long seq) {

        Optional<Student> studentOptional = studentRepository.findById(seq);
        if(!studentOptional.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return studentOptional.get();
    }

    public void register(Student student, List<String> classroomNames, List<Long> teacherSequences) {

        studentRepository.saveAndFlush(student);
        if(Objects.nonNull(student.getTag())) {
            tagService.register(student.getTag(), student);
        }

        List<Classroom> classrooms = classroomService.saveAndGet(classroomNames);
        if(!CollectionUtils.isEmpty(classrooms)){
            List<StudentClassroom> studentClassrooms = new ArrayList<>();
            for(Classroom classroom : classrooms){
                studentClassrooms.add(StudentClassroom.builder().student(student).classroom(classroom).build());
            }
            studentClassroomService.register(studentClassrooms);
        }

        List<Teacher> teachers = teacherService.getAll(teacherSequences);
        if(!CollectionUtils.isEmpty(teachers)){
            List<StudentTeacher> studentTeachers = new ArrayList<>();
            for(Teacher teacher : teachers){
                studentTeachers.add(StudentTeacher.builder().student(student).teacher(teacher).build());
            }
            studentTeacherService.register(studentTeachers);
        }
    }

    public void modify(Student student, List<String> classroomNames, List<Long> teacherSequences) {

        studentRepository.findById(student.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("seq : "+student.getSeq()));

        studentRepository.saveAndFlush(student);
        if(Objects.nonNull(student.getTag())) {
            tagService.modify(student.getTag(), student);
        }

        studentClassroomService.getAndDelete(student.getSeq());
        List<Classroom> classrooms = classroomService.saveAndGet(classroomNames);
        System.out.println("classroomNames : "+classroomNames);
        if(!CollectionUtils.isEmpty(classrooms)){
            List<StudentClassroom> studentClassrooms = new ArrayList<>();
            for(Classroom classroom : classrooms){
                studentClassrooms.add(StudentClassroom.builder().student(student).classroom(classroom).build());
            }
            studentClassroomService.register(studentClassrooms);
        }

        List<Teacher> teachers = teacherService.getAll(teacherSequences);
        if(!CollectionUtils.isEmpty(teachers)){
            List<StudentTeacher> studentTeachers = new ArrayList<>();
            for(Teacher teacher : teachers){
                studentTeachers.add(StudentTeacher.builder().student(student).teacher(teacher).build());
            }
            studentTeacherService.modify(studentTeachers, student.getSeq());
        }
    }

    public void delete(long seq) {

        studentRepository.deleteById(seq);
    }

}
