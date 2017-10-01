package org.nestordevelopments.lapurisima.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestor on 24/05/2016.
 */
public class Alumno {

    public static List<Alumno> ITEMSalumnos = new ArrayList<>();

    private String nombre;
    private String apellidos;
    private String curso;
    private boolean isSelected;

    public Alumno(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Alumno(String nombre, String apellidos, String curso){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.curso=curso;
    }

    public Alumno(String nombre, String apellidos, String curso, boolean isSelected){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.curso=curso;
        this.isSelected=isSelected;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return this.apellidos+", "+this.nombre;
    }

    private static void addItem(Alumno alumno, int curso) {
        ITEMSalumnos.add(alumno);
    }

    static{
        /*
        for(int i=0;i<ITEMS.size();i++) {
            addItem(createAlumnoItem(new Alumno("Alumno "+i, "Apellido "+i)));
        }
        */
        // Volcamos datos de la BD al array ITEMS

    }
}
