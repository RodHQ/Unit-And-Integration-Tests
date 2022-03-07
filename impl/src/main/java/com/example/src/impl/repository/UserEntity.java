package com.example.src.impl.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Document(collection = "User")
public class UserEntity {

    @Id
    private String id;
    private String userName;
}