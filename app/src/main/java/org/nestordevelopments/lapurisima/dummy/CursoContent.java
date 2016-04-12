package org.nestordevelopments.lapurisima.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CursoContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<CursoItem> ITEMS = new ArrayList<CursoItem>();

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

    private static void addItem(CursoItem item) {
        ITEMS.add(item);
    }

    private static CursoItem createCursoItem(String curso) {
        return new CursoItem(curso);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class CursoItem {
        public final String curso;

        public CursoItem(String content) {
            this.curso = content;
        }

        @Override
        public String toString() {
            return curso;
        }
    }
}
