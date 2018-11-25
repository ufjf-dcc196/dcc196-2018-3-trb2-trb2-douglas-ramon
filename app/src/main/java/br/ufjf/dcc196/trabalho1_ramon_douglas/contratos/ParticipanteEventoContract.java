package br.ufjf.dcc196.trabalho1_ramon_douglas.contratos;

import android.provider.BaseColumns;

public class ParticipanteEventoContract {

    public static final class ParticipanteEvento implements BaseColumns {
        public static final String TABLE_NAME = "participante_evento";
        public static final String COLUMN_NAME_ID_PARTICIPANTE = "id_participante";
        public static final String COLUMN_NAME_ID_EVENTO = "id_evento";
    }

    public static final String CREATE_PARTICIPANTE_EVENTO= "CREATE TABLE " + ParticipanteEvento.TABLE_NAME + " ("
            + ParticipanteEvento._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ParticipanteEvento.COLUMN_NAME_ID_PARTICIPANTE + " INTEGER, "
            + ParticipanteEvento.COLUMN_NAME_ID_EVENTO + " INTEGER, "
            + "FOREIGN KEY (" + ParticipanteEvento.COLUMN_NAME_ID_PARTICIPANTE + ") REFERENCES " + ParticipanteContract.Participante.TABLE_NAME + "(" + ParticipanteContract.Participante._ID + "), "
            + "FOREIGN KEY (" + ParticipanteEvento.COLUMN_NAME_ID_EVENTO + ") REFERENCES " + EventoContract.Evento.TABLE_NAME + "(" + EventoContract.Evento._ID + ")";

    public final static String DROP_PARTICIPANTE_EVENTO= "DROP TABLE IF EXISTS " + ParticipanteEvento.TABLE_NAME;

    public ParticipanteEventoContract() {
    }
}
