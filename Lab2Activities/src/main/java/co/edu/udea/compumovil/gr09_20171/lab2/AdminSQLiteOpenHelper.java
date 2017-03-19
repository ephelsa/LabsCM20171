package co.edu.udea.compumovil.gr09_20171.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UCharacter;
import android.widget.Toast;

/**
 * Created by Julian on 10/03/2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users" +
                "(user text primary key not null," +
                "email text not null," +
                "password text not null," +
                "photo BLOB," +
                "edad text" +
                ")");
        db.execSQL("create table events" +
                "(id INTEGER primary key autoincrement," +
                "photo BLOB," +
                "name text not null," +
                "descripcion text," +
                "puntuacion float," +
                "responsable text," +
                "fecha text," +
                "ubicacion text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected String getEmailUser(String u) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select email from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected String getEmailUser2(String e) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select email from users where user=\"" + e + "\"", null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }


    protected String getEdadUser(String u) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select edad from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected byte[] getFotoUser(String u) {
        byte[] res;
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select foto from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res = fila.getBlob(0);
        } else {
            res = null;
        }
        return res;

    }

    protected String getPassUser(String u) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select password from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected byte[] getFotoEvent(int id) {
        byte[] res;
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select photo from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getBlob(0);
        } else res = null;
        return res;
    }

    protected String getNameEvent(int id) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select name from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected String getDescripcionEvent(int id) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select descripcion from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected float getPuntuacionEvent(int id) {
        float res;
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select puntuacion from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getFloat(0);
        } else res = -1;
        return res;
    }

    protected String getResponsableEvent(int id) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select responsable from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected String getFechaEvent(int id) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select fecha, ubicacion from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected String getUbicacionEvent(int id) {
        String res = "";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select ubicacion from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res = fila.getString(0);
        }
        return res;
    }

    protected boolean Ingresar(String user, String email, String pass) {
        //permisos de escritura en la bd
        try{
        SQLiteDatabase bd = this.getWritableDatabase();
        //contenedor de valores para la bd
        ContentValues registro = new ContentValues();
        //se mandan los valores con su respectiva celda al contenedor
        registro.put("user", user);
        registro.put("email", email);
        registro.put("password", pass);
        //se inserta el contenedor en la tabla
        bd.insert("users", null, registro);
        //se cierra el uso de la base de datos
        bd.close();}catch (Exception e){
        return false;
        }
        return true;


    }

    protected void setPassword(String u,String p) {
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            bd.execSQL("UPDATE user SET password='" + p + "'WHERE user='" + u + "'");
        } catch (Exception e) {

        }
    }

    protected void setEmail(String u,String em){
        SQLiteDatabase bd=this.getWritableDatabase();
        try {
            bd.execSQL("UPDATE user SET email='" + em + "'WHERE user='" + u + "'");
        }catch (Exception e){

        }
    }

    protected void setEdad(String u,String ed){
        SQLiteDatabase bd=this.getWritableDatabase();
        try {
            bd.execSQL("UPDATE user SET edad='" + ed + "'WHERE user='" + u + "'");
        }catch (Exception e){

        }
    }

    protected void setFoto(String u,byte[] f){
        SQLiteDatabase bd=this.getWritableDatabase();
        try {
            bd.execSQL("UPDATE user SET edad=" + f + "WHERE user='" + u + "'");
        }catch (Exception e){

        }
    }



}
