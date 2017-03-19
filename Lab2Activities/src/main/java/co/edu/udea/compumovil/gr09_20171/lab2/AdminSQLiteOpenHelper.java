package co.edu.udea.compumovil.gr09_20171.lab2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    protected String getEmailUser(String u){
        String res="";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select email from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res=fila.getString(0);
        }
        return res;
    }

    protected String getEdadUser(String u,int dat){
        String res="";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select edad from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res=fila.getString(0);
        }
        return res;
    }

    protected byte[] getFotoUser(String u){
        byte []res;
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select foto from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res=fila.getBlob(0);
        }else{
            res=null;
        }
        return res;

    }

    protected String getPassUser(String u){
        String res="";
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select password from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            res=fila.getString(0);
        }
        return res;
    }

    protected byte[] getFotoEvent(int id){
        byte[] res;
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select photo from events where id=" + id, null);
        if (fila.moveToFirst()) {
            res=fila.getBlob(0);
        }else res=null;
        return res;
    }

    protected String getNameEvent(int id){
        String res="";
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila=bd.rawQuery("select name from events where id="+id,null);
        if(fila.moveToFirst()){
            res=fila.getString(0);
        }
        return res;
    }

    protected String getDescripcionEvent(int id){
        String res="";
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila=bd.rawQuery("select descripcion from events where id="+id,null);
        if(fila.moveToFirst()){
            res=fila.getString(0);
        }
        return res;
    }

    protected float getPuntuacionEvent(int id){
        float res;
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila=bd.rawQuery("select puntuacion from events where id="+id,null);
        if(fila.moveToFirst()){
            res=fila.getFloat(0);
        }else res=-1;
        return res;
    }

    protected String getResponsableEvent(int id){
        String res="";
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila=bd.rawQuery("select responsable from events where id="+id,null);
        if(fila.moveToFirst()){
            res=fila.getString(0);
        }
        return res;
    }

    protected String getFechaEvent(int id){
        String res="";
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila=bd.rawQuery("select fecha, ubicacion from events where id="+id,null);
        if(fila.moveToFirst()){
            res=fila.getString(0);
        }
        return res;
    }

    protected String getUbicacionEvent(int id){
        String res="";
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila=bd.rawQuery("select ubicacion from events where id="+id,null);
        if(fila.moveToFirst()){
            res=fila.getString(0);
        }
        return res;
    }


}
