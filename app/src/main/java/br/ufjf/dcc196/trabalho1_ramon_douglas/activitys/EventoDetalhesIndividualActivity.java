package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Evento;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Participante;
import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.ParticipanteEventoDAO;

public class EventoDetalhesIndividualActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnInscrever;
    private EditText txtTitulo;
    private EditText txtFacilitador;
    private EditText txtDescricao;
    private EditText txtData;
    private EditText txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhes_individual);

        txtTitulo = (EditText) findViewById(R.id.txt_titulo_individual);
        txtFacilitador = (EditText) findViewById(R.id.txt_facilitador_individual);
        txtDescricao = (EditText) findViewById(R.id.txt_descricao_individual);
        txtData = (EditText) findViewById(R.id.txt_data_individual);
        txtHora = (EditText) findViewById(R.id.txt_hora_individual);

        Bundle bundle = getIntent().getExtras();
        final int posicao = bundle.getInt("posicao");
        final int idParticipante = bundle.getInt("idParticipante");
        final Evento e = MainActivity.eventos.get(posicao);
        final Participante p = MainActivity.participantes.get(idParticipante);
        txtTitulo.setText(e.getTitulo());
        txtFacilitador.setText(e.getFacilitador());
        txtDescricao.setText(e.getDescricao());
        txtData.setText(e.getData());
        txtHora.setText(e.getHora());

        btnVoltar = (Button) findViewById(R.id.btn_voltar_individual);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoDetalhesIndividualActivity.this, ParticipanteInscreverActivity.class);
                intent.putExtra("posicao", idParticipante);
                startActivity(intent);
            }
        });

        btnInscrever = (Button) findViewById(R.id.btn_inscrever_individual);
        btnInscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoDetalhesIndividualActivity.this, ParticipanteDetalhesActivity.class);
                intent.putExtra("posicao", idParticipante);

                if(p.getEventos().contains(e)) {
                    Toast.makeText(getApplicationContext(), "Participante já está inscrito neste evento", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    ParticipanteEventoDAO crud = new ParticipanteEventoDAO(getBaseContext());
                    crud.insereDado(String.valueOf(p.getId()), String.valueOf(e.getId()));
                    MainActivity.eventos = MainActivity.listaEventos();
                    MainActivity.participantes = MainActivity.listaParticipantes();
                    Toast.makeText(getApplicationContext(), "Inscrito com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}
