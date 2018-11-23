package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class ParticipanteDAO {


    private SQLiteDatabase db;
    private DbHelper banco;
    private String tabela = ParticipanteContract.Participante.TABLE_NAME;
    private String nome = ParticipanteContract.Participante.COLUMN_NAME_NOME;
    private String email = ParticipanteContract.Participante.COLUMN_NAME_EMAIL;
    private String cpf = ParticipanteContract.Participante.COLUMN_NAME_CPF;
    String[] campos = {nome, email, cpf};


    public ParticipanteDAO(Context context) {
        banco = new DbHelper(context);
    }

    public String insereDado(String _nome, String _email, String _cpf) {
        ContentValues valores = new ContentValues();
        long resultado;
        db = banco.getWritableDatabase();

        valores.put(nome, _nome);
        valores.put(email, _email);
        valores.put(cpf, _cpf);

        resultado = db.insert(tabela, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Participante Inserido com sucesso";

    }

    public Cursor carregaDados() {
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.query(tabela, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id) {
        Cursor cursor;
        String where = ParticipanteContract.Participante.COLUMN_NAME_ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(tabela, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}


