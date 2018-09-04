package com.iteso.tarea1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {

    public class Alumno{
        String nom;
        String tel;
        String esc;
        String gen;
        String lib;
        String dep;

        public Alumno(){
            nom = "";
            tel = "";
            esc = "";
            gen = "";
            lib = "";
            dep = "";
        }

        public Alumno(String nom,String tel,String esc,String gen,String lib,String dep){
            this.nom = nom;
            this.tel = tel;
            this.esc = esc;
            this.gen = gen;
            this.lib = lib;
            this.dep = dep;
        }

        @Override
        public String toString(){
            return "Nombre: " + nom + "\nTelef: " + tel + "\nEscol: "
                    + esc + "\n Genero: " + gen + "\nLibro: " + lib + "\nDeporte: " + dep;
        }
    }

    EditText nombre;
    EditText telefono;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    RadioGroup radioGroup;
    RadioButton radioFem;
    RadioButton radioMas;
    CheckBox deporte;
    Button limpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.main_spinner);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.main_autoTextView);
        limpiar = (Button) findViewById(R.id.main_limpiar);

        ArrayAdapter<CharSequence> adapterEscolaridad = ArrayAdapter.createFromResource(this,
                R.array.escolaridad, android.R.layout.simple_spinner_item);
        adapterEscolaridad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterEscolaridad);

        String[] libros = getResources().getStringArray(R.array.libros);
        ArrayAdapter<String> adapterLibros = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, libros);
        autoCompleteTextView.setAdapter(adapterLibros);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ActivityMain.this);
                builder1.setMessage("Seguro quieres limpiar?");

                builder1.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                limpiar();
                                Toast.makeText(ActivityMain.this, "Limpio",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Alumno alumno;
        nombre = findViewById(R.id.main_nombre);
        telefono = findViewById(R.id.main_telefono);
        spinner = findViewById(R.id.main_spinner);
        autoCompleteTextView = findViewById(R.id.main_autoTextView);
        radioGroup = findViewById(R.id.main_radio_group);
        Button gender = findViewById(radioGroup.getCheckedRadioButtonId());
        deporte = findViewById(R.id.main_check);
        String isDeporte;

        if(deporte.isChecked())
            isDeporte = "Si Practica";
        else
            isDeporte = "No Practica";

        switch(item.getItemId()){
            case R.id.activity_main_save:
                alumno = new Alumno(nombre.getText().toString(), telefono.getText().toString(), spinner.getSelectedItem().toString(),
                        gender.getText().toString(), autoCompleteTextView.getText().toString(), isDeporte);
                Toast.makeText(this, alumno.toString(),Toast.LENGTH_SHORT).show();
                limpiar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void limpiar(){
        nombre = findViewById(R.id.main_nombre);
        telefono = findViewById(R.id.main_telefono);
        spinner = findViewById(R.id.main_spinner);
        autoCompleteTextView = findViewById(R.id.main_autoTextView);
        radioGroup = findViewById(R.id.main_radio_group);
        radioFem = findViewById(R.id.main_fem);
        radioMas = findViewById(R.id.main_masc);
        deporte = findViewById(R.id.main_check);

        nombre.setText("");
        telefono.setText("");
        spinner.setSelection(0);
        autoCompleteTextView.setText("");
        radioFem.setChecked(true);
        radioMas.setChecked(false);
        deporte.setChecked(false);
    }

}
