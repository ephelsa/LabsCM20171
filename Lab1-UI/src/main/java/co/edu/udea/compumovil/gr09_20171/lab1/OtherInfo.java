package co.edu.udea.compumovil.gr09_20171.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class OtherInfo extends AppCompatActivity implements View.OnClickListener {
    //RatingBar and CheckBox declare.
    private RatingBar rBar_1;
    private RatingBar rBar_2;
    private RatingBar rBar_3;
    private RatingBar rBar_4;
    private RatingBar rBar_5;
    private CheckBox cBox_1;
    private CheckBox cBox_2;
    private CheckBox cBox_3;
    private CheckBox cBox_4;
    private CheckBox cBox_5;

    private Button btn_mostrar; //Declaración del Button mostrar.
    private TextView textView_mostrar;  //Declaración del TextView mostrar.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);
        setTitle(R.string.title_otherInfo);

        //inicializo
        rBar_1 = (RatingBar)findViewById(R.id.rBar_1);
        rBar_2 = (RatingBar)findViewById(R.id.rBar_2);
        rBar_3 = (RatingBar)findViewById(R.id.rBar_3);
        rBar_4 = (RatingBar)findViewById(R.id.rBar_4);
        rBar_5 = (RatingBar)findViewById(R.id.rBar_5);

        //Inicialización de los checkBox
        cBox_1 = (CheckBox)findViewById(R.id.cBox_1);
        cBox_2 = (CheckBox)findViewById(R.id.cBox_2);
        cBox_3 = (CheckBox)findViewById(R.id.cBox_3);
        cBox_4 = (CheckBox)findViewById(R.id.cBox_4);
        cBox_5 = (CheckBox)findViewById(R.id.cBox_5);

        //llamado a setOnclickListener para usar eventos de presionado
        cBox_1.setOnClickListener(this);
        cBox_2.setOnClickListener(this);
        cBox_3.setOnClickListener(this);
        cBox_4.setOnClickListener(this);
        cBox_5.setOnClickListener(this);

        //Mostrar Información
        btn_mostrar = (Button)findViewById(R.id.btn_mostrar);
        textView_mostrar = (TextView)findViewById(R.id.tx_mostrar);

        btn_mostrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //control de seleccion
        switch (v.getId()) {
            case R.id.cBox_1:
                //tomo el valor del CheckBox correspondiente
                if (((CheckBox) v).isChecked()) {
                    //setIsIndicator funciona para definir si el RantingBar puede ser editado por el usuario
                    //cuando es false puede ser editado
                    rBar_1.setIsIndicator(false);
                } else {
                    //bloqueo el RantingBar y reinicio el ranting mandando un valor 0
                    rBar_1.setIsIndicator(true);
                    rBar_1.setRating(0);
                }
                break;

            case R.id.cBox_2:
                if (((CheckBox) v).isChecked()) {
                    rBar_2.setIsIndicator(false);
                } else {
                    rBar_2.setIsIndicator(true);
                    rBar_2.setRating(0);
                }
                break;

            case R.id.cBox_3:
                if (((CheckBox) v).isChecked()) {
                    rBar_3.setIsIndicator(false);
                } else {
                    rBar_3.setIsIndicator(true);
                    rBar_3.setRating(0);
                }
                break;

            case R.id.cBox_4:
                if (((CheckBox) v).isChecked()) {
                    rBar_4.setIsIndicator(false);
                } else {
                    rBar_4.setIsIndicator(true);
                    rBar_4.setRating(0);
                }
                break;

            case R.id.cBox_5:
                if (((CheckBox) v).isChecked()) {
                    rBar_5.setIsIndicator(false);
                } else {
                    rBar_5.setIsIndicator(true);
                    rBar_5.setRating(0);
                }
                break;

            case R.id.btn_mostrar:
                textView_mostrar.setText(R.string.resumen);
                Revelar("nombres");
                Revelar("apellidos");
                Revelar("genero");
                Revelar("fecha");
                Revelar("estudios");
                Revelar("telefono");
                Revelar("email");
                Revelar("pais");
                Revelar("ciudad");
                Revelar("direccion");
                RevelarOther(cBox_1, rBar_1);
                RevelarOther(cBox_2, rBar_2);
                RevelarOther(cBox_3, rBar_3);
                RevelarOther(cBox_4, rBar_4);
                RevelarOther(cBox_5, rBar_5);

                textView_mostrar.setVisibility(View.VISIBLE);
                textView_mostrar.setMovementMethod(new ScrollingMovementMethod());
                break;
        }
    }

    //Encargado de mostrar toda la cadena
    private void Revelar(String llave) {
        textView_mostrar.setText(textView_mostrar.getText().toString()
                + System.getProperty("line.separator")
                + getIntent().getExtras().getString(llave));
    }

    //Encargado de mostrar la cadena del Activity OtherInfo
    private void RevelarOther(CheckBox checkBox, RatingBar ratingBar) {
        if (checkBox.isChecked()) {
            textView_mostrar.setText(textView_mostrar.getText().toString()
                    + System.getProperty("line.separator")
                    + checkBox.getText()
                    + ": " + ratingBar.getRating()
                    + " " + getString(R.string.valor));
        }
    }
}
