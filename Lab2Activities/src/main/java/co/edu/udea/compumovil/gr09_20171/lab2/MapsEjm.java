package co.edu.udea.compumovil.gr09_20171.lab2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapsEjm extends AppCompatActivity implements View.OnClickListener {
Button boton;
    EditText dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ejm);
        boton=(Button)findViewById(R.id.mapb);
        dir=(EditText)findViewById(R.id.mapt);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String url=dir.getText().toString().replaceAll(" ","+");
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.co.in/maps/place/"+url));
        startActivity(intent);
    }
}
