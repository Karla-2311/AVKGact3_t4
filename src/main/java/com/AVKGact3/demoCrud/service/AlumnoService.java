package com.AVKGact3.demoCrud.service;

import com.AVKGact3.demoCrud.models.Alumno;
import com.AVKGact3.demoCrud.repository.AlumnoRepository;
import com.AVKGact3.demoCrud.repository.EscuelaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlumnoService {
    private final AlumnoRepository repo;
    private final EscuelaRepository escuelaRepo;

    public AlumnoService(AlumnoRepository repo, EscuelaRepository escuelaRepo) {
        this.repo = repo;
        this.escuelaRepo = escuelaRepo;
    }

    public List<Alumno> listar() { return repo.findAll(); }

    public Alumno obtener(Long id) { return repo.findById(id).orElseThrow(); }

    public Alumno guardar(Alumno a, Long escuelaId) {
        a.setEscuela(escuelaRepo.findById(escuelaId).orElseThrow());
        return repo.save(a);
    }

    public Alumno actualizar(Long id, Alumno datos, Long escuelaId) {
        Alumno a = obtener(id);
        a.setNombre(datos.getNombre());
        a.setMatricula(datos.getMatricula());
        a.setEscuela(escuelaRepo.findById(escuelaId).orElseThrow());
        return repo.save(a);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}