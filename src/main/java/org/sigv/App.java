package org.sigv;

import org.sigv.model.TipoUsuario;
import org.sigv.model.Usuario;
import org.sigv.repository.UsuarioRepository;

public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicación...");

        UsuarioRepository usuarioRepository = new UsuarioRepository();

        // Crear y guardar usuario
        Usuario usuario = new Usuario("juan123", "Juan Pérez", "miContrasenaSegura", TipoUsuario.MEDICO);
        usuarioRepository.guardarUsuario(usuario);

        // Parámetros de búsqueda
        String usuarioBuscado = "juan123";
        String contrasenaBuscada = "miContrasenaSegura";
        TipoUsuario tipoBuscado = TipoUsuario.MEDICO;

        // Consultar usuario
        Usuario usuarioConsultado = usuarioRepository.buscarUsuario(usuarioBuscado, contrasenaBuscada, tipoBuscado);

        // Verificar si el usuario fue encontrado
        if (usuarioConsultado != null) {
            System.out.println("Usuario encontrado: " + usuarioConsultado.getNombre());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}