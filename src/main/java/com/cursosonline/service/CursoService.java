package com.cursosonline.service;

import com.cursosonline.entity.Categoria;
import com.cursosonline.entity.Curso;
import com.cursosonline.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repo;

    @Autowired
    private CategoriaService categoriaService;

    public Curso crear(Curso curso) {

        if (curso.getCategoria() == null)
            throw new RuntimeException("Debe asignarse una categoría");

        Categoria c = categoriaService.obtener(curso.getCategoria().getId());
        curso.setCategoria(c);

        return repo.save(curso);
    }

    public List<Curso> listar() { return repo.findAll(); }

    public Curso obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Curso editar(Long id, Curso data) {
        Curso c = obtener(id);
        c.setTitulo(data.getTitulo());
        c.setDescripcion(data.getDescripcion());
        c.setCategoria(categoriaService.obtener(data.getCategoria().getId()));
        return repo.save(c);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}
