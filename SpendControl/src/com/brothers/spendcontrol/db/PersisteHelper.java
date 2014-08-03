package com.brothers.spendcontrol.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersisteHelper extends SQLiteOpenHelper {

	public static final String NOME_BANCO =  "SpendControlDB";
    public static final int VERSAO =  1;
	private static PersisteHelper instance;

	private PersisteHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO);
	}

	public static PersisteHelper getInstance(Context context) {
		if(instance == null)
			instance = new PersisteHelper(context);

		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		db.execSQL(DatabaseOperationsDAO.CREATE_TABLE_INFRAME);
		db.execSQL(DatabaseOperationsDAO.CREATE_TABLE_SPEND);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
