package co.edu.udea.compumovil.gr09_20171.lab1;

import android.content.Intent;
import android.service.notification.Condition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactInfo extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView_country, autoCompleteTextView_cities; //Los autocompletados.
    private Button btn_next;    //Botón para pasar.
    private EditText editText_telefono; //Declaración de teléfono.
    private EditText editText_email;    // Declaración de email.
    private EditText editText_direccion;    //Declaración de la dirección

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        setTitle(R.string.title_contactInfo);


        btn_next = (Button)findViewById(R.id.btn_OtherInfo);
        editText_telefono = (EditText)findViewById(R.id.edit_telefono);
        editText_email = (EditText)findViewById(R.id.edit_email);
        autoCompleteTextView_country = (AutoCompleteTextView)findViewById(R.id.autocomplete_acountry);
        autoCompleteTextView_cities = (AutoCompleteTextView)findViewById(R.id.autocomplete_ccity);
        editText_direccion = (EditText)findViewById(R.id.edit_direccion);

        //Para el autocompletado del País latino.
        String[] countries = getResources().getStringArray(R.array.paises_latinos);
        ArrayAdapter<String> adapter_countries = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        autoCompleteTextView_country.setAdapter(adapter_countries);

        //Autocompletado para las principales ciudades de Colobmia.
        String[] cities = getResources().getStringArray(R.array.ciudades_colombia);
        ArrayAdapter<String> adapter_cities = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        autoCompleteTextView_cities.setAdapter(adapter_cities);

        //Botón de siguiente para llegar al otherInfo.
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactInfo.this, OtherInfo.class);
                EnvíoExtraContact(i);

                startActivity(i);
            }
        });
    }

    //Encargado de enviar los extras al OtherInfo
    private void EnvíoExtraContact(Intent i) {
        String string_nombres = RecibirPersonalInfo("nombres");
        String string_apellidos = RecibirPersonalInfo("apellidos");
        String string_genero = RecibirPersonalInfo("genero");
        String string_fecha = RecibirPersonalInfo("fecha");
        String string_estudios = RecibirPersonalInfo("estudios");
        String string_telefono = editText_telefono.getText().toString();
        String string_email = editText_email.getText().toString();
        String string_pais = autoCompleteTextView_country.getText().toString();
        String string_ciudad = autoCompleteTextView_cities.getText().toString();
        String string_direccion = editText_direccion.getText().toString();

        ConditionToSend(i, string_nombres, "nombres", getString(R.string.void_nombres)); //Nombres
        ConditionToSend(i, string_apellidos, "apellidos", getString(R.string.void_apellidos)); //Apellidos
        i.putExtra("genero", string_genero); //Genero
        i.putExtra("fecha", string_fecha); //Fecha
        i.putExtra("estudios", string_estudios); //Estudios
        ConditionToSend(i, string_telefono, "telefono", getString(R.string.void_telefono)); //Telefono
        ConditionToSend(i, string_email, "email", getString(R.string.void_email)); //Email
        ConditionToSend(i, string_pais, "pais", getString(R.string.void_pais)); //Pais
        ConditionToSend(i, string_ciudad, "ciudad", getString(R.string.void_ciudad)); //Ciudad
        ConditionToSend(i, string_direccion,"direccion", getString(R.string.void_dirección)); //Direccion

    }

    //Encargado de recibir la información del PersonalInfo
    private String RecibirPersonalInfo(String asd) {
        return getIntent().getExtras().getString(asd);
    }

    //Envíos con condición.
    private void ConditionToSend(Intent intent, String str, String llave, String void_xml) {
        if (str.equals("") || str.equals(null)) {
            intent.putExtra(llave, void_xml);
        }
        else {
            intent.putExtra(llave, str);
        }
    }
}