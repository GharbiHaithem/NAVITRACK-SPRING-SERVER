package com.CRUD.PROJECT.entities;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 @AllArgsConstructor 
@Document(collection = "roles")
public class Role {
      @Id
      private String  id;
      private ERole name;
      public Role (){}
}
