package com.AVKGact3.demoCrud.service;

import com.AVKGact3.demoCrud.models.Escuela;
import com.AVKGact3.demoCrud.repository.EscuelaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EscuelaService {
    private final EscuelaRepository repo;

    public EscuelaService(EscuelaRepository repo) { this.repo = repo; }

    public List<Escuela> listar() { return repo.findAll(); }
    public Escuela guardar(Escuela e) { return repo.save(e); }
}