package org.nestordevelopments.lapurisima.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestor on 1/10/17.
 */

public class Curso {

    public static final List<Curso> ITEMS = new ArrayList<Curso>();
    private String curso;

    private static final int COUNT = 25;

    static {
        addItem(createCursoItem("1º ESO A"));
        addItem(createCursoItem("1º ESO B"));
        addItem(createCursoItem("1º ESO C"));
        addItem(createCursoItem("2º ESO A"));
        addItem(createCursoItem("2º ESO B"));
        addItem(createCursoItem("2º ESO C"));
        addItem(createCursoItem("3º ESO A"));
        addItem(createCursoItem("3º ESO B"));
        addItem(createCursoItem("3º ESO C"));
        addItem(createCursoItem("4º ESO A"));
        addItem(createCursoItem("4º ESO B"));
        addItem(createCursoItem("4º ESO C"));
        addItem(createCursoItem("4º ESO D"));

    }

    public Curso(String nombreCurso) {
        this.curso = nombreCurso;
    }

    public String getCurso(){
        return this.curso;
    }

    @Override
    public String toString() {
        return curso;
    }

    private static void addItem(Curso item) {
        ITEMS.add(item);
    }

    private static Curso createCursoItem(String curso) {
        return new Curso(curso);
    }

}
