package br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.EventoContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.ParticipanteEventoContract;

public class ParticipanteEventoDAO {


    private SQLiteDatabase db;
    private DbHelper banco;
    private String tabela = ParticipanteEventoContract.ParticipanteEvento.TABLE_NAME;
    private String id = ParticipanteEventoContract.ParticipanteEvento._ID;
    private String id_participante = ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_PARTICIPANTE;
    private String id_evento = ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_EVENTO;

    private ContentValues valores = new ContentValues();
    private String[] campos = {id_participante, id_evento};
    private Cursor cursor;


    public ParticipanteEventoDAO(Context context) {
        banco = new DbHelper(context);
    }

    public void putHelper(ContentValues v, String _id_participante, String _id_evento) {
        v.put(id_participante, _id_participante);
        v.put(id_evento, _id_evento);
    }

    public String insereDado(String _id_participante, String _id_evento) {
        long resultado;
        db = banco.getWritableDatabase();

        putHelper(valores, _id_participante, _id_evento);
        resultado = db.insert(tabela, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Evento Inserido com sucesso";

    }

    public Cursor carregaDados() {
        db = banco.getReadableDatabase();
        cursor = db.query(tabela, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int _id) {
        String where = id + "=" + _id;
        db = banco.getReadableDatabase();
        cursor = db.query(tabela, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int _id, String _id_participante, String _id_evento) {
        String where = id + " = ?";
        String[] args = {String.valueOf(_id)};
        db = banco.getWritableDatabase();
        putHelper(valores, _id_participante, _id_evento);
        db.update(tabela, valores, where, args);
        db.close();
    }

    public void removeEvento(int _id) {
        String where = id + " = ?";
        String[] args = {String.valueOf(_id)};
        db = banco.getWritableDatabase();
        db.delete(tabela, where, args);
        db.close();
    }
}


