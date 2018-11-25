package br.ufjf.dcc196.trabalho1_ramon_douglas.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Evento;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;
import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.EventoDAO;

public class EventoNovoActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnCadastrarEvento;
    private EditText edtTitulo;
    private EditText edtData;
    private EditText edtHora;
    private EditText edtFacilitador;
    private EditText edtDescricao;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_novo);

        btnVoltar = (Button) findViewById(R.id.btn_voltar_evento_novo);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventoNovoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        edtTitulo = (EditText) findViewById(R.id.edt_titulo);
        edtDescricao = (EditText) findViewById(R.id.edt_descricao);
        edtFacilitador = (EditText) findViewById(R.id.edt_facilitador);
        edtHora = (EditText) findViewById(R.id.edt_hora);
        edtData = (EditText) findViewById(R.id.edt_data);

        btnCadastrarEvento = (Button) findViewById(R.id.btn_cadastrar_evento);
        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EventoDAO crud = new EventoDAO(getBaseContext());

                String titulo = edtTitulo.getText().toString();
                String descricao = edtDescricao.getText().toString();
                String facilitador = edtFacilitador.getText().toString();
                String data = edtData.getText().toString();
                String hora = edtHora.getText().toString();

                Intent resultadoEvento = new Intent();

                if (titulo.equals("") || descricao.equals("") || facilitador.equals("") || data.equals("") || hora.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    crud.insereDado(titulo, descricao, facilitador, data, hora);
                    Evento e = new Evento(titulo, facilitador, data, hora, descricao);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("evento", e);

                    resultadoEvento.putExtras(bundle);
                    setResult(Activity.RESULT_OK, resultadoEvento);

                    finish();
                }


            }
        });
    }
}
