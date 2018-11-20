package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.provider.BaseColumns;

public class ParticipanteContract {

    public static final class Participante implements BaseColumns {
        public static final String TABLE_NAME = "participante";
        public static final String COLUMN_NAME_TITULO = "nome";
        public static final String COLUMN_NAME_FACILITADOR = "email";
        public static final String COLUMN_NAME_DATA = "cpf";
    }

    public static final String CREATE_PARTICIPANTE= "CREATE TABLE " + Participante.TABLE_NAME + " ("
            + Participante._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Participante.COLUMN_NAME_TITULO + " TEXT, "
            + Participante.COLUMN_NAME_FACILITADOR + " TEXT, "
            + Participante.COLUMN_NAME_DATA + " TEXT "
            + ")";

    public final static String DROP_PARTICIPANTE= "DROP TABLE IF EXISTS " + Participante.TABLE_NAME;
}
