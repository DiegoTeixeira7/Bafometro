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

        double Peso = 1.0;
        int Copos = 0;

        boolean valida = true;

        if(peso.equals("") || sexo.equals("") || nCopos.equals("") || isJejum.equals("")) {
            valida = false;
            Toast.makeText(getBaseContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
        } else {
            Peso = parseDouble(peso);
            Copos = parseInt(nCopos);

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
            //Intent it = new Intent(this, Calculo.class);
            Intent it = new Intent("Calc");
            it.addCategory("Calc1");

            it.putExtra("peso", Peso);
            it.putExtra("sexo", sexo);
            it.putExtra("nCopos", Copos);
            it.putExtra("isJejum", isJejum);

            startActivityForResult(it, 10);
        }
    }

    @Override
    protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent it) {
        super.onActivityResult(codigoRequisicao, codigoResultado, it);

        if(it != null) {
           if(codigoRequisicao == 10) {
               double taxa = it.getDoubleExtra("taxaAlcoolemia",0);
               String classificaco = it.getStringExtra("classificacao");

               Toast.makeText(getBaseContext(),"Taxa de Alcooemia: " + taxa + "\nClassificação: " + classificaco,
                       Toast.LENGTH_SHORT).show();
           }
        }
    }
}