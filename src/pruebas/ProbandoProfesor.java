package pruebas;

import dao.HibernateUtil;
import dao.ProfesorDao;
import java.util.Scanner;
import model.Profesor;

public class ProbandoProfesor {
    
    public static void main(String[] args) {
        // INSTANCIAR OBJETOS PROFESOR
        Profesor p1 = new Profesor(1, "Juan", "Perez", "Gomez");
        Profesor p2 = new Profesor(2, "Maria", "Torrez", "Aliaga");
        Profesor p3 = new Profesor(3, "Pedro", "Romero", "Sanchez");
        Profesor p4 = new Profesor(4, "Ana", "Perales", "Balboa");
        // INSTANCIAR OBJETO DAO
        ProfesorDao pdao = new ProfesorDao();
        // PERESISTIR OBJETOS EN LA BASE DE DATOS
        pdao.add(p1);
        pdao.add(p2);
        pdao.add(p3);
        pdao.add(p4);
        // RECUPERAR EL LISTADO DE PROFESORES
        System.out.println("LISTA DE PROFESORES");
        for (Profesor p : pdao.getAllProfesores()) {
            System.out.println(p.getId() + "-" + p.getNombre());
        }
        // BUSCAR UN PROFESOR
        System.out.println("DETALLE PROFESOR");
        System.out.print("Ingrese un id: ");
        Scanner t = new Scanner(System.in);
        int id = t.nextInt();
        Profesor p = pdao.getProfesorById(id);
        if (p != null) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Paterno: " + p.getPaterno());
            System.out.println("Materno: " + p.getMaterno());
        } else {
            System.out.println("El profesor no existe...");
        }
        // EDITAR UN PROFESOR
        System.out.print("Ingrese un nuevo Nombre: ");
        String nombre = t.next();
        p.setNombre(nombre);
        pdao.update(p);
        System.out.println("El profesor fue modificado...");
        // ELIMINAR UN PROFESOR
        System.out.print("Ingrese ID de profesor a eliminar:");
        p.setId(t.nextInt());
        pdao.delete(p);
        System.out.println("El profesor fue eliminado...");
        HibernateUtil.closeSessionFactory();
    }

}
