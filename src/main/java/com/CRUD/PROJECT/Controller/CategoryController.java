package com.CRUD.PROJECT.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.PROJECT.Repo.CategoryRepo;
import com.CRUD.PROJECT.entities.Category;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/category")
public class CategoryController {
@Autowired
	public CategoryRepo categoryRepo ;

  @Operation(hidden = true) 
@GetMapping("/allcats")
public List<Category> getAllCategory(){
	return  categoryRepo.findAll();
	
}
@Operation(hidden = true) 
@GetMapping("/test")
public  String getMessage() {
	return "TEST MESSAGE";
}
}
