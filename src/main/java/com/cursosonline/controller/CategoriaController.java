package com.cursosonline.controller;

import com.cursosonline.entity.Categoria;
import com.cursosonline.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public Categoria crear(@RequestBody Categoria c) { return service.crear(c); }

    @GetMapping
    public List<Categoria> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Long id) { return service.obtener(id); }

    @PutMapping("/{id}")
    public Categoria editar(@PathVariable Long id, @RequestBody Categoria c) {
        return service.editar(id, c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
