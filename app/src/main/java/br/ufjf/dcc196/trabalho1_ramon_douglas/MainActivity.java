package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
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

    private Button btnNovoParticipante;
    private Button btnNovoEvento;
    private RecyclerView rclParticipantes;

    public static List<String> participantes = new ArrayList<String>(){{
        add("Ramon Larivoir");
        add("Douglas Baumgratz");
        add("Igor Knop");
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
        final ParticipanteAdapter adapter = new ParticipanteAdapter(participantes);
        rclParticipantes.setAdapter(adapter);
    }
}
