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
@CrossOrigin(origins = "*")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/getall")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Program>> getAllPrograms(){
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    @GetMapping("/{name}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Program> getProgramByName(@PathVariable String name){
        return ResponseEntity.ok(programService.findByName(name));
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Program> insertProgram(@RequestBody Program program){
        return ResponseEntity.ok(programService.insertProgram(program));
    }

    @PostMapping("/course/{courseName}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Program> insertCourse(@PathVariable String courseName,@RequestBody Course course){
        return ResponseEntity.ok(programService.insertCourse(courseName, course));
    }

    @PutMapping("/update/{name}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Program> updateProgram(@RequestBody Program program, @PathVariable String name){
        return ResponseEntity.ok(programService.updateProgram(program, name));
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/graphql")
    public ResponseEntity<Object> getAllGraphQLPrograms(@RequestBody Map<String, Object> query){
        return ResponseEntity.ok(programService.graphQLGetAllPrograms((String) query.get("query")));
    }

    @DeleteMapping("/delete/{name}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProgram(@PathVariable String name){
        return ResponseEntity.ok(programService.deleteProgramByName(name));
    }
}
