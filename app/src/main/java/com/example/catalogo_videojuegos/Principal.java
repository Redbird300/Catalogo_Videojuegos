package com.example.catalogo_videojuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.catalogo_videojuegos.API.Cliente;
import com.example.catalogo_videojuegos.API.Juego_Interface;
import com.example.catalogo_videojuegos.API.RegistroAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Principal extends AppCompatActivity {

    private FloatingActionButton nuevo;
    //consultar
    private RecyclerView recyclerView;
    private JuegoAdapter juegoAdapter;
    private List<Juego_Interface> juegos;
    private RegistroAPI consultaAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        //consultar
        recyclerView = findViewById(R.id.recicler);
        recyclerView .setHasFixedSize(true);
        recyclerView .setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        LlenarLista();


        // insertar
        nuevo = (FloatingActionButton)findViewById(R.id.nuevoJuego);

        //insertar 
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), IngresoJuego.class);
                startActivity(i);
            }
        });
    }



    //consultar
    private void LlenarLista(){

        consultaAPI = Cliente.getRetrofitInstance().create(RegistroAPI.class);
        Call<List<Juego_Interface>> call = consultaAPI.getJuegos();
        call.enqueue(new Callback<List<Juego_Interface>>() {

            @Override
            public void onResponse(Call<List<Juego_Interface>> call, Response<List<Juego_Interface>> response) {

                juegos = response.body();
                juegoAdapter= new JuegoAdapter(juegos,getApplicationContext());
                recyclerView.setAdapter(juegoAdapter);

            }

            @Override
            public void onFailure(Call<List<Juego_Interface>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Error // "+t.getMessage()+" // Headers = "+ call.request().headers(),Toast.LENGTH_SHORT).show();

            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        LlenarLista();
    }

}


