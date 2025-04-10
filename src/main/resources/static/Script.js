document.addEventListener("DOMContentLoaded", function () {
    cargarUsuarios();

    document.getElementById("usuarioForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const id = document.getElementById("id").value;
        if (id) {
            actualizarUsuario(); // Si hay ID, actualizar
        } else {
            agregarUsuario(); // Si no hay ID, agregar
        }
    });

    document.getElementById("actualizarUsuario").addEventListener("click", function () {
        actualizarUsuario();
    });
});

function cargarUsuarios() {
    fetch('/api/usuarios')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById("usuariosTableBody");
            tbody.innerHTML = "";

            data.sort((a, b) => a.id - b.id);

            data.forEach(usuario => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${usuario.id}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.apellido}</td>
                    <td>${usuario.correo}</td>
                    <td>${usuario.cedula}</td>
                    <td>
                        <button class="editar" onclick="editarUsuario(${usuario.id})">✏️ Editar</button>
                        <button class="eliminar" onclick="eliminarUsuario(${usuario.id})">🗑️ Eliminar</button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(error => console.error("Error al cargar usuarios:", error));
}

function agregarUsuario() {
    const usuario = obtenerDatosFormulario();

    fetch('/api/usuarios', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text); });
        }
        return response.json();
    })
    .then(() => {
        alert("✅ Usuario agregado correctamente.");
        resetFormulario();
        cargarUsuarios();
    })
    .catch(error => {
        console.error("Error al agregar usuario:", error);
        alert("⚠️ Error al agregar usuario: " + error.message);
    });
}

function editarUsuario(id) {
    fetch(`/api/usuarios/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error al obtener usuario: ${response.status}`);
            }
            return response.json();
        })
        .then(usuario => {
            document.getElementById("id").value = usuario.id || "";
            document.getElementById("nombre").value = usuario.nombre || "";
            document.getElementById("apellido").value = usuario.apellido || "";
            document.getElementById("correo").value = usuario.correo || "";
            document.getElementById("cedula").value = usuario.cedula || "";

            document.getElementById("guardarUsuario").style.display = "none";
            document.getElementById("actualizarUsuario").style.display = "inline-block";
        })
        .catch(error => {
            console.error("Error al obtener usuario:", error);
            alert("❌ No se pudo cargar el usuario: " + error.message);
        });
}

function actualizarUsuario() {
    const id = document.getElementById("id").value;
    if (!id) {
        alert("⚠️ No se ha seleccionado un usuario para actualizar.");
        return;
    }

    const usuarioActualizado = obtenerDatosFormulario();

    fetch(`/api/usuarios/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuarioActualizado)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => {
                if (response.status === 409) {
                    throw new Error("⚠️ Conflicto: " + text);
                }
                throw new Error("❌ Error: " + text);
            });
        }
        return response.json();
    })
    .then(() => {
        alert("✅ Usuario actualizado correctamente.");
        resetFormulario();
        cargarUsuarios();
    })
    .catch(error => {
        console.error("Error al actualizar usuario:", error);
        alert("⚠️ " + error.message);
    });
}

function eliminarUsuario(id) {
    if (confirm("¿Estás seguro de que deseas eliminar este usuario?")) {
        fetch(`/api/usuarios/${id}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) {
                throw new Error("❌ No se pudo eliminar el usuario.");
            }
            alert("🗑️ Usuario eliminado correctamente.");
            cargarUsuarios();
        })
        .catch(error => {
            console.error("Error al eliminar usuario:", error);
            alert("⚠️ Error al eliminar usuario: " + error.message);
        });
    }
}

function obtenerDatosFormulario() {
    return {
        nombre: document.getElementById("nombre").value.trim(),
        apellido: document.getElementById("apellido").value.trim(),
        correo: document.getElementById("correo").value.trim(),
        cedula: document.getElementById("cedula").value.trim()
    };
}

function resetFormulario() {
    document.getElementById("usuarioForm").reset();
    document.getElementById("id").value = "";
    document.getElementById("guardarUsuario").style.display = "inline-block";
    document.getElementById("actualizarUsuario").style.display = "none";
}
 



















