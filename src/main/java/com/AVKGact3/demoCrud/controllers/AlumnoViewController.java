package com.AVKGact3.demoCrud.controllers;

import com.AVKGact3.demoCrud.models.Alumno;
import com.AVKGact3.demoCrud.service.AlumnoService;
import com.AVKGact3.demoCrud.service.EscuelaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumnos")
public class AlumnoViewController {
    private final AlumnoService alumnos;
    private final EscuelaService escuelas;

    public AlumnoViewController(AlumnoService alumnos, EscuelaService escuelas) {
        this.alumnos = alumnos;
        this.escuelas = escuelas;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("alumnos", alumnos.listar());
        return "alumnos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("escuelas", escuelas.listar());
        return "alumnos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("alumno", alumnos.obtener(id));
        model.addAttribute("escuelas", escuelas.listar());
        return "alumnos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Alumno alumno, @RequestParam Long escuelaId) {
        if (alumno.getId() == null) alumnos.guardar(alumno, escuelaId);
        else alumnos.actualizar(alumno.getId(), alumno, escuelaId);
        return "redirect:/alumnos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        alumnos.eliminar(id);
        return "redirect:/alumnos";
    }
}