package com.example.restapi.service.impl;

import com.example.restapi.dto.AddStudentRequestDto;
import com.example.restapi.dto.StudentDto;
import com.example.restapi.entity.Student;
import com.example.restapi.repository.StudentRepository;
import com.example.restapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto,Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
         return students
                .stream()
                .map(student -> modelMapper.map(student,StudentDto.class)).toList();
    }

    @Override
    public StudentDto getStudentById(Long id) throws IllegalAccessException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Student not Found with this corresponding id: " + id));
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public String deleteById(Long id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return ("Student Deleted Successfully with id:" + id);
        }
        else{
            return "Student not found with id:" + id;
        }
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) throws IllegalAccessException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Student mot Found with this corresponding id: " + id));
        modelMapper.map(addStudentRequestDto,student);

        student = studentRepository.save(student);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) throws IllegalAccessException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Student not Found with this corresponding id: " + id));

        updates.forEach((field,value) ->{
            switch (field){
                case "name":student.setName((String) value);break;
                case "email":student.setEmail((String) value);break;
                default:throw new IllegalArgumentException("Student not Supported");
            }
        });
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent,StudentDto.class);
    }




}
