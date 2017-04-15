package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import co.edu.com.compumovil.gr09_20171.lab3.Otros.InfoUsuario;
import co.edu.com.compumovil.gr09_20171.lab3.POJO.RestInterface;
import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends AppCompatActivity implements View.OnClickListener {

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
                registerUser();
                break;
        }
    }

    private void registerUser() {
        InfoUsuario infoUsuario=new InfoUsuario();
        String u, e, p, p2;
        u = username.getText().toString().toUpperCase();
        e = email.getText().toString().toUpperCase();
        p = password.getText().toString();
        p2 = passwordC.getText().toString();
        if (p.equals(p2)) {
            boolean res = false;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(infoUsuario.getUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            RestInterface restInterface = retrofit.create(RestInterface.class);
            Call<Usuario> call = restInterface.newUser(u,u,p,e," ",u+"img.png");
            try {

                Usuario data = call.execute().body();
                if(infoUsuario.getBDdata(u)){
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent().setClass(
                            Register.this, Login.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(mainIntent);

                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
            }

        } else
            Toast.makeText(getApplicationContext(), "Claves distintas", Toast.LENGTH_SHORT).show();
    }


}
