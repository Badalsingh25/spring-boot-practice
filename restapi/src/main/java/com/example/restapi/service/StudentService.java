package com.example.restapi.service;

import com.example.restapi.dto.AddStudentRequestDto;
import com.example.restapi.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);
    List <StudentDto> getAllStudents();
    StudentDto getStudentById(Long id) throws IllegalAccessException;

    String deleteById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) throws IllegalAccessException;

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates) throws IllegalAccessException;
}
