package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNovoParticipante;
    private Button btnNovoEvento;

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
    }
}
