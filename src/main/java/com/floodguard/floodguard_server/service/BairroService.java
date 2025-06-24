package com.floodguard.floodguard_server.service;

import com.floodguard.floodguard_server.dto.BairroDTO;
import com.floodguard.floodguard_server.model.Bairro;
import com.floodguard.floodguard_server.repository.BairroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {
    private final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    public List<BairroDTO> listarBairros() {
        List<Bairro> bairros = bairroRepository.findAllByOrderByNomeBairroAsc();
        return bairros.stream()
            .map(bairro -> new BairroDTO(
                bairro.getId(),
                bairro.getNomeBairro()
            ))
            .collect(Collectors.toList());
    }
}