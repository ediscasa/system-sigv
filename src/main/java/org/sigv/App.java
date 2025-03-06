package org.sigv;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sigv.config.HibernateUtil;
import org.sigv.model.TipoUsuario;
import org.sigv.model.Usuario;


public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicación...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Insertar un usuarioConsultado de prueba
        Usuario usuario = new Usuario(
                "juan123",
                "Juan Pérez",
                "miContrasenaSegura",
                TipoUsuario.MEDICO
        );

        session.persist(usuario);

        transaction.commit();
        System.out.println("Usuario guardado exitosamente.");

        // Parámetros de búsqueda
        String usuarioBuscado = "juan123";
        String contrasenaBuscada = "miContrasenaSegura";
        TipoUsuario tipoBuscado = TipoUsuario.MEDICO;


        // Consulta con HQL (Hibernate Query Language)

        String hql = "FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = :contrasena AND u.tipoUsuario = :tipo";
        Query<Usuario> query = session.createQuery(hql, Usuario.class);
        query.setParameter("usuario", usuarioBuscado);
        query.setParameter("contrasena", contrasenaBuscada);
        query.setParameter("tipo", tipoBuscado);

        // Obtener el resultado
        Usuario usuarioConsultado = query.uniqueResult(); // Devuelve un solo resultado o null

        // Verificar si encontró un usuarioConsultado
        if (usuarioConsultado != null) {
            System.out.println("Usuario encontrado: " + usuarioConsultado.getNombre());
        } else {
            System.out.println("Usuario no encontrado.");
        }

        session.close();

    }
}
