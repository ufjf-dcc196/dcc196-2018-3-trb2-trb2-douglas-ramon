package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.app.Activity;
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

        btnVoltar = (Button) findViewById(R.id.btn_voltar_participante_novo);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipanteNovoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        edtNomeCompleto = (EditText) findViewById(R.id.edt_nome_completo);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtCpf = (EditText) findViewById(R.id.edt_cpf);

        btnCadastrarParticipante = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoParticipante = new Intent();
                String nome = edtNomeCompleto.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCpf.getText().toString();
                resultadoParticipante.putExtra(MainActivity.PARTICIPANTE_NOME, nome);
                resultadoParticipante.putExtra(MainActivity.PARTICIPANTE_EMAIL, email);
                resultadoParticipante.putExtra(MainActivity.PARTICIPANTE_CPF, cpf);
                setResult(Activity.RESULT_OK, resultadoParticipante);
                finish();
            }
        });
    }
}
