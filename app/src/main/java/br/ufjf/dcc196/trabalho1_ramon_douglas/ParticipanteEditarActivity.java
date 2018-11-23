package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, edtNome.getText().toString());
                valores.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, edtEmail.getText().toString());
                valores.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, edtCpf.getText().toString());
                long id = db.insert(ParticipanteContract.Participante.COLUMN_NAME_ID, null, valores);
                Intent intent = new Intent(ParticipanteEditarActivity.this, MainActivity.class);
                startActivity(intent);


                /* Código sem banco */
                /*
                Intent resultadoParticipante = new Intent(ParticipanteEditarActivity.this, ParticipanteDetalhesActivity.class);
                resultadoParticipante.putExtra("posicao", posicao);

                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCpf.getText().toString();

                if(nome.equals("") || email.equals("") || cpf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoParticipante);
                } else {

                    MainActivity.participantes.get(posicao).setCpf(cpf);
                    MainActivity.participantes.get(posicao).setEmail(email);
                    MainActivity.participantes.get(posicao).setNome(nome);

                    Toast.makeText(getApplicationContext(), "Participante atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoParticipante);
                }
                */

            }
        });
    }
}
