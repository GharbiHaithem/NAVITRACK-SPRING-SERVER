package com.CRUD.PROJECT.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.CategoryRepo;
import com.CRUD.PROJECT.Service.CategoryService;
import com.CRUD.PROJECT.entities.Category;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*,https://navitrack-dashbord.onrender.com/")
@RequestMapping("api/category")
public class CategoryController {
@Autowired
	public CategoryRepo categoryRepo ;
	@Autowired
   public CategoryService categoryService; 
  @Operation(hidden = true) 
@GetMapping("/allcats")
public List<Category> getAllCategory(){
	return   categoryRepo.findAll();
	
}
@Operation(hidden = true) 
@GetMapping("/test")
public  String getMessage() {
	return "TEST MESSAGE";
}

@PutMapping("/update-stck/{id}")
public ResponseEntity<Response> updateStk(@PathVariable(name="id") String id, @RequestBody Category dataCategory ) {
    return categoryService.updateStck(dataCategory);
}
}
