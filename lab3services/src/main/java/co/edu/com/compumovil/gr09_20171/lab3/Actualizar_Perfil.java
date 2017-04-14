package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Actualizar_Perfil extends AppCompatActivity implements View.OnClickListener {
    private static final int PHOTO_SELECTED = 2;
    AdminSQLiteOpenHelper admin;
    private Bitmap img;
    private String username;
    private ImageView foto;
    private EditText user;
    private EditText email;
    private EditText edad;
    private Button actualizar;
    private Button cancelar;
    FileOutputStream outputStream;
    private final String filename = "registro.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        admin = new AdminSQLiteOpenHelper(this, "compumovil", null, 1);
        setContentView(R.layout.activity_actualizar__perfil);
        foto=(ImageView)findViewById(R.id.Act_perfil_foto);
        user=(EditText)findViewById(R.id.Act_perfil_User);
        email=(EditText)findViewById(R.id.Act_perfil_email);
        edad=(EditText)findViewById(R.id.Act_perfil_edad);
        actualizar=(Button)findViewById(R.id.Act_perfil_act);
        cancelar=(Button)findViewById(R.id.Act_perfil_cancelar);
        actualizar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        foto.setOnClickListener(this);
        /*try{
            username=getUsuario();
            Bitmap img=admin.getFotoUser(username);
                    if(img!=null) {
                        foto.setImageBitmap(img);
                    }
            user.setText(username);
            email.setText(admin.getEmailUser(username));
            edad.setText(admin.getEdadUser(username));

        }catch (Exception e){

        }*/
    }

    private String getUsuario () throws IOException {
        final String filename = "registro.txt";
        String usuario = "";
        BufferedReader lector;
        lector = new BufferedReader(
                new InputStreamReader(
                        openFileInput(filename)));
        usuario = lector.readLine();
        return usuario;
    }

    @Override
    public void onClick(View v) {
switch (v.getId()) {
    case R.id.Act_perfil_act:
        String pUser = user.getText().toString();
        String pEmail = email.getText().toString();
        String pEdad = edad.getText().toString();
        actualizar(pUser, pEmail, pEdad, img);
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(pUser.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent mainIntent = new Intent().setClass(
                Actualizar_Perfil.this, Navegacion.class);
        startActivity(mainIntent);
        finish();

        finish();

        break;

    case R.id.Act_perfil_foto:
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_SELECTED);
        break;

    case R.id.Act_perfil_cancelar:break;
}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_SELECTED) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
                    img=bitmap;
                    foto.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void actualizar(String u,String e,String age,Bitmap f){
        admin.setEdad(username,age);
        admin.setEmail(username,e);
        admin.setFoto(username,f);
        admin.setUsername(username,u);
    }
}
