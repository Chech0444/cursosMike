package com.cursosonline.controller;

import com.cursosonline.entity.Curso;
import com.cursosonline.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public Curso crear(@RequestBody Curso c) { return service.crear(c); }

    @GetMapping
    public List<Curso> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Curso obtener(@PathVariable Long id) { return service.obtener(id); }

    @PutMapping("/{id}")
    public Curso editar(@PathVariable Long id, @RequestBody Curso c) {
        return service.editar(id, c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
