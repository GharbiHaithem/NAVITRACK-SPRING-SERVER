package com.CRUD.PROJECT.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.ArchiveSubUserRepo;
import com.CRUD.PROJECT.Repo.UserAccountRepo;
import com.CRUD.PROJECT.entities.ArchiveSubUser;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.User;

import ch.qos.logback.classic.Logger;

@Service
public class UserAccountService {
  private static final Logger logger = (Logger) LoggerFactory.getLogger(UserAccountService.class);

  @Autowired
  private UserAccountRepo userAccountRepo;
  
  @Autowired
 private ArchiveSubUserRepo archiveSubUserRepo;
public List<User> rechercher() {
    try {
        List<User> result = userAccountRepo.findAll();
        System.out.println("Résultat de la recherche : " + result);
        return result;
    } catch (Exception e) {
        // Gérer l'exception de manière appropriée (imprimer des logs, renvoyer une réponse d'erreur, etc.)
        e.printStackTrace();
        return Collections.emptyList(); // Ou renvoyer une liste vide, selon le cas.
    }
}

  public ResponseEntity<Response> createSubUser(String parentId, String firstname, String lastname,String email, String password, String address, String role,
          String client , String NumPermis, String PieceIdentite) {
Optional<User> parentUserOptional = userAccountRepo.findById(parentId);
System.out.println(parentUserOptional);
if (parentUserOptional.isPresent()) {
User parentUser = parentUserOptional.get();


if (parentUser.getSubUsers() == null) {
parentUser.setSubUsers(new ArrayList<>());
}

User subUser = new User();
subUser.setLastname(lastname);
subUser.setFirstname(firstname);
subUser.setEmail(email);
BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
String passwordHashed =  bcrypt.encode(password);
subUser.setPasswordClaire(password);
subUser.setPassword(passwordHashed);
subUser.setRole(role);
subUser.setClient(client);
subUser.setNumPermis(NumPermis);
subUser.setPieceIdentite(PieceIdentite);
userAccountRepo.save(subUser);

parentUser.getSubUsers().add(subUser);
 User createsubuser =   userAccountRepo.save(parentUser);
 return ResponseEntity.ok(new Response("account created ya imtinen enfin", null, null, null, null, createsubuser));
}
return null;

}

public ResponseEntity<Response> editSubUser(String subuserId, User userData) {
  Optional<User> findsubUser = userAccountRepo.findById(subuserId);
  System.out.println(findsubUser);
  List<User> allUsers = userAccountRepo.findAll();
boolean isUserIncludedInOtherUsers = false;



System.out.println("L'utilisateur est inclus dans d'autres utilisateurs : " + isUserIncludedInOtherUsers);
  if (findsubUser.isPresent()) {
     
    
          // Faire quelque chose avec chaque utilisateur (par exemple, afficher le nom)
       
              System.out.println("un update");
              User userUpdate = findsubUser.get();
              userUpdate.setAddress(userData.getAddress());
              userUpdate.setEmail(userData.getEmail());
              userUpdate.setFirstname(userData.getFirstname());
              userUpdate.setLastname(userData.getLastname());
              userUpdate.setRole(null);
              String password = userData.getPassword();
              System.out.println(password);
              BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
              String passwordHashed = bcrypt.encode(password);
              userUpdate.setPassword(passwordHashed);
              userUpdate.setPasswordClaire(password);
              User savedSubUserEdit = userAccountRepo.save(userUpdate);
              return ResponseEntity.ok(new Response("sub user edited success", null, null, null, null, savedSubUserEdit));
          

    
  }

  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("sub user not fund", null, null, null, null));
}

public ResponseEntity<Response> deleteSubUser(String _id) {
    Optional<User> findUserOptional = userAccountRepo.findById(_id);

    if (findUserOptional.isPresent()) {
        User deletedUser = findUserOptional.get();

        // Archiver le sous-utilisateur
        ArchiveSubUser archiveSubUser = new ArchiveSubUser();
        archiveSubUser.setAddress(deletedUser.getAddress());
        archiveSubUser.setEmail(deletedUser.getEmail());
        archiveSubUser.setFirstname(deletedUser.getFirstname());
        archiveSubUser.setLastname(deletedUser.getLastname());
        archiveSubUser.setPasswordClaire(deletedUser.getPasswordClaire());
        archiveSubUser.setRole(deletedUser.getRole());
        archiveSubUserRepo.save(archiveSubUser);

        // Trouver tous les utilisateurs
        List<User> allUsers = userAccountRepo.findAll();

        for (User currentUser : allUsers) {
            // Vérifiez si la liste des sous-utilisateurs est null
            if (currentUser.getSubUsers() != null) {
                // Recherche de l'utilisateur dans la liste des sous-utilisateurs du parent
                Optional<User> subUserOptional = currentUser.getSubUsers().stream()
                        .filter(subUser -> subUser.get_id().equals(_id))
                        .findFirst();
    
                if (subUserOptional.isPresent()) {
                    User subUser = subUserOptional.get();
    
                    // Supprimer le sous-utilisateur de la liste des sous-utilisateurs du parent
                    currentUser.getSubUsers().remove(subUser);
    
                    // Supprimer le sous-utilisateur de la collection distincte des utilisateurs
                    userAccountRepo.deleteById(_id);
    
                    // Enregistrez la modification du parent dans la base de données
                    userAccountRepo.save(currentUser);
    
                    // Ajoutez ici le reste de la logique de réponse appropriée
                    return ResponseEntity.ok(new Response("Subuser deleted successfully", null, null, null, null));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Subuser not found", null, null, null, null));
    }
    

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new Response("Utilisateur ou sous-utilisateur non trouvé", null, null, null, null));
}




}
