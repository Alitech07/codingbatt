package restapi.codingbat.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.codingbat.entity.Problems;
import restapi.codingbat.payload.ProblemsDto;
import restapi.codingbat.payload.Result;
import restapi.codingbat.service.ProblemsService;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemsController {
    @Autowired
    ProblemsService problemsService;

    @GetMapping
    public HttpEntity<?> getProblems(){
        List<Problems> problems = problemsService.getProblemsService();
        return ResponseEntity.status(!problems.isEmpty()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(problems);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getProblem(@PathVariable Integer id){
        Problems problems = problemsService.getProblemService(id);
        return ResponseEntity.status(problems!=null?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(problems);
    }
    @PostMapping("/add")
    public HttpEntity<?> addProblem(@RequestBody ProblemsDto problemsDto){
        Result result = problemsService.addProblemsService(problemsDto);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editProblem(@RequestBody ProblemsDto problemsDto,@PathVariable Integer id){
        Result result = problemsService.editProblemsService(problemsDto, id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProblem(@PathVariable Integer id){
        Result result = problemsService.deleteProblemsService(id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
}
