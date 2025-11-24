package com.cursosonline.controller;

import com.cursosonline.entity.Curso;
import com.cursosonline.entity.Estudiante;
import com.cursosonline.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante e) { return service.crear(e); }

    @GetMapping
    public List<Estudiante> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Estudiante obtener(@PathVariable Long id) { return service.obtener(id); }

    @PutMapping("/{id}")
    public Estudiante editar(@PathVariable Long id, @RequestBody Estudiante e) {
        return service.editar(id, e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }

    @GetMapping("/{id}/cursos")
    public Set<Curso> cursos(@PathVariable Long id) {
        return service.cursosDeEstudiante(id);
    }
}
