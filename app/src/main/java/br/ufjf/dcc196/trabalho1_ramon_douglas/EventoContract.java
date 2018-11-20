package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.provider.BaseColumns;

public class EventoContract {

    public static final class Evento implements BaseColumns {
        public static final String TABLE_NAME = "evento";
        public static final String COLUMN_NAME_ID= "titulo";
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
            + Evento.COLUMN_NAME_HORA + " TEXT"
            + Evento.COLUMN_NAME_DESCRICAO + " TEXT"
            + ")";

    public final static String DROP_SERIE = "DROP TABLE IF EXISTS " + Evento.TABLE_NAME;
}
