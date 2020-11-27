package com.example.diegoteixeira.bafmetro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Calculo extends AppCompatActivity {
    private double peso;
    private int nCopos;
    private String sexo;
    private String isJejum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        Intent it = getIntent();

        peso = it.getDoubleExtra("peso",1);
        nCopos = it.getIntExtra("nCopos",0);
        sexo = it.getStringExtra("sexo");
        isJejum = it.getStringExtra("isJejum");
    }

    public void calcular(View view) {
        double taxaAlcoolemia = (nCopos * 4.8) / (peso * coeficiente());
        String classificacao = classificacao(taxaAlcoolemia);

        Intent it = new Intent(getBaseContext(), MainActivity.class);

        it.putExtra("taxaAlcoolemia", taxaAlcoolemia);
        it.putExtra("classificacao", classificacao);

        it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(it);
        finish();
    }

    private double coeficiente() {
        if(sexo.equals("m") && isJejum.equals("s")) {
            return 0.7;
        } else if(sexo.equals("f") && isJejum.equals("s")) {
            return 0.6;
        } else {
            return 1.1;
        }
    }

    private String classificacao(double taxaAlcoolemia) {
        if(taxaAlcoolemia > 0.2) {
            return "Pessoa Alcoolizada";
        } else {
            return "Pessoa NÂO Alcoolizada";
        }
    }
}