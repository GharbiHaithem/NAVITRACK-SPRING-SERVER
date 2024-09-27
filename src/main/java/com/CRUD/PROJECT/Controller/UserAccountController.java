package com.CRUD.PROJECT.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Service.UserAccountService;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.User;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin(value = "*")
@RequestMapping("api/user")
public class UserAccountController {
  @Autowired
  public  UserAccountService userAccountService;

  @GetMapping("/users")
  public List<User> getAll() {
      return userAccountService.rechercher();
  }
  @PostMapping("/createSubUser/{parentId}")
  public ResponseEntity<Response> createSubUser(
          @PathVariable(name="parentId") String parentId, @RequestBody User user) {
            return  userAccountService.createSubUser(parentId, user.getFirstname(), user.getLastname(),user.getEmail(),  user.getPassword(), user.getAddress(), user.getRole(), user.getClient(),user.getNumPermis(),user.getPieceIdentite());
      
  }

  @PutMapping("/editSubUser/{id}")
  public ResponseEntity<Response> editSubUser (@PathVariable (name="id") String subuserId, @RequestBody User userData ) {
      
      return userAccountService.editSubUser(subuserId,userData) ;
  }
 
  @DeleteMapping("/delete/subuser/{id}")
  public ResponseEntity<Response> deleteSubUser(@PathVariable(name="id") String _id){
    return userAccountService.deleteSubUser(_id);
  }
 
 

}
