package br.com.aviapp.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserCredentialsDTO {
    
    private Long clientId;
    private String username;
    private String password;
}
