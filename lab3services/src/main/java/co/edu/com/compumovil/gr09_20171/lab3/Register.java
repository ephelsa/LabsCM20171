package co.edu.com.compumovil.gr09_20171.lab3;

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

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private final String url = "http://192.168.1.7:3000/api";
    private EditText username, email, password, passwordC;
    private Button register;
    boolean exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.registerEtUser);
        email = (EditText) findViewById(R.id.registerEtEmail);
        password = (EditText) findViewById(R.id.registerEtPass1);
        passwordC = (EditText) findViewById(R.id.registerEtPass2);
        register = (Button) findViewById(R.id.registerBtnReg);
        register.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerBtnReg:
                existUser();
                break;
        }
    }

    private void existUser() {
        String u, e, p, p2;
        u = username.getText().toString().toUpperCase();
        e = email.getText().toString();
        p = password.getText().toString();
        p2 = passwordC.getText().toString();
        if (p.equals(p2)) {
            //making object of RestAdapter
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();

            //Creating Rest Services
            RestInterface restInterface = adapter.create(RestInterface.class);
            restInterface.newUser(u, p, e, " ", u + "img.png", new Callback<Usuario>() {
                @Override
                public void success(Usuario usuario, Response response) {
                    String res = response.getReason();
                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent().setClass(
                            Register.this, Login.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(mainIntent);

                    finish();
                }

                @Override
                public void failure(RetrofitError error) {
                    String megError = error.getMessage();
                    Toast.makeText(getApplicationContext(), megError, Toast.LENGTH_LONG).show();

                }
            });
        } else
            Toast.makeText(getApplicationContext(), "Claves distintas", Toast.LENGTH_SHORT).show();
    }


}
