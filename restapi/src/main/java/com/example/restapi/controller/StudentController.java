package com.example.restapi.controller;

import com.example.restapi.dto.AddStudentRequestDto;
import com.example.restapi.dto.StudentDto;
import com.example.restapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
//            return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) throws IllegalAccessException {
            return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body( studentService.createNewStudent(addStudentRequestDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody @Valid AddStudentRequestDto addStudentRequestDto)
            throws IllegalAccessException {
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody @Valid Map<String, Object> updates)
            throws IllegalAccessException {
        return ResponseEntity.ok(studentService.updatePartialStudent(id,updates));
    }

}
