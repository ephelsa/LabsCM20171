package co.edu.udea.compumovil.gr09_20171.lab2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private Button btnReg;
    private Button btnCancel;
    private EditText username;
    private EditText emailR;
    private EditText password;
    private EditText passwordC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnReg = (Button) findViewById(R.id.btnIngReg);
        btnReg.setOnClickListener(this);
        btnCancel=(Button)findViewById(R.id.btnCancelar);
        btnCancel.setOnClickListener(this);
        username = (EditText) findViewById(R.id.userRegistro);
        emailR = (EditText) findViewById(R.id.correoRegistro);
        password = (EditText) findViewById(R.id.passwordR);
        passwordC = (EditText) findViewById(R.id.passwordR2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIngReg:

                guardar(v);
                break;
            case R.id.btnCancelar:
                Intent intent=new Intent().setClass(Registro.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                break;
        }
    }

    //guardamos el usuario en la base de datos
    private void guardar(View v) {
        //obtenemos los valores de los campos de texto
        String user = username.getText().toString();
        final String email = emailR.getText().toString();
        String pass = password.getText().toString();
        String pass2 = passwordC.getText().toString();
        //validamos que no exista el usuario o el correo , tambien que los campos esten llenos
        if (noExisteU(user) && noVacio(username, emailR, password, passwordC) && noExisteC(email)) {
            if (pass.equals(pass2)) {//se valida que la clave sea la misma
                Ingresar(user, email, pass);//metodo de ingreso de datos
                //timertask para que espere un momento antes de pasar a otra activity
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        //iniciar siguiente actividad
                        Intent mainIntent = new Intent().setClass(
                                Registro.this, Login.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(mainIntent);

                        finish();
                    }
                };
                Timer timer = new Timer();
                //tiempo en decimas
                timer.schedule(task, 1000);
            } else {
                passwordC.setError(getString(R.string.ErrorPass2));
            }
        }
    }

    //comprovacion de campos vacios
    private boolean noVacio(EditText u, EditText e, EditText p, EditText p2) {

        if (u.getText().toString().isEmpty()) {
            u.setError(getString(R.string.ErrorUsuario));
        }
        if (e.getText().toString().isEmpty()) {
            e.setError(getString(R.string.ErrorCorreo));
        }
        if (p.getText().toString().isEmpty()) {
            p.setError(getString(R.string.ErrorPass));
        }
        if (p2.getText().toString().isEmpty()) {
            p2.setError(getString(R.string.ErrorPass));
        }
        return (!u.getText().toString().isEmpty() &&
                !e.getText().toString().isEmpty() &&
                !p.getText().toString().isEmpty() &&
                !p2.getText().toString().isEmpty());
    }

    //ingreso de nuevo usuario
    private void Ingresar(String user, String email, String pass) {
        //conexion con la base de datos , si no existe se crea
        //el nombre de la base de datos es compumovil
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "compumovil", null, 1);
        //permisos de escritura en la bd
        SQLiteDatabase bd = admin.getWritableDatabase();
        //contenedor de valores para la bd
        ContentValues registro = new ContentValues();
        //se mandan los valores con su respectiva celda al contenedor
        registro.put("user", user);
        registro.put("email", email);
        registro.put("password", pass);
        //se inserta el contenedor en la tabla
        bd.insert("users", null, registro);
        //se cierra el uso de la base de datos
        bd.close();
        Toast.makeText(this, R.string.OkUserR,
                Toast.LENGTH_SHORT).show();

    }

    //verifica que el usuario no exista aun en la base de datos
    private boolean noExisteU(String user) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "compumovil", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //se crea un tipo cursos que guarda una fila
        //se usa el rawquery para realizar una consulta
        Cursor fila = bd.rawQuery("select * from users where user=\"" + user + "\"", null);
//verifica si se encontro un resultado
        if (fila.moveToFirst()) {
            Toast.makeText(this, R.string.ErrorUserRep,
                    Toast.LENGTH_SHORT).show();
        }
        bd.close();
        //retorna la busqueda
        return !fila.moveToFirst();
    }


    //funciona igual que el noExisteU pero usando el email
    private boolean noExisteC(String email) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "compumovil", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from users where user=\"" + email + "\"", null);
        if (fila.moveToFirst()) {
            Toast.makeText(this, R.string.ErrorCorreoR,
                    Toast.LENGTH_SHORT).show();
        }
        bd.close();
        return !fila.moveToFirst();
    }
}
