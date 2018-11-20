package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.provider.BaseColumns;

public class ParticipanteContract {

    public static final class Participante implements BaseColumns {
        public static final String TABLE_NAME = "participante";
        public static final String COLUMN_NAME_NOME= "nome";
        public static final String COLUMN_NAME_EMAIL= "email";
        public static final String COLUMN_NAME_CPF= "cpf";
    }

    public static final String CREATE_EVENTO = "CREATE TABLE " + Participante.TABLE_NAME + " ("
            + Participante.COLUMN_NAME_TITULO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Participante.COLUMN_NAME_FACILITADOR + " TEXT, "
            + Participante.COLUMN_NAME_DATA + " DATE, "
            + Participante.COLUMN_NAME_HORA + " TIME"
            + Participante.COLUMN_NAME_DESCRICAO + " TEXT"
            + ")";

    public final static String DROP_SERIE = "DROP TABLE IF EXISTS " + Participante.TABLE_NAME;
}
