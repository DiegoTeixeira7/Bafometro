package com.example.diegoteixeira.bafmetro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendData(View view) {
        String peso = ((EditText)findViewById(R.id.valorPeso)).getText().toString();
        String sexo = ((EditText)findViewById(R.id.valorSexo)).getText().toString();
        String nCopos = ((EditText)findViewById(R.id.valorCopos)).getText().toString();
        String isJejum = ((EditText)findViewById(R.id.valorJejum)).getText().toString();

        boolean valida = true;

        if(peso.equals("") || sexo.equals("") || nCopos.equals("") || isJejum.equals("")) {
            valida = false;
            Toast.makeText(getBaseContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
        } else {
            double Peso = parseDouble(peso);
            int Copos = parseInt(nCopos);

            if(Peso < 20 || Peso > 400) {
                valida = false;
                Toast.makeText(getBaseContext(),"Peso inválido",Toast.LENGTH_SHORT).show();
            }

            if(Copos < 0) {
                valida = false;
                Toast.makeText(getBaseContext(),"Quantidade de copos inválida",Toast.LENGTH_SHORT).show();
            }

            if(!sexo.equals("m") && !sexo.equals("f")) {
                valida = false;
                Toast.makeText(getBaseContext(),"Sexo inválido",Toast.LENGTH_SHORT).show();
            }

            if(!isJejum.equals("s") && !isJejum.equals("n")) {
                valida = false;
                Toast.makeText(getBaseContext(),"Jejum inválido",Toast.LENGTH_SHORT).show();
            }
        }

        if(valida) {
            Intent it = new Intent(getBaseContext(), Calculo.class);

            it.putExtra("peso", peso);
            it.putExtra("sexo", sexo);
            it.putExtra("nCopos", nCopos);
            it.putExtra("isJejum", isJejum);

            startActivity(it);
        }
    }
}