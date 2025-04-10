// UsuarioRepository.java
package com.example.repository;

import com.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCorreoAndIdNot(String correo, Long id);

    boolean existsByCedulaAndIdNot(String cedula, Long id);

    boolean existsByCorreoOrCedula(String correo, String cedula);

    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findByCedula(String cedula);
}



