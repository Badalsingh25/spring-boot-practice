package com.codingshuttle.youtube.LearningSpringboot.service;


import com.codingshuttle.youtube.LearningSpringboot.dto.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningSpringboot.dto.StudentDto;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface StudentService {
    
    List<StudentDto> getAllStudents();
    
    StudentDto getStudentById(Long id);
    
    @Nullable StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);
    
    void deleteStudentById(Long id);
    
    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);
    
    @Nullable StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
    
}
