package com.AVKGact3.demoCrud.controllers;

import com.AVKGact3.demoCrud.models.Alumno;
import com.AVKGact3.demoCrud.service.AlumnoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
    private final AlumnoService service;

    public AlumnoController(AlumnoService service) { this.service = service; }

    @GetMapping
    public List<Alumno> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Alumno obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    public Alumno crear(@RequestBody Alumno a, @RequestParam Long escuelaId) {
        return service.guardar(a, escuelaId);
    }

    @PutMapping("/{id}")
    public Alumno actualizar(@PathVariable Long id, @RequestBody Alumno a,
                             @RequestParam Long escuelaId) {
        return service.actualizar(id, a, escuelaId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}