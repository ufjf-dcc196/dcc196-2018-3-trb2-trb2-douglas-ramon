package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edtTitulo = (EditText) findViewById(R.id.edt_titulo_edt);
        edtFacilitador = (EditText) findViewById(R.id.edt_facilitador_edt);
        edtDescricao = (EditText) findViewById(R.id.edt_descricao_edt);
        edtData = (EditText) findViewById(R.id.edt_data_edt);
        edtHora = (EditText) findViewById(R.id.edt_hora_edt);

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Evento e = MainActivity.eventos.get(posicao);
        edtTitulo.setText(e.getTitulo());
        edtFacilitador.setText(e.getFacilitador());
        edtDescricao.setText(e.getDescricao());
        edtData.setText(e.getData());
        edtHora.setText(e.getHora());

        btnVoltar = (Button) findViewById(R.id.btn_voltar_evento_editar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoEditarActivity.this, EventoDetalhesActivity.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });

        btnSalvar = (Button) findViewById(R.id.btn_salvar_evento);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoEvento = new Intent(EventoEditarActivity.this, EventoDetalhesActivity.class);

                String titulo = edtTitulo.getText().toString();
                String facilitador = edtFacilitador.getText().toString();
                String descricao = edtDescricao.getText().toString();
                String data = edtData.getText().toString();
                String hora = edtHora.getText().toString();

                MainActivity.eventos.get(posicao).setTitulo(titulo);
                MainActivity.eventos.get(posicao).setFacilitador(facilitador);
                MainActivity.eventos.get(posicao).setDescricao(descricao);
                MainActivity.eventos.get(posicao).setData(data);
                MainActivity.eventos.get(posicao).setHora(hora);

                resultadoEvento.putExtra("posicao", posicao);
                startActivity(resultadoEvento);
            }
        });
    }
}
