package br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.EventoContract;

public class EventoDAO {


    private SQLiteDatabase db;
    private DbHelper banco;
    private String tabela = EventoContract.Evento.TABLE_NAME;
    private String titulo = EventoContract.Evento.COLUMN_NAME_TITULO;
    private String descricao = EventoContract.Evento.COLUMN_NAME_DESCRICAO;
    private String facilitador = EventoContract.Evento.COLUMN_NAME_FACILITADOR;
    private String data = EventoContract.Evento.COLUMN_NAME_DATA;
    private String hora = EventoContract.Evento.COLUMN_NAME_HORA;
    private String id = EventoContract.Evento.COLUMN_NAME_ID;

    private ContentValues valores = new ContentValues();
    private String[] campos = {titulo, descricao, facilitador, data, hora};
    private Cursor cursor;


    public EventoDAO(Context context) {
        banco = new DbHelper(context);
    }

    public void putHelper(ContentValues v, String _titulo, String _descricao, String _facilitador, String _data, String _hora) {
        v.put(titulo, _titulo);
        v.put(descricao, _descricao);
        v.put(facilitador, _facilitador);
        v.put(data, _data);
        v.put(hora, _hora);
    }

    public String insereDado(String _titulo, String _descricao, String _facilitador, String _data, String _hora) {
        long resultado;
        db = banco.getWritableDatabase();

        putHelper(valores, _titulo, _descricao, _facilitador, _data, _hora);
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

    public void alteraRegistro(int _id, String _titulo, String _descricao, String _facilitador, String _data, String _hora) {
        String where = id + "=" + _id;
        db = banco.getWritableDatabase();
        putHelper(valores, _titulo, _descricao, _facilitador, _data, _hora);
        db.update(tabela, valores, where, null);
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


