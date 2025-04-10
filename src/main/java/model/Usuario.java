package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "usuarios", indexes = {
    @Index(name = "idx_correo", columnList = "correo"),
    @Index(name = "idx_cedula", columnList = "cedula")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    // ID generado automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    // Nombre del usuario
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    // Apellido del usuario
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede exceder 50 caracteres")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    // Correo electrónico (único)
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe proporcionar un correo válido")
    @Column(name = "correo", unique = true, nullable = false, length = 100)
    private String correo;

    // Cédula (solo números, única)
    @NotBlank(message = "La cédula no puede estar vacía")
    @Pattern(regexp = "\\d+", message = "La cédula solo puede contener números")
    @Size(min = 6, max = 20, message = "La cédula debe tener entre 6 y 20 caracteres")
    @Column(name = "cedula", unique = true, nullable = false, length = 20)
    private String cedula;

    // Constructor vacío (requerido por JPA)
    public Usuario() {
    }

    // Constructor sin ID
    public Usuario(String nombre, String apellido, String correo, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
    }

    // Constructor completo (opcional)
    public Usuario(Long id, String nombre, String apellido, String correo, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", correo='" + correo + '\'' +
               ", cedula='" + cedula + '\'' +
               '}';
    }
}



