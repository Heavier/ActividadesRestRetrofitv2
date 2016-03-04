package com.example.dam.actividadesrestretrofit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dam.actividadesrestretrofit.api.ApiActividad;
import com.example.dam.actividadesrestretrofit.pojo.Actividad;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Principal extends AppCompatActivity {

    private android.widget.TextView textView;
    private android.widget.EditText etIdProfesor;
    private android.widget.RadioButton rbComplementaria;
    private android.widget.RadioButton rbExtraescolar;
    private android.widget.RadioGroup rgTipo;
    private android.widget.Button bFechai;
    private android.widget.Button bFechaf;
    private android.widget.Button bHorai;
    private android.widget.Button bHoraf;
    private android.widget.TextView textView2;
    private android.widget.TextView textView3;
    private android.widget.EditText etDescripcion;
    private android.widget.EditText etAlumno;
    private android.widget.EditText etLugari;
    private android.widget.EditText etLugarf;
    private android.widget.ImageButton imageButton;
    private DatePickerDialog datePi;
    private DatePickerDialog datePf;
    private TimePickerDialog timePi;
    private TimePickerDialog timePf;
    private SimpleDateFormat dateFormatter;
    private String idprofesor;
    private String tipo;
    private String fechai;
    private String fechaf;
    private String lugari;
    private String lugarf;
    private String descripcion;
    private String alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.imageButton = (ImageButton) findViewById(R.id.imageButton);
        this.etLugarf = (EditText) findViewById(R.id.etLugarf);
        this.etLugari = (EditText) findViewById(R.id.etLugari);
        this.etAlumno = (EditText) findViewById(R.id.etAlumno);
        this.etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        this.bHoraf = (Button) findViewById(R.id.bHoraf);
        this.bHorai = (Button) findViewById(R.id.bHorai);
        this.bFechaf = (Button) findViewById(R.id.bFechaf);
        this.bFechai = (Button) findViewById(R.id.bFechai);
        this.rgTipo = (RadioGroup) findViewById(R.id.rgTipo);
        this.rbExtraescolar = (RadioButton) findViewById(R.id.rbExtraescolar);
        this.rbComplementaria = (RadioButton) findViewById(R.id.rbComplementaria);
        this.etIdProfesor = (EditText) findViewById(R.id.etIdProfesor);
        this.textView = (TextView) findViewById(R.id.textView);

        // Iniciando campos
        idprofesor = "3";
        tipo = "Complementaria";
        fechai = "2000-01-01 00:00";
        fechaf = "2000-01-01 00:00";
        lugari = "No";
        lugarf = "No";
        descripcion = "Vacio";
        alumno = "Javier";

        fechasYhoras();
    }

    public void fechasYhoras(){
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final Calendar calendar = Calendar.getInstance();

        datePi = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                bFechai.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePf = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                bFechaf.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        timePi = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                bHorai.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        timePf = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                bHoraf.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        View.OnClickListener dateListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePi.show();
            }
        };
        View.OnClickListener dateListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePf.show();
            }
        };
        View.OnClickListener timeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePi.show();
            }
        };
        View.OnClickListener timeListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePf.show();
            }
        };

        bFechai.setOnClickListener(dateListener);
        bFechaf.setOnClickListener(dateListener2);
        bHorai.setOnClickListener(timeListener);
        bHoraf.setOnClickListener(timeListener2);
    }

    public void guardar(View view) {

        if (!etIdProfesor.getText().equals(""))
        idprofesor = etIdProfesor.getText().toString();

        if (rbComplementaria.isChecked()) {
            tipo = "complementaria";
        } else if (rbExtraescolar.isChecked()) {
            tipo = "extraescolar";
        }

        if (!bFechai.getText().equals(R.string.fecha) && !bHorai.getText().equals(R.string.hora)){
            fechai = bFechai.getText().toString() + " " + bHorai.getText().toString();
        }
        if (!bFechaf.getText().equals(R.string.fecha) && !bHoraf.getText().equals(R.string.hora)){
            fechaf = bFechaf.getText().toString() + " " + bHoraf.getText().toString();
        }

        if (!etLugari.getText().equals(""))
        lugari = etLugari.getText().toString();

        if (!etLugarf.getText().equals(""))
        lugarf = etLugarf.getText().toString();

        if (!etDescripcion.getText().equals(""))
        descripcion = etDescripcion.getText().toString();

        if (!etAlumno.getText().equals(""))
        alumno = etAlumno.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiActividad api = retrofit.create(ApiActividad.class);
        Actividad ac = new Actividad(idprofesor, tipo, fechai, fechaf, lugari, lugarf, descripcion, alumno);
        Call<Actividad> call = api.crearActividad(ac);
        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(), R.string.si, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.no, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limpiar(View view) {
        etIdProfesor.setText("");
        rbExtraescolar.setChecked(false);
        rbComplementaria.setChecked(false);
        bFechaf.setText(R.string.fecha);
        bFechai.setText(R.string.fecha);
        bHoraf.setText(R.string.hora);
        bHorai.setText(R.string.hora);
        etLugarf.setText("");
        etLugari.setText("");
        etDescripcion.setText("");
        etAlumno.setText("");
    }

    public void lista(View view) {
        startActivity(new Intent(this, Lista.class));
    }
}