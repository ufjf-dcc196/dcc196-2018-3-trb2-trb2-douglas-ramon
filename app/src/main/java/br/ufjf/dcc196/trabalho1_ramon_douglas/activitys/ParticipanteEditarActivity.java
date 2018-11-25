package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Participante;
import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.ParticipanteDAO;

public class ParticipanteEditarActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnSalvar;
    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtEmail;
    private DbHelper dbHelper;

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

                Bundle bundleDetalhes = getIntent().getExtras();
                final int posicao = bundleDetalhes.getInt("posicao");
                ParticipanteDAO crud = new ParticipanteDAO(getBaseContext());

                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCpf.getText().toString();

                crud.alteraRegistro(MainActivity.participantes.get(posicao).getId(), nome, email, cpf);
                Toast.makeText(getApplicationContext(), "Participante atualizado com Sucesso!", Toast.LENGTH_LONG).show();

                MainActivity.participantes = MainActivity.listaParticipantes();

                Intent resultadoParticipante = new Intent(ParticipanteEditarActivity.this, ParticipanteDetalhesActivity.class);
                resultadoParticipante.putExtra("posicao", posicao);
                startActivity(resultadoParticipante);


            }
        });
    }
}
