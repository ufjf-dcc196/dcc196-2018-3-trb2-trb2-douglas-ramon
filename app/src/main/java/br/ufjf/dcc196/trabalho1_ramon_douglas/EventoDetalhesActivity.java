package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventoDetalhesActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnEditar;
    private EditText txtTitulo;
    private EditText txtFacilitador;
    private EditText txtDescricao;
    private EditText txtData;
    private EditText txtHora;
    private RecyclerView rclParticipantesInscritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhes);

        txtTitulo = (EditText) findViewById(R.id.txt_titulo);
        txtFacilitador = (EditText) findViewById(R.id.txt_facilitador);
        txtDescricao = (EditText) findViewById(R.id.txt_descricao);
        txtData = (EditText) findViewById(R.id.txt_data);
        txtHora = (EditText) findViewById(R.id.txt_hora);

        btnVoltar = (Button) findViewById(R.id.btn_voltar_evento_editar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoDetalhesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Evento e = MainActivity.eventos.get(posicao);
        txtTitulo.setText(e.getTitulo());
        txtFacilitador.setText(e.getFacilitador());
        txtDescricao.setText(e.getDescricao());
        txtData.setText(e.getData());
        txtHora.setText(e.getHora());

        rclParticipantesInscritos = (RecyclerView) findViewById(R.id.rcl_participantes_inscritos);
        rclParticipantesInscritos.setLayoutManager(new LinearLayoutManager(this));
        final ParticipanteAdapter participanteAdapter = new ParticipanteAdapter(e.getParticipantes());
        rclParticipantesInscritos.setAdapter(participanteAdapter);

        btnEditar = (Button) findViewById(R.id.btn_editar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEditar = new Intent(EventoDetalhesActivity.this, EventoEditarActivity.class);
                intentEditar.putExtra("posicao", posicao);
                startActivity(intentEditar);
            }
        });
    }
}
