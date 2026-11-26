
package com.corridaia;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.text.InputType;

public class MainActivity extends Activity {

    EditText valor, distancia;
    TextView resultado;

    double gasolina = 5.00;
    double consumo = 8.0;
    double lucroMinimo = 10.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout tela = new LinearLayout(this);
        tela.setOrientation(LinearLayout.VERTICAL);
        tela.setPadding(28, 35, 28, 25);
        tela.setBackgroundColor(Color.rgb(15, 22, 35));

        TextView titulo = new TextView(this);
        titulo.setText("CORRIDA IA");
        titulo.setTextSize(30);
        titulo.setTextColor(Color.GREEN);
        titulo.setGravity(Gravity.CENTER);
        tela.addView(titulo);

        TextView subtitulo = new TextView(this);
        subtitulo.setText("Seu assistente inteligente de corridas");
        subtitulo.setTextColor(Color.WHITE);
        subtitulo.setGravity(Gravity.CENTER);
        tela.addView(subtitulo);

        valor = campo("Valor da corrida (R$)");
        tela.addView(valor);

        distancia = campo("Distância total (KM)");
        tela.addView(distancia);

        Button calcular = new Button(this);
        calcular.setText("ANALISAR CORRIDA");
        tela.addView(calcular);

        resultado = new TextView(this);
        resultado.setTextColor(Color.WHITE);
        resultado.setTextSize(19);
        resultado.setPadding(5, 30, 5, 20);
        tela.addView(resultado);

        calcular.setOnClickListener(v -> analisar());

        setContentView(tela);
    }

    EditText campo(String dica) {
        EditText e = new EditText(this);
        e.setHint(dica);
        e.setTextColor(Color.WHITE);
        e.setHintTextColor(Color.LTGRAY);
        e.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return e;
    }

    void analisar() {
        try {
            double preco = Double.parseDouble(
                    valor.getText().toString().replace(",", "."));
            double km = Double.parseDouble(
                    distancia.getText().toString().replace(",", "."));

            if (preco <= 0 || km <= 0) {
                resultado.setText("Informe valores maiores que zero.");
                return;
            }

            double combustivel = (km / consumo) * gasolina;
            double lucro = preco - combustivel;

            String decisao = lucro >= lucroMinimo
                    ? "ACEITAR CORRIDA"
                    : "RECUSAR CORRIDA";

            resultado.setText(
                    "DECISÃO: " + decisao +
                    "\n\nValor: R$ " + String.format("%.2f", preco) +
                    "\nCombustível: R$ " + String.format("%.2f", combustivel) +
                    "\nLucro estimado: R$ " + String.format("%.2f", lucro) +
                    "\n\nLucro mínimo: R$ 10,00"
            );

        } catch (Exception e) {
            resultado.setText("Preencha os dois campos corretamente.");
        }
    }
}
