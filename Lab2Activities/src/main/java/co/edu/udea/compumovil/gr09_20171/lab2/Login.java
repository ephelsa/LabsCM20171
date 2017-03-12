package co.edu.udea.compumovil.gr09_20171.lab2;

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

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private Button registrar;
    private EditText user;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.bttLogin);
        registrar = (Button) findViewById(R.id.bttReg);
        user = (EditText) findViewById(R.id.edUserL);
        pass = (EditText) findViewById(R.id.edPassL);
        login.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bttLogin:
                log();
                break;
            case R.id.bttReg:
                Intent intent = new Intent().setClass(Login.this, Registro.class);
                startActivity(intent);

                break;
        }
    }

    private void log() {
        String usuario = user.getText().toString();
        String password = pass.getText().toString();
        if (validar(usuario, password)) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    //iniciar siguiente actividad
                    Intent mainIntent = new Intent().setClass(
                            Login.this, test.class);
                    startActivity(mainIntent);
                    finish();
                }
            };
            Timer timer = new Timer();
            //tiempo en decimas
            timer.schedule(task, 1000);
        }
    }

    private boolean validar(String u, String p) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "compumovil", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select password from users where user=\"" + u + "\"", null);
        if (fila.moveToFirst()) {
            if (fila.getString(0).equals(p)) {
                Toast.makeText(this, getString(R.string.LoginOk) + u,
                        Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(this, R.string.ErrorLogin, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.ErrorLogin, Toast.LENGTH_SHORT).show();
        }

        bd.close();
        return false;
    }
}

