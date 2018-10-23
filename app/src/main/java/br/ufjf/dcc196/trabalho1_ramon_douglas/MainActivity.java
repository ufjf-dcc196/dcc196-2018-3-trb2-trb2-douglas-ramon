package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PARTICIPANTE_NOME = "nome";
    public static final String PARTICIPANTE_EMAIL = "email";
    public static final String PARTICIPANTE_CPF = "cpf";
    public static final Evento EVENTO = new Evento();
    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;

    private Button btnNovoParticipante;
    private Button btnNovoEvento;
    private RecyclerView rclParticipantes;
    private RecyclerView rclEventos;

    public static List<String> participantes = new ArrayList<String>(){{
        add("Ramon Larivoir");
        add("Douglas Baumgratz");
        add("Igor Knop");
        add("João da Silva");
        add("José de Souza");
    }};

    public static List<String> eventos = new ArrayList<String>(){{
        add("Curso Android");
        add("Palestra Igor");
        add("Curso Java");
        add("Mesa redonda");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovoParticipante = (Button) findViewById(R.id.btn_novo_participante);
        btnNovoParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParticipanteNovoActivity.class);
                startActivity(intent);
            }
        });

        btnNovoEvento = (Button) findViewById(R.id.btn_novo_evento);
        btnNovoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventoNovoActivity.class);
                startActivity(intent);
            }
        });

        rclParticipantes = (RecyclerView) findViewById(R.id.rcl_participantes);
        rclParticipantes.setLayoutManager(new LinearLayoutManager(this));
        final ParticipanteAdapter participanteAdapter = new ParticipanteAdapter(participantes);
        rclParticipantes.setAdapter(participanteAdapter);
        participanteAdapter.setOnParticipanteClickListener(new ParticipanteAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {
            }
        });
        participanteAdapter.setOnParticipanteLongClickListener(new ParticipanteAdapter.OnParticipanteLongClickListener() {
            @Override
            public void onParticipanteLongClickListener(View view, int position) {
                participantes.remove(position);
                participanteAdapter.notifyItemChanged(position);
            }
        });

        rclEventos = (RecyclerView) findViewById(R.id.rcl_eventos);
        rclEventos.setLayoutManager(new LinearLayoutManager(this));
        final EventoAdapter eventoAdapter = new EventoAdapter(eventos);
        rclEventos.setAdapter(eventoAdapter);
        eventoAdapter.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClickListener(View view, int position) {
                eventos.remove(position);
                eventoAdapter.notifyItemChanged(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MainActivity.REQUEST_PARTICIPANTE && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoParticipante = data.getExtras();
            String nome = bundleResultadoParticipante.getString(MainActivity.PARTICIPANTE_NOME);
            String email = bundleResultadoParticipante.getString(MainActivity.PARTICIPANTE_EMAIL);
            String cpf = bundleResultadoParticipante.getString(MainActivity.PARTICIPANTE_CPF);

            Participante p = new Participante(nome, email, cpf);

            participantes.add(p.getNome());

            Toast.makeText(getApplicationContext(), "Nome: " + p.getNome(), Toast.LENGTH_SHORT).show();

        }
    }
}
