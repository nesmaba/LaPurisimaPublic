package org.nestordevelopments.lapurisima.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by nestor on 30/08/2016.
 */
public class AlumnosSQLiteHelperBAK extends SQLiteOpenHelper {

    //LEEMOS UNA BD EXTERNA MÁXIMO 1MB. PARA MÁS BUSCA EN http://www.aprendeandroid.com/l5/sql4.htm
    public static String DB_PATH = "/data/data/org.nestordevelopments.lapurisima/databases/";
    public static String DB_NAME = "faltas.db";
    public static int DB_VERSION = 1;
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public AlumnosSQLiteHelperBAK(Context contexto, String nombre,
                                  SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        this.myContext = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No hacemos nada aqui

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cuando haya cambios en la estructura deberemos
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // Si existe, no haemos nada!
        } else {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();
            try {
                copyDataBase();
                openDataBase();
            } catch (IOException e) {
                throw new Error("Error copiando database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // Base de datos no creada todavia
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    public void openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = myContext.getAssets().open("faltas.db");

        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }

    public void getAlumnos(int curso){
        String args[] =new String[1];
        args[0]=String.valueOf(curso);
        Cursor c = myDataBase.query("Alumno", null, "curso=?", args, null, null, null);
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String nombre= c.getString(1);
                String apellido1 = c.getString(2);
                String apellido2 = c.getString(3);
                System.out.println("FILA: "+apellido1+" "+apellido2+", "+nombre);
            } while(c.moveToNext());
        }
    }
}