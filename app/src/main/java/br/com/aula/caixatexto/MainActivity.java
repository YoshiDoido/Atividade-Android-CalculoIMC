package br.com.aula.caixatexto;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calcular(View view) {
        TextInputEditText campoNome = findViewById(R.id.textoInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textoInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textoInputEditAltura);
        TextView resultado1 = findViewById(R.id.textoResultado1);
        TextView resultado2 = findViewById(R.id.textoResultado2);

        // Converte os campos de entrada como campoNome, campoPeso e campoAltura para String
        String nome = campoNome.getText().toString();
        String peso = campoPeso.getText().toString();
        String altura = campoAltura.getText().toString();

        // Tratamento de Exceção que checa quando o valor digitado,
        // se o valor for estiver normal, o código executa normalmente
        try {
            // Converte o peso e altura para Double
            Double numPeso = Double.parseDouble(peso);
            Double numAltura = Double.parseDouble(altura);

            // Calcula o IMC
            Double numImc = numPeso / (numAltura * numAltura);

            // Formatação para a apresentação do resultado:
            DecimalFormat df = new DecimalFormat("##.##");
            String imcFormatado = df.format(numImc);

            // Apresentar resultado no campo "resultado1"
            resultado1.setText("IMC: " + imcFormatado + " kg/m²");

        /* Checa a faixa de IMC e exibe os resultados correspondentes
           Para isso vou declarar uma variável classificacao do tipo String
           para armazenar qual faixa de peso o usuário se encontra
        */

            // Deixo declarado aqui em cima
            String classificacao;

            if (numImc < 18.5) {
                classificacao = "Abaixo do peso";
            } else if (numImc < 25) {
                classificacao = "Peso normal";
            } else if (numImc < 30)
                classificacao = "Sobrepeso";
            else if (numImc < 35) {
                classificacao = "Obesidade Grau 1";
            } else if (numImc < 40) {
                classificacao = "Obesidade Grau 2";
            } else {
                classificacao = "Obesidade Grau 3 (Obesidade Extrema)";
            }

            // Exibe a classificação do peso da pessoa no campo "resultado2"
            resultado2.setText("Classificação: " + classificacao);
        } catch (NumberFormatException erroDigitacao) {
            // Se ocorrer um erro de digitação, exibe uma mensagem de erro através de uma exceção
            resultado1.setText("Erro no cálculo do IMC");
            resultado2.setText("Valor inválido!\nPor favor, insira números válidos para peso e altura");
        }
    }

    public void limpar(View view) {
        TextInputEditText campoNome = findViewById(R.id.textoInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textoInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textoInputEditAltura);
        TextView resultado1 = findViewById(R.id.textoResultado1);
        TextView resultado2 = findViewById(R.id.textoResultado2);

        campoNome.setText("");
        campoPeso.setText("");
        campoAltura.setText("");
        resultado1.setText("-");
        resultado2.setText("-");
    }
}