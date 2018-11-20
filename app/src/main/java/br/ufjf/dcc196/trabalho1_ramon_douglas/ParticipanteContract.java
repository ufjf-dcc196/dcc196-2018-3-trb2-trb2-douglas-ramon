package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.provider.BaseColumns;

public class ParticipanteContract {

    public static final class Participante implements BaseColumns {
        public static final String TABLE_NAME = "participante";
        public static final String COLUMN_NAME_ID= "_ID";
        public static final String COLUMN_NAME_NOME= "nome";
        public static final String COLUMN_NAME_EMAIL= "email";
        public static final String COLUMN_NAME_CPF= "cpf";
    }

    public static final String CREATE_PARTICIPANTE= "CREATE TABLE " + Participante.TABLE_NAME + " ("
            + Participante._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Participante.COLUMN_NAME_NOME + " TEXT, "
            + Participante.COLUMN_NAME_EMAIL + " TEXT, "
            + Participante.COLUMN_NAME_CPF + " TEXT "
            + ")";

    public final static String DROP_PARTICIPANTE= "DROP TABLE IF EXISTS " + Participante.TABLE_NAME;
}
