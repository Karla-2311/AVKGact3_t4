package com.AVKGact3.demoCrud.controllers;

import com.AVKGact3.demoCrud.models.Escuela;
import com.AVKGact3.demoCrud.service.EscuelaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/escuelas")
public class EscuelaController {
    private final EscuelaService service;

    public EscuelaController(EscuelaService service) { this.service = service; }

    @GetMapping
    public List<Escuela> listar() { return service.listar(); }

    @PostMapping
    public Escuela crear(@RequestBody Escuela e) { return service.guardar(e); }
}