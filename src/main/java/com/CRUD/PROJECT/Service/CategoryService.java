package com.CRUD.PROJECT.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.CategoryRepo;
import com.CRUD.PROJECT.entities.Category;
@Service
public class CategoryService {
@Autowired
private CategoryRepo categoryRepo;
    public ResponseEntity<Response> updateStck(Category dataCategory) {
        int newQtyStck  = dataCategory.getQtyStock();
       String catId = dataCategory.getId();
         Optional<Category> findCat = categoryRepo.findById(catId);
         if (findCat.isPresent()) {
          Category existingCategory = findCat.get();
          int qtyStckCat = existingCategory.getQtyStock();
          
          // Mise à jour du stock
          existingCategory.setQtyStock(newQtyStck + qtyStckCat);
  
          // Sauvegarde de la Category mise à jour
          categoryRepo.save(existingCategory);
  
          Response response = new Response("Category updated successfully", null, null, null, null, null);
          return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
      }
         Response response = new Response("Category not exist", null, null, null, null, null);
         return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
