package com.floodguard.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCadastroDTO {
    // Atributos
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    public String email;

    @NotBlank 
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;  }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
} 