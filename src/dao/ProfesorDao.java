package dao;

import java.util.List;
import model.Profesor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProfesorDao {

    private Session session; // Mantiene la referencia de la sesión a la BD
    private Transaction tx;  // Mantiene la referencia a la transacción actual
    // Inicia una sesión y una transacción a la base de datos
    private void iniciaOperacion() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    public void add(Profesor profesor) {
        try {
            iniciaOperacion();
            session.save(profesor);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            System.out.println("Error al guardar: " + ex);
        } finally {
            session.close();
        }
    }
    
    public void update(Profesor profesor) {
        try {
            iniciaOperacion();
            session.update(profesor);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            System.out.println("Error al actualizar: " + ex);
        } finally {
            session.close();
        }
    }
    
    public void delete(Profesor profesor){
        try {
            iniciaOperacion();
            session.delete(profesor);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            System.out.println("Error al actualizar: " + ex);
        } finally {
            session.close();
        }
    }

    public Profesor getProfesorById(int id){
        Profesor profesor=null;
        try {
            iniciaOperacion();
            profesor=(Profesor)session.get(Profesor.class, id);
        } catch (HibernateException ex) {
            System.out.println("Error al recuperar un profesor: " + ex);
        } finally {
            session.close();
        }
        return profesor;
    }
    
    public List<Profesor> getAllProfesores(){
        List<Profesor> profesores=null;
        try {
            iniciaOperacion();
            profesores=session.createQuery("from Profesor").list();
        } catch (HibernateException ex) {
            System.out.println("Error al recuperar varios profesores: " + ex);
        } finally {
            session.close();
        }
        return profesores;
    }
}
