package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParticipanteEditarActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnSalvar;
    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_editar);

        edtNome = (EditText) findViewById(R.id.edt_nome_completo_edt);
        edtEmail = (EditText) findViewById(R.id.edt_email_edt);
        edtCpf = (EditText) findViewById(R.id.edt_cpf_edt);

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Participante p = MainActivity.participantes.get(posicao);
        edtNome.setText(p.getNome());
        edtEmail.setText(p.getEmail());
        edtCpf.setText(p.getCpf());

        btnVoltar = (Button) findViewById(R.id.btn_voltar_participante_editar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipanteEditarActivity.this, ParticipanteDetalhesActivity.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });

        btnSalvar = (Button) findViewById(R.id.btn_salvar_alteracao);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoParticipante = new Intent(ParticipanteEditarActivity.this, ParticipanteDetalhesActivity.class);

                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCpf.getText().toString();

                MainActivity.participantes.get(posicao).setCpf(cpf);
                MainActivity.participantes.get(posicao).setEmail(email);
                MainActivity.participantes.get(posicao).setNome(nome);

                resultadoParticipante.putExtra("posicao", posicao);
                startActivity(resultadoParticipante);
            }
        });
    }
}
