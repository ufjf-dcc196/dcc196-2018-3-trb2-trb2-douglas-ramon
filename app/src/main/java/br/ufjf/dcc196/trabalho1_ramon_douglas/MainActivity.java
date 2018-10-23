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
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;

    private Button btnNovoParticipante;
    private Button btnNovoEvento;
    private RecyclerView rclParticipantes;
    private RecyclerView rclEventos;

    public static HashMap<String, Participante> participantes = new HashMap<String, Participante>(){{
        Participante p1 = new Participante("Ramon Larivoir", "rlarivoir@gmail.com", "11111111111");
        Participante p2 = new Participante("Douglas Baumgratz", "douglas@gmail.com", "22222222222");
        Participante p3 = new Participante("Igor Knop", "igor@gmail.com", "3333333333");
        Participante p4 = new Participante("João da Silva", "joao@gmail.com", "44444444444");
        Participante p5 = new Participante("José de Souza", "jose@gmail.com", "55555555555");
        put(p1.getCpf(), p1);
        put(p2.getCpf(), p2);
        put(p3.getCpf(), p3);
        put(p4.getCpf(), p4);
        put(p5.getCpf(), p5);
    }};

    public static HashMap<String, Evento> eventos = new HashMap<String, Evento>(){{
        Evento e1 = new Evento("Curso Android", "Igor Knop", "20/10/2018" , "20:00" , "Curso de introdução ao desenvolvimento android.");
        Evento e2 = new Evento("Palestra Igor", "Igor Knop", "21/10/2018" , "17:00" , "Palestra sobre clean code.");
        Evento e3 = new Evento("Curso Java", "Jairo Souza", "22/10/2018" , "19:00" , "Curso avançado de Java.");
        Evento e4 = new Evento("Mesa redonda", "Luciana Campos", "21/10/2018" , "21:00" , "Mesa redonda para debater as novas tendências do mercado.");
        put(e1.getTitulo(), e1);
        put(e2.getTitulo(), e2);
        put(e3.getTitulo(), e3);
        put(e4.getTitulo(), e4);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovoParticipante = (Button) findViewById(R.id.btn_novo_participante);
        btnNovoParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentParticipante = new Intent(MainActivity.this, ParticipanteNovoActivity.class);
                startActivityForResult(intentParticipante, MainActivity.REQUEST_PARTICIPANTE);
            }
        });

        btnNovoEvento = (Button) findViewById(R.id.btn_novo_evento);
        btnNovoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvento = new Intent(MainActivity.this, EventoNovoActivity.class);
                startActivityForResult(intentEvento, MainActivity.REQUEST_EVENTO);
            }
        });

        rclParticipantes = (RecyclerView) findViewById(R.id.rcl_participantes);
        rclParticipantes.setLayoutManager(new LinearLayoutManager(this));
        final ParticipanteAdapter participanteAdapter = new ParticipanteAdapter(participantes);
        rclParticipantes.setAdapter(participanteAdapter);
        participanteAdapter.setOnParticipanteClickListener(new ParticipanteAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {
                Intent intentPartipanteDetalhe = new Intent(MainActivity.this, ParticipanteDetalhesActivity.class);
                startActivity(intentPartipanteDetalhe);
            }
        });
        participanteAdapter.setOnParticipanteLongClickListener(new ParticipanteAdapter.OnParticipanteLongClickListener() {
            @Override
            public void onParticipanteLongClickListener(View view, int position) {
//                participantes.remove(position);
//                participanteAdapter.notifyItemChanged(position);
            }
        });

        rclEventos = (RecyclerView) findViewById(R.id.rcl_eventos);
        rclEventos.setLayoutManager(new LinearLayoutManager(this));
        final EventoAdapter eventoAdapter = new EventoAdapter(eventos);
        rclEventos.setAdapter(eventoAdapter);
        eventoAdapter.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View view, int position) {

            }
        });
        eventoAdapter.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClickListener(View view, int position) {
//                eventos.remove(position);
//                eventoAdapter.notifyItemChanged(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MainActivity.REQUEST_PARTICIPANTE && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoParticipante = data.getExtras();

            Participante p = (Participante) bundleResultadoParticipante.getSerializable("participante");

            Toast.makeText(getApplicationContext(), "Nome: " + p.getNome(), Toast.LENGTH_SHORT).show();
        } else if(requestCode == MainActivity.REQUEST_EVENTO && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoEvento = data.getExtras();

            Evento e = (Evento) bundleResultadoEvento.getSerializable("evento");

            Toast.makeText(getApplicationContext(), "Título: " + e.getTitulo(), Toast.LENGTH_SHORT).show();
        }
    }
}
