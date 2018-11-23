package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class ParticipanteController {


    private SQLiteDatabase db;
    private DbHelper banco;

    public ParticipanteController(Context context) {
        banco = new DbHelper(context);
    }

    public String insereDado(String nome, String email, String cpf) {
        ContentValues valores = new ContentValues();
        long resultado;
        db = banco.getWritableDatabase();


        valores.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, nome);
        valores.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, email);
        valores.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, cpf);

        resultado = db.insert(ParticipanteContract.Participante.TABLE_NAME, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Participante Inserido com sucesso";

    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {
                ParticipanteContract.Participante.COLUMN_NAME_NOME,
                ParticipanteContract.Participante.COLUMN_NAME_EMAIL,
                ParticipanteContract.Participante.COLUMN_NAME_CPF
        };

        db = banco.getReadableDatabase();
        cursor = db.query(ParticipanteContract.Participante.TABLE_NAME, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}


