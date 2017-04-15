package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.com.compumovil.gr09_20171.lab3.Otros.InfoUsuario;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final long DELAY = 5000;
    private InfoUsuario perfil;
    private EditText username, password;
    private Button signIN, signUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        perfil = new InfoUsuario();
        username = (EditText) findViewById(R.id.loginEdtUser);
        password = (EditText) findViewById(R.id.loginEdtPass);
        signIN = (Button) findViewById(R.id.loginBtnLogin);
        signUP = (Button) findViewById(R.id.loginBtnReg);
        signIN.setOnClickListener(this);
        signUP.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtnLogin:
                if(perfil.getBDdata(username.getText().toString().toUpperCase())) {
                    if (password.getText().toString().equals(perfil.getPassword())) {
                        Intent intent = new Intent(Login.this, Navegacion.class);
                        intent.putExtra("datos", perfil);
                        startActivity(intent);
                        finish();
                    }
                }

                break;

            case R.id.loginBtnReg:
                Intent intent = new Intent().setClass(Login.this, Register.class);
                startActivity(intent);
                break;
        }

    }
}
