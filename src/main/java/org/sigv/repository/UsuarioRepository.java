package org.sigv.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sigv.config.HibernateUtil;
import org.sigv.model.TipoUsuario;
import org.sigv.model.Usuario;

public class UsuarioRepository {

    // Método para guardar un usuario en la BD
    public void guardarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
            System.out.println("Usuario guardado exitosamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para consultar si un usuario existe
    public Usuario buscarUsuario(String usuario, String contrasena, TipoUsuario tipoUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = :contrasena AND u.tipoUsuario = :tipo";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("usuario", usuario);
            query.setParameter("contrasena", contrasena);
            query.setParameter("tipo", tipoUsuario);

            return query.uniqueResult(); // Devuelve el usuario o null si no existe
        }
    }
}