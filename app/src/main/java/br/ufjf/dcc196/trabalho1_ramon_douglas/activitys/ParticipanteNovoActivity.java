package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Participante;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.ParticipanteDAO;

public class ParticipanteNovoActivity extends AppCompatActivity {

    private Button btnCadastrarParticipante;
    private Button btnVoltar;
    private EditText edtNomeCompleto;
    private EditText edtEmail;
    private EditText edtCpf;
    private DbHelper dbHelper;

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

                ParticipanteDAO crud = new ParticipanteDAO(getBaseContext());

                String nome = edtNomeCompleto.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCpf.getText().toString();

                crud.insereDado(nome, email, cpf);
                Intent resultadoParticipante = new Intent();

                if(nome.equals("") || email.equals("") || cpf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Participante p = new Participante(nome, email, cpf);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("participante", p);

                    resultadoParticipante.putExtras(bundle);
                    setResult(Activity.RESULT_OK, resultadoParticipante);

                    finish();
                }

            }
        });
    }
}
