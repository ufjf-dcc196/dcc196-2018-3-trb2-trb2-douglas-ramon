package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParticipanteNovoActivity extends AppCompatActivity {

    private Button btnCadastrarParticipante;
    private Button btnVoltar;
    private EditText edtNomeCompleto;
    private EditText edtEmail;
    private EditText edtCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_novo);

        btnVoltar = (Button) findViewById(R.id.btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipanteNovoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
