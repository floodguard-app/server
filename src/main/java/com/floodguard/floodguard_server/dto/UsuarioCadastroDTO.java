package com.floodguard.floodguard_server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioCadastroDTO {
    // @NotBlank(message = "Username é obrigatório")
    public String username = "defaultUser";

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    public String email;

    @NotBlank(message = "Password é obrigatório")
    public String password;
}