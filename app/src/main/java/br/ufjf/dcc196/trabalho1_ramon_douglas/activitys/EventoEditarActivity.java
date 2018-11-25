package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Evento;
import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.EventoDAO;

public class EventoEditarActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnSalvar;
    private EditText edtTitulo;
    private EditText edtFacilitador;
    private EditText edtDescricao;
    private EditText edtData;
    private EditText edtHora;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_editar);

        edtTitulo = (EditText) findViewById(R.id.edt_titulo_editar);
        edtFacilitador = (EditText) findViewById(R.id.edt_facilitador_editar);
        edtDescricao = (EditText) findViewById(R.id.edt_descricao_editar);
        edtData = (EditText) findViewById(R.id.edt_data_editar);
        edtHora = (EditText) findViewById(R.id.edt_hora_editar);

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Evento e = MainActivity.eventos.get(posicao);
        edtTitulo.setText(e.getTitulo());
        edtFacilitador.setText(e.getFacilitador());
        edtDescricao.setText(e.getDescricao());
        edtData.setText(e.getData());
        edtHora.setText(e.getHora());

        btnVoltar = (Button) findViewById(R.id.btn_voltar_evento_editar_editar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoEditarActivity.this, EventoDetalhesActivity.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });

        btnSalvar = (Button) findViewById(R.id.btn_salvar_evento_editar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundleDetalhes = getIntent().getExtras();
                final int posicao = bundleDetalhes.getInt("posicao");
                EventoDAO crud = new EventoDAO(getBaseContext());

                String titulo = edtTitulo.getText().toString();
                String descricao = edtDescricao.getText().toString();
                String facilitador = edtFacilitador.getText().toString();
                String data = edtData.getText().toString();
                String hora = edtHora.getText().toString();

                crud.alteraRegistro(MainActivity.eventos.get(posicao).getId(), titulo, descricao, facilitador, data, hora);
                Toast.makeText(getApplicationContext(), "Evento atualizado com Sucesso!", Toast.LENGTH_LONG).show();

                MainActivity.eventos = MainActivity.listaEventos();


                Intent resultadoEventoEditar = new Intent(EventoEditarActivity.this, EventoDetalhesActivity.class);
                resultadoEventoEditar.putExtra("posicao", posicao);
                startActivity(resultadoEventoEditar);


            }
        });
    }
}
