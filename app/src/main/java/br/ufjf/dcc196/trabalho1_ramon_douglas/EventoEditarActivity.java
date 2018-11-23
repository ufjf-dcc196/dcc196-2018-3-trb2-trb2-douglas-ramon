package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(EventoContract.Evento.COLUMN_NAME_TITULO, edtTitulo.getText().toString());
                valores.put(EventoContract.Evento.COLUMN_NAME_DATA, edtData.getText().toString());
                valores.put(EventoContract.Evento.COLUMN_NAME_HORA, edtHora.getText().toString());
                valores.put(EventoContract.Evento.COLUMN_NAME_FACILITADOR, edtFacilitador.getText().toString());
                valores.put(EventoContract.Evento.COLUMN_NAME_DESCRICAO, edtDescricao.getText().toString());
                long id = db.insert(EventoContract.Evento.COLUMN_NAME_ID, null, valores);
                Intent intent = new Intent(EventoEditarActivity.this, MainActivity.class);
                startActivity(intent);


                /* Código sem banco */
                /*
                Intent resultadoEventoEditar = new Intent(EventoEditarActivity.this, EventoDetalhesActivity.class);
                resultadoEventoEditar.putExtra("posicao", posicao);

                String titulo = edtTitulo.getText().toString();
                String facilitador = edtFacilitador.getText().toString();
                String descricao = edtDescricao.getText().toString();
                String data = edtData.getText().toString();
                String hora = edtHora.getText().toString();

                if(titulo.equals("") || descricao.equals("") || facilitador.equals("") || data.equals("") || hora.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoEventoEditar);
                } else {
                    MainActivity.eventos.get(posicao).setTitulo(titulo);
                    MainActivity.eventos.get(posicao).setFacilitador(facilitador);
                    MainActivity.eventos.get(posicao).setFacilitador(descricao);
                    MainActivity.eventos.get(posicao).setFacilitador(data);
                    MainActivity.eventos.get(posicao).setFacilitador(hora);

                    Toast.makeText(getApplicationContext(), "Evento atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoEventoEditar);
                }
                */

            }
        });
    }
}
