package co.edu.udea.compumovil.gr09_20171.lab1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import static android.R.attr.checked;
//k

public class PersonalInfo extends AppCompatActivity implements View.OnClickListener {
    private int cYear;
    private int cMonth;
    private int cDay;
    private Spinner estudios;
    private Button btn_next;
    private Button bt_Data;
    private EditText editText_nombres;
    private EditText editText_apellidos;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        setTitle(R.string.title_personalInfo);

        //Creación del Spinner junto con sus datos en el Array.
        estudios =(Spinner) findViewById(R.id.SpEstudio);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estudios, android.R.layout.simple_spinner_item);
        estudios.setAdapter(adapter);
        bt_Data=(Button)findViewById(R.id.bt_Data);
        bt_Data.setOnClickListener(this);

        //Definición de vistas
        editText_nombres = (EditText)findViewById(R.id.TF_Name);
        editText_apellidos = (EditText)findViewById(R.id.TF_LName);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        //Creación del botón Siguiente.
        btn_next = (Button)findViewById(R.id.btn_ContactInfo);
        btn_next.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle guardarEstado){
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putInt("day", cDay);
        guardarEstado.putInt("month", cMonth);
        guardarEstado.putInt("year", cYear);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        cDay = recEstado.getInt("day");
        cMonth = recEstado.getInt("month");
        cYear = recEstado.getInt("year");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ContactInfo:
                Intent intent_ContactInfo = new Intent(PersonalInfo.this, ContactInfo.class);

                GetEditText(intent_ContactInfo, "nombres", editText_nombres);
                GetEditText(intent_ContactInfo, "apellidos", editText_apellidos);
                GetRadioButton(intent_ContactInfo, "genero", radioGroup);
                GetDataPick(intent_ContactInfo, "fecha", cDay, cMonth, cYear);
                GetSpinner(intent_ContactInfo, "estudios");

                startActivity(intent_ContactInfo);
                break;

            case R.id.bt_Data:
                //Creacion del datapick
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        cDay = dayOfMonth;
                        cMonth = month;
                        cYear = year;
                    }
                }, cDay, cMonth, cYear);

                if(cYear != 0) {
                    datePickerDialog.show();
                    datePickerDialog.updateDate(cYear, cMonth, cDay);
                }else{
                    final Calendar c = Calendar.getInstance();
                    cDay = c.get(Calendar.DAY_OF_MONTH);
                    cMonth = c.get(Calendar.MONTH);
                    cYear = c.get(Calendar.YEAR);
                    datePickerDialog.updateDate(cYear, cMonth, cDay);
                    datePickerDialog.show();
                    datePickerDialog.updateDate(cYear, cMonth, cDay);
                }
                break;
        }
    }

    private void GetEditText(Intent intent, String llave, EditText e) {
        intent.putExtra(llave, e.getText().toString());
    }

    private void GetRadioButton(Intent intent, String llave, RadioGroup r) {
        if (r.getCheckedRadioButtonId() != R.id.RB_GMale && r.getCheckedRadioButtonId() != R.id.RB_GFemale) {
            intent.putExtra(llave, getString(R.string.void_gender));
        }
        else if (r.getCheckedRadioButtonId() == R.id.RB_GMale) {
            intent.putExtra(llave, getString(R.string.RB_GMale));
        }
        else {
            intent.putExtra(llave, getString(R.string.RB_GFemale));
        }
    }

    private void GetDataPick(Intent intent, String llave,int dia, int mes, int año){
        String innnnt;
        if (dia == 0) {
            innnnt = getString(R.string.void_fecha);
            intent.putExtra(llave, innnnt);
        }
        else {
            innnnt = dia + "/" + (mes + 1) + "/" + año;
            intent.putExtra(llave, innnnt);
        }
    }

    private void GetSpinner(Intent intent, String llave) {
        if(estudios.getSelectedItem().toString() == getString(R.string.Grado)) {
            intent.putExtra(llave, getString(R.string.void_estudios));
        }
        else {
            intent.putExtra(llave, estudios.getSelectedItem().toString());
        }
    }
}
