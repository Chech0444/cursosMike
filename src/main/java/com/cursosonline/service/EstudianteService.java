package com.cursosonline.service;

import com.cursosonline.entity.Curso;
import com.cursosonline.entity.Estudiante;
import com.cursosonline.repository.CursoRepository;
import com.cursosonline.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repo;

    @Autowired
    private CursoRepository cursoRepo;

    public Estudiante crear(Estudiante e) {

        if (e.getNombre() == null || e.getNombre().isBlank())
            throw new RuntimeException("Nombre obligatorio");

        if (e.getEmail() == null || e.getEmail().isBlank())
            throw new RuntimeException("Email obligatorio");

        if (repo.existsByEmail(e.getEmail()))
            throw new RuntimeException("Email ya registrado");

        Set<Curso> cursosValidados = new HashSet<>();

        for (Curso c : e.getCursos()) {
            Curso cursoDB = cursoRepo.findById(c.getId())
                .orElseThrow(() -> new RuntimeException("Curso no existe: " + c.getId()));
            cursosValidados.add(cursoDB);
        }

        e.setCursos(cursosValidados);

        return repo.save(e);
    }

    public List<Estudiante> listar() { return repo.findAll(); }

    public Estudiante obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public Estudiante editar(Long id, Estudiante data) {
        Estudiante e = obtener(id);
        e.setNombre(data.getNombre());
        e.setEmail(data.getEmail());

        Set<Curso> cursos = new HashSet<>();
        for (Curso c : data.getCursos()) {
            cursos.add(cursoRepo.findById(c.getId())
              .orElseThrow(() -> new RuntimeException("Curso no existe: " + c.getId())));
        }

        e.setCursos(cursos);
        return repo.save(e);
    }

    public void eliminar(Long id) { repo.deleteById(id); }

    public Set<Curso> cursosDeEstudiante(Long id) {
        return obtener(id).getCursos();
    }
}
