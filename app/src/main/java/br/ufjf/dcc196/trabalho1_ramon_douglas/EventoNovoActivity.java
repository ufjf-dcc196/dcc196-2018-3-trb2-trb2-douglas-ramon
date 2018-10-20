package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventoNovoActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnCadastrarEvento;
    private EditText edtTitulo;
    private EditText edtData;
    private EditText edtHora;
    private EditText edtFacilitador;
    private EditText edtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_novo);

        btnVoltar = (Button) findViewById(R.id.btn_voltar_evento_novo);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoNovoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
