package org.nestordevelopments.lapurisima.dummy;

import org.nestordevelopments.lapurisima.Modelo.Alumno;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class AlumnoContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<AlumnoItem> ITEMS = new ArrayList<>();

    private static void addItem(AlumnoItem item) {
        ITEMS.add(item);
    }

    private static AlumnoItem createCursoItem(Alumno alumno) {
        return new AlumnoItem(alumno);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class AlumnoItem {
        public final Alumno alumno;

        public AlumnoItem(Alumno content) {
            this.alumno = content;
        }

        @Override
        public String toString() {
            return alumno.toString();
        }
    }
}
