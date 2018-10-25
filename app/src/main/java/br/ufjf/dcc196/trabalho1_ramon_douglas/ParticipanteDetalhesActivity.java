package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ParticipanteDetalhesActivity extends AppCompatActivity {

//    public static final int REQUEST_EDITAR = 1;

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
        final int posicao = bundleDetalhes.getInt("posicao");
        final Participante p = MainActivity.participantes.get(posicao);
        txtNome.setText(p.getNome());
        txtEmail.setText(p.getEmail());
        txtCpf.setText(p.getCpf());

        rclEventosInscritos = (RecyclerView) findViewById(R.id.rcl_participantes_inscritos);
        rclEventosInscritos.setLayoutManager(new LinearLayoutManager(this));
        final EventoAdapter eventoAdapter = new EventoAdapter(p.getEventos());
        rclEventosInscritos.setAdapter(eventoAdapter);
        eventoAdapter.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClickListener(View view, int position) {
                int idEvento = MainActivity.eventos.indexOf(p.getEventos().get(position));
                MainActivity.eventos.get(idEvento).getParticipantes().remove(p);
                MainActivity.participantes.get(posicao).getEventos().remove(position);
                Toast.makeText(getApplicationContext(),"Evento removido com sucesso!", Toast.LENGTH_SHORT).show();
                eventoAdapter.notifyItemRemoved(position);
            }
        });

        btnEditar = (Button) findViewById(R.id.btn_editar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEditar = new Intent(ParticipanteDetalhesActivity.this, ParticipanteEditarActivity.class);
                intentEditar.putExtra("posicao", posicao);
                startActivity(intentEditar);
            }
        });

        btnInscrever = (Button) findViewById(R.id.btn_inscrever);
        btnInscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipanteDetalhesActivity.this, ParticipanteInscreverActivity.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });
    }
}
