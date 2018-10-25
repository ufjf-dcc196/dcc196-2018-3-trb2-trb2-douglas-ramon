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

    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;

    private Button btnNovoParticipante;
    private Button btnNovoEvento;
    private RecyclerView rclParticipantes;
    private RecyclerView rclEventos;

    public static List<Participante> participantes = new ArrayList<Participante>(){{
        Participante p0 = new Participante("Ramon Larivoir", "rlarivoir@gmail.com", "11111111111");
        Participante p1 = new Participante("Douglas Baumgratz", "douglas@gmail.com", "22222222222");
        Participante p2 = new Participante("Igor Knop", "igor@gmail.com", "3333333333");
        Participante p3 = new Participante("João da Silva", "joao@gmail.com", "44444444444");
        Participante p4 = new Participante("José de Souza", "jose@gmail.com", "55555555555");
        add(p0);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
    }};

    public static List<Evento> eventos = new ArrayList<Evento>(){{
        Evento e0 = new Evento("Curso Android", "Igor Knop", "20/10/2018" , "20:00" , "Curso de introdução ao desenvolvimento android.");
        Evento e1 = new Evento("Palestra Igor", "Igor Knop", "21/10/2018" , "17:00" , "Palestra sobre clean code.");
        Evento e2 = new Evento("Curso Java", "Jairo Souza", "22/10/2018" , "19:00" , "Curso avançado de Java.");
        Evento e3 = new Evento("Mesa redonda", "Luciana Campos", "21/10/2018" , "21:00" , "Mesa redonda para debater as novas tendências do mercado.");
        add(e0);
        add(e1);
        add(e2);
        add(e3);

        e0.getParticipantes().add(participantes.get(0));
        e1.getParticipantes().add(participantes.get(0));
        e1.getParticipantes().add(participantes.get(3));
        e2.getParticipantes().add(participantes.get(1));
        e2.getParticipantes().add(participantes.get(2));
        e2.getParticipantes().add(participantes.get(3));
        e3.getParticipantes().add(participantes.get(0));
        e3.getParticipantes().add(participantes.get(3));
        e3.getParticipantes().add(participantes.get(4));

        participantes.get(0).getEventos().add(e0);
        participantes.get(0).getEventos().add(e3);
        participantes.get(0).getEventos().add(e1);
        participantes.get(1).getEventos().add(e2);
        participantes.get(2).getEventos().add(e2);
        participantes.get(3).getEventos().add(e3);
        participantes.get(3).getEventos().add(e1);
        participantes.get(4).getEventos().add(e3);
        participantes.get(4).getEventos().add(e2);

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
                intentPartipanteDetalhe.putExtra("posicao", position);
                startActivity(intentPartipanteDetalhe);
            }
        });
        participanteAdapter.setOnParticipanteLongClickListener(new ParticipanteAdapter.OnParticipanteLongClickListener() {
            @Override
            public void onParticipanteLongClickListener(View view, int position) {
                participantes.remove(position);
                participanteAdapter.notifyItemRemoved(position);
            }
        });

        rclEventos = (RecyclerView) findViewById(R.id.rcl_eventos);
        rclEventos.setLayoutManager(new LinearLayoutManager(this));
        final EventoAdapter eventoAdapter = new EventoAdapter(eventos);
        rclEventos.setAdapter(eventoAdapter);
        eventoAdapter.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View view, int position) {
                Intent intentPartipanteDetalhe = new Intent(MainActivity.this, EventoDetalhesActivity.class);
                intentPartipanteDetalhe.putExtra("posicao", position);
                startActivity(intentPartipanteDetalhe);
            }
        });
        eventoAdapter.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClickListener(View view, int position) {
                eventos.remove(position);
                eventoAdapter.notifyItemRemoved(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MainActivity.REQUEST_PARTICIPANTE && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoParticipante = data.getExtras();

            Participante p = (Participante) bundleResultadoParticipante.getSerializable("participante");

        } else if(requestCode == MainActivity.REQUEST_EVENTO && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoEvento = data.getExtras();

            Evento e = (Evento) bundleResultadoEvento.getSerializable("evento");

        }
    }
}
