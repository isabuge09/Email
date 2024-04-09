package buge.isabelly.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar); // obtem o botao pelo seu id

        btnEnviar.setOnClickListener(new View.OnClickListener() {//Captura evento de click do botao
            @Override
            public void onClick(View v) { // metodo executado quando o botao e clicado

                // obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);// obtem o campo de email pelo id
                String email = etEmail.getText().toString();// obtem o texto do campo de email e converte para string

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);// obtem o campo de assunto pelo id
                String assunto = etAssunto.getText().toString();//obtem o texto do campo de assunto e converte para string

                EditText etTexto = (EditText) findViewById(R.id.etTexto);// obtem o campo de texto pelo id
                String texto = etTexto.getText().toString();//obtem o texto do campo de texto e converte para string

                Intent i = new Intent(Intent.ACTION_SENDTO);//cria uma intencao, indicando a acao a ser realizada

                i.setData(Uri.parse("mailto:"));// define que esta interessado apenas em apps que sao clientes de email

                String[] emails = new String[] {email};//lista de strings que vao ser passados para o intent
                i.putExtra(Intent.EXTRA_EMAIL, emails);//lista de strings de email
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);//lista de strings de assunto
                i.putExtra(Intent.EXTRA_TEXT, texto);//lista de strings de texto

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));//tenta executar intent, e da opcao de escolha dos apps disponiveis
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum APP que posso realizar essa operação", Toast.LENGTH_LONG).show();//caso nao tenha apps disponiveis aparece uma mensagem de erro
                }
            }
        });
    }
}