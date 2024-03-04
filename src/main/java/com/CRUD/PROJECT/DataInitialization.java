package com.CRUD.PROJECT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.CRUD.PROJECT.Repo.CategoryRepo;
import com.CRUD.PROJECT.entities.Category;

@Component
public class DataInitialization implements CommandLineRunner {
@Autowired
    public CategoryRepo categoryRepo;

  
    public DataInitialization(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
       
        createDefaultCategories();
    }

    private void createDefaultCategories() {
        Category category1 = new Category(null, 0, "diagnostique");
        Category category2 = new Category(null, 0, "diagnostique GPS");
        Category category3 = new Category(null, 0 ,"diagnostique GPS CAM");
        Category category4 = new Category(null, 0, "diagnostique GPS CAM route");

       
        if (categoryRepo.findByName(category1.getName()) == null) {
            categoryRepo.save(category1);
        }
        if (categoryRepo.findByName(category2.getName()) == null) {
            categoryRepo.save(category2);
        }
        if (categoryRepo.findByName(category3.getName()) == null) {
            categoryRepo.save(category3);
        }
        if (categoryRepo.findByName(category4.getName()) == null) {
            categoryRepo.save(category4);
        }
    }
}
