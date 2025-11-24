package com.cursosonline.service;

import com.cursosonline.entity.Categoria;
import com.cursosonline.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria crear(Categoria c) {
        if (c.getNombre() == null || c.getNombre().isBlank())
            throw new RuntimeException("El nombre es obligatorio");

        if (repo.existsByNombre(c.getNombre()))
            throw new RuntimeException("La categoría ya existe");

        return repo.save(c);
    }

    public List<Categoria> listar() { return repo.findAll(); }

    public Categoria obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Categoria editar(Long id, Categoria data) {
        Categoria c = obtener(id);
        c.setNombre(data.getNombre());
        return repo.save(c);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}
