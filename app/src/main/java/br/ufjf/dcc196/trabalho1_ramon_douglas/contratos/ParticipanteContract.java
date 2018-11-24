package br.ufjf.dcc196.trabalho1_ramon_douglas.contratos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia.DbHelper;

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

    public ParticipanteContract() {
    }
//
//    public static void insereDadosPrimarios(DbHelper dbHelper) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues valores = new ContentValues();
//        valores.put(Participante.COLUMN_NAME_NOME, "Ramon Larivoir");
//        valores.put(Participante.COLUMN_NAME_CPF, "111111111");
//        valores.put(Participante.COLUMN_NAME_EMAIL, "rlarivoir@gmail.com");
//        long id = db.insert(Participante.TABLE_NAME, null, valores);
//        Log.i("DBINFO", "registro criado com id: " + id + " - nome: " + valores.get(Participante.COLUMN_NAME_NOME) + " - email: " + valores.get(Participante.COLUMN_NAME_CPF) + " - CPF: " + valores.get(Participante.COLUMN_NAME_CPF));
//    }
}
