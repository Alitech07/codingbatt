package restapi.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.codingbat.entity.Category;
import restapi.codingbat.payload.CategoryDto;
import restapi.codingbat.payload.Result;
import restapi.codingbat.service.CategoryService;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping
    public HttpEntity<?> getCategories(){
        List<Category> categories = categoryService.getCategoriesService();
        return ResponseEntity.status(!categories.isEmpty()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(categories);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getCategory(@PathVariable Integer id){
        Category category = categoryService.getCategoryService(id);
        return ResponseEntity.status(category!=null?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(category);
    }
    @PostMapping("/add")
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategoryService(categoryDto);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategoryService(categoryDto, id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategoryService(id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
}
