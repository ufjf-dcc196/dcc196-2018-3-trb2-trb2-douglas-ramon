package br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.EventoContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.ParticipanteContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.ParticipanteEventoContract;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Eventos.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EventoContract.CREATE_EVENTO);
        sqLiteDatabase.execSQL(ParticipanteContract.CREATE_PARTICIPANTE);
        sqLiteDatabase.execSQL(ParticipanteEventoContract.CREATE_PARTICIPANTE_EVENTO);
        insereDadosIniciais(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ParticipanteContract.DROP_PARTICIPANTE);
        sqLiteDatabase.execSQL(EventoContract.DROP_EVENTO);
        sqLiteDatabase.execSQL(ParticipanteEventoContract.DROP_PARTICIPANTE_EVENTO);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void insereDadosIniciais(SQLiteDatabase sqLiteDatabase) {
        //Evento 1
        ContentValues valores1 = new ContentValues();
        valores1.put(EventoContract.Evento.COLUMN_NAME_TITULO, "Curso Android");
        valores1.put(EventoContract.Evento.COLUMN_NAME_FACILITADOR, "Igor Knop");
        valores1.put(EventoContract.Evento.COLUMN_NAME_DESCRICAO, "Curso de introdução ao desenvolvimento android.");
        valores1.put(EventoContract.Evento.COLUMN_NAME_DATA, "20/10/2018");
        valores1.put(EventoContract.Evento.COLUMN_NAME_HORA, "20:00");
        sqLiteDatabase.insert(EventoContract.Evento.TABLE_NAME, null, valores1);

        //Evento 2
        ContentValues valores2 = new ContentValues();
        valores2.put(EventoContract.Evento.COLUMN_NAME_TITULO, "Palestra Igor");
        valores2.put(EventoContract.Evento.COLUMN_NAME_FACILITADOR, "Igor Knop");
        valores2.put(EventoContract.Evento.COLUMN_NAME_DESCRICAO, "Palestra sobre clean code.");
        valores2.put(EventoContract.Evento.COLUMN_NAME_DATA, "11/11/2018");
        valores2.put(EventoContract.Evento.COLUMN_NAME_HORA, "20:00");
        sqLiteDatabase.insert(EventoContract.Evento.TABLE_NAME, null, valores2);

        //Participante 1
        ContentValues valores3 = new ContentValues();
        valores3.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, "Ramon Larivoir");
        valores3.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, "rlarivoir@gmail.com");
        valores3.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, "111111111");
        sqLiteDatabase.insert(ParticipanteContract.Participante.TABLE_NAME, null, valores3);

        //Participante 2
        ContentValues valores4 = new ContentValues();
        valores4.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, "Douglas Baumgratz");
        valores4.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, "douglas@gmail.com");
        valores4.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, "222222222");
        sqLiteDatabase.insert(ParticipanteContract.Participante.TABLE_NAME, null, valores4);

        //Participante Evento 1
        ContentValues valores5 = new ContentValues();
        valores5.put(ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_PARTICIPANTE, 1);
        valores5.put(ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_EVENTO, 1);
        sqLiteDatabase.insert(ParticipanteEventoContract.ParticipanteEvento.TABLE_NAME, null, valores5);

        //Participante Evento 2
        ContentValues valores6 = new ContentValues();
        valores6.put(ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_PARTICIPANTE, 1);
        valores6.put(ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_EVENTO, 2);
        sqLiteDatabase.insert(ParticipanteEventoContract.ParticipanteEvento.TABLE_NAME, null, valores6);

        //Participante Evento 1
        ContentValues valores7 = new ContentValues();
        valores7.put(ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_PARTICIPANTE, 2);
        valores7.put(ParticipanteEventoContract.ParticipanteEvento.COLUMN_NAME_ID_EVENTO, 1);
        sqLiteDatabase.insert(ParticipanteEventoContract.ParticipanteEvento.TABLE_NAME, null, valores7);
    }

}
