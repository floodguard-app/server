package com.floodguard.floodguard_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.model.UsuarioComum;
import com.floodguard.floodguard_server.repository.UsuarioComumRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UsuarioComumService {
    
    @Autowired
    private UsuarioComumRepository repository;

    public void criarUsuarioComum(Integer id) {
        UsuarioComum comum = new UsuarioComum(id);
        repository.save(comum);
    }

    // public void criarUsuarioComum(Usuario usuario) {
    //     UsuarioComum comum = new UsuarioComum(usuario);
    //     repository.save(comum);
    // }
}