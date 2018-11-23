package br.ufjf.dcc196.trabalho1_ramon_douglas.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.EventoContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.ParticipanteContract;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Biblioteca.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EventoContract.CREATE_EVENTO);
        sqLiteDatabase.execSQL(ParticipanteContract.CREATE_PARTICIPANTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ParticipanteContract.DROP_PARTICIPANTE);
        sqLiteDatabase.execSQL(EventoContract.DROP_EVENTO);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
