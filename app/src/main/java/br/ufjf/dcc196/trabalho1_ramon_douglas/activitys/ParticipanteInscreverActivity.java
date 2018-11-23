package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import br.ufjf.dcc196.trabalho1_ramon_douglas.adapter.EventoAdapter;
import br.ufjf.dcc196.trabalho1_ramon_douglas.R;

public class ParticipanteInscreverActivity extends AppCompatActivity {

    private Button btnVoltar;
    private RecyclerView rclEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_inscrever);

        Bundle bundle = getIntent().getExtras();
        final int posicao = bundle.getInt("posicao");

        btnVoltar = (Button) findViewById(R.id.btn_voltar_inscrever);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipanteInscreverActivity.this, ParticipanteDetalhesActivity.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });

        rclEventos = (RecyclerView) findViewById(R.id.rcl_eventos_increver);
        rclEventos.setLayoutManager(new LinearLayoutManager(this));
        final EventoAdapter eventoAdapter = new EventoAdapter(MainActivity.eventos);
        rclEventos.setAdapter(eventoAdapter);
        eventoAdapter.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View view, int position) {
                Intent intent = new Intent(ParticipanteInscreverActivity.this, EventoDetalhesIndividualActivity.class);
                intent.putExtra("posicao", position);
                int idParticipante = posicao;
                intent.putExtra("idParticipante", idParticipante);
                startActivity(intent);
            }
        });
    }
}
