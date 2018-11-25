package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;
import android.content.ContentValues;
import android.database.Cursor;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.EventoContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Evento;
import br.ufjf.dcc196.trabalho1_ramon_douglas.adapter.EventoAdapter;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Participante;
import br.ufjf.dcc196.trabalho1_ramon_douglas.adapter.ParticipanteAdapter;
import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.ParticipanteContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.EventoDAO;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.ParticipanteDAO;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;

    private Button btnNovoParticipante;
    private Button btnNovoEvento;
    private RecyclerView rclParticipantes;
    private RecyclerView rclEventos;
    private static DbHelper dbHelper;

    public static List<Participante> participantes = new ArrayList<Participante>() {{
        Participante p0 = new Participante("Ramon Larivoir", "rlarivoir@gmail.com", "11111111111");
        Participante p1 = new Participante("Douglas Baumgratz", "douglas@gmail.com", "22222222222");
//        Participante p2 = new Participante("Igor Knop", "igor@gmail.com", "3333333333");
//        Participante p3 = new Participante("João da Silva", "joao@gmail.com", "44444444444");
//        Participante p4 = new Participante("José de Souza", "jose@gmail.com", "55555555555");
        add(p0);
        add(p1);
//        add(p2);
//        add(p3);
//        add(p4);
    }};

    public static List<Evento> eventos = new ArrayList<Evento>() {{
        Evento e0 = new Evento("Curso Android", "Igor Knop", "20/10/2018", "20:00", "Curso de introdução ao desenvolvimento android.");
        Evento e1 = new Evento("Palestra Igor", "Igor Knop", "21/10/2018", "17:00", "Palestra sobre clean code.");
//        Evento e2 = new Evento("Curso Java", "Jairo Souza", "22/10/2018", "19:00", "Curso avançado de Java.");
//        Evento e3 = new Evento("Mesa redonda", "Luciana Campos", "21/10/2018", "21:00", "Mesa redonda para debater as novas tendências do mercado.");
        add(e0);
        add(e1);
//        add(e2);
//        add(e3);
//
        e0.getParticipantes().add(participantes.get(0));
        e1.getParticipantes().add(participantes.get(0));
//        e1.getParticipantes().add(participantes.get(3));
//        e2.getParticipantes().add(participantes.get(1));
//        e2.getParticipantes().add(participantes.get(2));
//        e2.getParticipantes().add(participantes.get(3));
//        e3.getParticipantes().add(participantes.get(0));
//        e3.getParticipantes().add(participantes.get(3));
//        e3.getParticipantes().add(participantes.get(4));
//
        participantes.get(0).getEventos().add(e0);
//        participantes.get(0).getEventos().add(e3);
        participantes.get(0).getEventos().add(e1);
//        participantes.get(1).getEventos().add(e2);
//        participantes.get(2).getEventos().add(e2);
//        participantes.get(3).getEventos().add(e3);
//        participantes.get(3).getEventos().add(e1);
//        participantes.get(4).getEventos().add(e3);
//        participantes.get(4).getEventos().add(e2);

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(getApplicationContext());
        final ParticipanteDAO crudParticipante = new ParticipanteDAO(getBaseContext());
        final EventoDAO crudEvento = new EventoDAO(getBaseContext());
        Cursor cursor = crudParticipante.carregaDados();

        participantes = listaParticipantes();
        eventos = listaEventos();

        String[] nomeCampos = new String[] {
                ParticipanteContract.Participante.COLUMN_NAME_NOME,
                ParticipanteContract.Participante.COLUMN_NAME_EMAIL,
                ParticipanteContract.Participante.COLUMN_NAME_CPF
        };

        int[] idViews = new int[] {R.id.edt_nome_completo, R.id.edt_email, R.id.edt_cpf};

//        SimpleCursorAdapter participanteAdapter = new SimpleCursorAdapter(getBaseContext(),
//                R.layout.activity_main,cursor,nomeCampos,idViews, 0);

//        lista = (ListView)findViewById(R.id.listView);
//        lista.setAdapter(adaptador);

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
                crudParticipante.removeParticipante(participantes.get(position).getId());
                participantes.remove(position);
                participanteAdapter.notifyItemRemoved(position);
            }
        });


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
                crudEvento.removeEvento(eventos.get(position).getId());
                eventos.remove(position);
                eventoAdapter.notifyItemRemoved(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.REQUEST_PARTICIPANTE && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoParticipante = data.getExtras();

            Participante p = (Participante) bundleResultadoParticipante.getSerializable("participante");

            participantes.add(p);
            rclParticipantes.getAdapter().notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Participante " + p.getNome() + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == MainActivity.REQUEST_EVENTO && resultCode == Activity.RESULT_OK && data != null) {

            Bundle bundleResultadoEvento = data.getExtras();

            Evento e = (Evento) bundleResultadoEvento.getSerializable("evento");

            eventos.add(e);

            rclEventos.getAdapter().notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Evento " + e.getTitulo() + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public static Cursor getEventos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                EventoContract.Evento.COLUMN_NAME_TITULO
        };
        return db.query(EventoContract.Evento.TABLE_NAME,visao,null,null,null,null,null);
    }

    public static Cursor getParticipantes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                ParticipanteContract.Participante.COLUMN_NAME_NOME
        };
        return db.query(ParticipanteContract.Participante.TABLE_NAME,visao,null,null,null,null,null);
    }

    public static List<Participante> listaParticipantes() {
        List<Participante> participantes = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from participante", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante._ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_EMAIL));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_CPF));
            participantes.add(new Participante(id,nome,email,cpf));
        }
        return participantes;
    }

    public static List<Evento> listaEventos() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from evento", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(EventoContract.Evento._ID));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_TITULO));
            String facilitador = cursor.getString(cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_FACILITADOR));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_DESCRICAO));
            String data = cursor.getString(cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_DATA));
            String hora = cursor.getString(cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_HORA));
            eventos.add(new Evento(id,titulo,facilitador,data,hora,descricao));
        }
        return eventos;
    }
}
