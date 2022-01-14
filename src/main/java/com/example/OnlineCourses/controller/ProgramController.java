package com.example.OnlineCourses.controller;

import com.example.OnlineCourses.domain.model.Course;
import com.example.OnlineCourses.domain.model.Program;
import com.example.OnlineCourses.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/getall")
    public ResponseEntity<List<Program>> getAllPrograms(){
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Program> getProgramByName(@PathVariable String name){
        return ResponseEntity.ok(programService.findByName(name));
    }

    @PostMapping("/insert")
    public ResponseEntity<Program> insertProgram(@RequestBody Program program){
        return ResponseEntity.ok(programService.insertProgram(program));
    }

    @PostMapping("/course/{name}")
    public ResponseEntity<Program> insertCourse(@PathVariable String name,@RequestBody Course course){
        return ResponseEntity.ok(programService.insertCourse(name, course));
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Program> updateProgram(@RequestBody Program program, @PathVariable String name){
        return ResponseEntity.ok(programService.updateProgram(program, name));
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
//    @PostMapping("/graphql")
//    public ResponseEntity<Object> getAllGraphQLPrograms(@RequestBody Map<String, Object> query){
//        return ResponseEntity.ok(programService.graphQLGetAllPrograms((String) query.get("query")));
//    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteProgram(@PathVariable String name){
        return ResponseEntity.ok(programService.deleteProgramByName(name));
    }
}
