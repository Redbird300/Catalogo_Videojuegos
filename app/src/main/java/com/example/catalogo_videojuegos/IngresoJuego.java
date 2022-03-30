package com.example.catalogo_videojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.catalogo_videojuegos.API.Cliente;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IngresoJuego extends AppCompatActivity {

    private EditText fecha, nombre, estudio;
    private Spinner plataforma, clasificacion;
    private Button guardar;
    private Calendar calendario;
    private DatePickerDialog FechaDeDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_juego);

        nombre = findViewById(R.id.txt_nombre);
        estudio = findViewById(R.id.txt_estudio);
        plataforma = findViewById(R.id.spinner_plataforma);
        clasificacion = findViewById(R.id.spinner_clasificacion);
        fecha = findViewById(R.id.txtFechaLan);
        guardar = findViewById(R.id.btn_guardar);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCalendario();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Guardar();
            }

        });

    }

    //Metodos

    private void mostrarCalendario(){
        calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);

        FechaDeDialogo = new DatePickerDialog(IngresoJuego.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int cAno, int cMes, int cDia) {
                fecha.setText(cAno + "-" +cMes + "-" + cDia);
            }
        }, dia, mes, ano);
        FechaDeDialogo.show();
    }

    private void Guardar() {

        String Nombre = nombre.getText().toString();
        String Estudio = estudio.getText().toString();
        int Plataforma = plataforma.getSelectedItemPosition();
        int Clasificacion = clasificacion.getSelectedItemPosition();
        String Fecha = fecha.getText().toString();
        System.out.printf("Nombre>>"+Nombre);
        System.out.printf("Estudio>>"+Estudio);
        System.out.printf("Plataforma>>"+Plataforma);
        System.out.printf("Clasificacion>>"+Clasificacion);
        System.out.printf("Fecha>>"+Fecha);

        Call<ResponseBody> call = Cliente.getInstance()
                .getMyApi()
                .Juego(Nombre, Estudio, Plataforma, Clasificacion, Fecha);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response != null && response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.to_regexitoso), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.to_error) + t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }

        });

    }

}