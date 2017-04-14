package co.edu.com.compumovil.gr09_20171.lab3;

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

import java.io.IOException;

public class EventV extends AppCompatActivity implements View.OnClickListener {

    private static final int PHOTO_SELECTED = 2;
    private Button cargar;
    private Button buscar;
    private EditText idEvent;
    AdminSQLiteOpenHelper admin;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_v);

        cargar=(Button)findViewById(R.id.eventCre);
        buscar=(Button)findViewById(R.id.eventBusc);
        idEvent=(EditText)findViewById(R.id.eventId);
        cargar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        img=(ImageView)findViewById(R.id.imageView2);
        admin=new AdminSQLiteOpenHelper(this,
                "compumovil", null, 1);
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
                    admin.CreateEvent(bitmap,"","",0,"","","");
                    img.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.eventBusc:
                Bitmap imgr =admin.getFotoEvent(Integer.parseInt(idEvent.getText().toString()));
                img.setImageBitmap(imgr);
                break;
            case R.id.eventCre:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PHOTO_SELECTED);
                break;
        }
    }
}
