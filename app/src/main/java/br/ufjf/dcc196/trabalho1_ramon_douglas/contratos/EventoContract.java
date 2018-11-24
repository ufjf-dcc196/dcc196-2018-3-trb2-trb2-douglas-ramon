package br.ufjf.dcc196.trabalho1_ramon_douglas.contratos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;

public class EventoContract {

    public static final class Evento implements BaseColumns {
        public static final String TABLE_NAME = "evento";
        public static final String COLUMN_NAME_ID= "_ID";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_FACILITADOR = "facilitador";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_HORA = "hora";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
    }

    public static final String CREATE_EVENTO = "CREATE TABLE " + Evento.TABLE_NAME + " ("
            + Evento._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Evento.COLUMN_NAME_TITULO + " TEXT, "
            + Evento.COLUMN_NAME_FACILITADOR + " TEXT, "
            + Evento.COLUMN_NAME_DATA + " TEXT, "
            + Evento.COLUMN_NAME_HORA + " TEXT, "
            + Evento.COLUMN_NAME_DESCRICAO + " TEXT"
            + ")";

    public final static String DROP_EVENTO = "DROP TABLE IF EXISTS " + Evento.TABLE_NAME;

    public EventoContract() {
    }
//
//    public static void insereDadosPrimarios(DbHelper dbHelper) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues valores = new ContentValues();
//        valores.put(Evento.COLUMN_NAME_TITULO, "Curso Android");
//        valores.put(Evento.COLUMN_NAME_FACILITADOR, "Igor Knop");
//        valores.put(Evento.COLUMN_NAME_DESCRICAO, "Curso de introdução ao desenvolvimento android.");
//        valores.put(Evento.COLUMN_NAME_DATA, "20/10/2018");
//        valores.put(Evento.COLUMN_NAME_HORA, "20:00");
//        long id = db.insert(Evento.TABLE_NAME, null, valores);
//        Log.i("DBINFO", "registro criado com id: " + id);
//        db.close();
//    }
}
