package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParticipanteDetalhesActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnEditar;
    private Button btnInscrever;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtCpf;
    private RecyclerView rclEventosInscritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_detalhes);

        txtNome = (EditText) findViewById(R.id.txt_nome);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtCpf = (EditText) findViewById(R.id.txt_cpf);

        btnVoltar = (Button) findViewById(R.id.btn_voltar_participante_editar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipanteDetalhesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundleDetalhes = getIntent().getExtras();
        int posicao = bundleDetalhes.getInt("idParticipante");
        Participante p = MainActivity.participantes.get(posicao);
        txtNome.setText(p.getNome());
        txtEmail.setText(p.getEmail());
        txtCpf.setText(p.getCpf());

        rclEventosInscritos = (RecyclerView) findViewById(R.id.rcl_eventos_inscritos);
        rclEventosInscritos.setLayoutManager(new LinearLayoutManager(this));
        final EventoAdapter eventoAdapter = new EventoAdapter(p.getEventos());
        rclEventosInscritos.setAdapter(eventoAdapter);
    }
}
