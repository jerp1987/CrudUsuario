// UsuarioService.java
package com.example.service;

import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        boolean esNuevo = usuario.getId() == null;

        if (esNuevo) {
            if (usuarioRepository.existsByCorreoOrCedula(usuario.getCorreo(), usuario.getCedula())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo o la cédula ya están registrados.");
            }
        } else {
            if (usuarioRepository.existsByCorreoAndIdNot(usuario.getCorreo(), usuario.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está registrado por otro usuario.");
            }
            if (usuarioRepository.existsByCedulaAndIdNot(usuario.getCedula(), usuario.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "La cédula ya está registrada por otro usuario.");
            }
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con ID " + id + " no existe.");
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula);
    }
}




