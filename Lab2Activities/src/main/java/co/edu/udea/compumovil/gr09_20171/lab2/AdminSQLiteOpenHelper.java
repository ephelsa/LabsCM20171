package co.edu.udea.compumovil.gr09_20171.lab2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
                "photo text," +
                "edad text)");
        db.execSQL("create table events" +
                "(id INTEGER primary key autoincrement," +
                "photo text," +
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

    protected String getDataUser(String u,int i){
        String r="";
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select email,password,edad from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            r=fila.getString(i);
        }
        return r;

    }
}
