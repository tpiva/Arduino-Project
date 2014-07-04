package com.brothers.spendcontrol.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brothers.spendcontrol.entities.Inframe;
import com.brothers.spendcontrol.entities.Spends;
import com.brothers.spendcontrol.utils.OperationDayTime;

public class DatabaseOperationsDAO {
	
	private static final String INFRAME_TABLE = "Inframe";
	private static final String COLUMN_INFRAME_VALUE = "inframe_value";
	private static final String COLUMN_ID_INFRAME = "id_inframe";
	
	private static final String COLUMN_PAYDAY = "payday";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_NAME_OF_SPEND = "name_of_spend";
	private static final String COLUMN_CONSTANTLY_SPEND = "constantly_spend";
	private static final String COLUMN_ID_SPENDS = "id_spends";
	private static final String COLUMN_SPEND_VALUE = "value";
	private static final String COLUMN_SPEND_DATE_REGISTER = "date_register";
	private static final String COLUMN_SPEND_DUO_DATE = "duo_date";
	
	
	private static final String SPEND_TABLE = "Spend";

	public static final String CREATE_TABLE_INFRAME = "CREATE TABLE "
			+ INFRAME_TABLE + "(" + COLUMN_ID_INFRAME
			+ " INTEGER PRIMARY KEY," + COLUMN_INFRAME_VALUE + " REAL" + ")";
	
	public static final String CREATE_TABLE_SPEND = "CREATE TABLE "
			+ SPEND_TABLE + "(" + COLUMN_ID_SPENDS
			+ " INTEGER PRIMARY KEY NOT NULL ," + COLUMN_NAME_OF_SPEND + " TEXT," + COLUMN_DESCRIPTION + " TEXT," 
			+ COLUMN_SPEND_VALUE + " REAL," + COLUMN_CONSTANTLY_SPEND + " TEXT," + COLUMN_PAYDAY + " TEXT," 
			+ COLUMN_ID_INFRAME + " INTEGER," + COLUMN_SPEND_DATE_REGISTER + " TEXT," + COLUMN_SPEND_DUO_DATE + " TEXT," + "FOREIGN KEY(" + COLUMN_ID_INFRAME + ") REFERENCES " 
			+ INFRAME_TABLE + "(" + COLUMN_ID_INFRAME + ")" + ")";


	private SQLiteDatabase dataBase = null;
	private static DatabaseOperationsDAO instance;

	public static DatabaseOperationsDAO getInstance(Context context) {
		if (instance == null)
			instance = new DatabaseOperationsDAO(context);
		return instance;
	}

	private DatabaseOperationsDAO(Context context) {
		PersisteHelper persistenceHelper = PersisteHelper
				.getInstance(context);
		dataBase = persistenceHelper.getWritableDatabase();
	}

	public void saveInframe(Inframe inframe) {
		ContentValues values = getContentValueInframe(inframe);
		dataBase.insert(INFRAME_TABLE, null, values);
	}
	
	public void updateInframe(Inframe inframe) {
		ContentValues values = getContentValueInframe(inframe);
		dataBase.update(INFRAME_TABLE, values, COLUMN_ID_INFRAME + "=" + inframe.getId(), null);
	}
	
	public void updateSpend(Spends spends) {
		ContentValues values = getContentValueSpends(spends);
		dataBase.update(SPEND_TABLE, values, COLUMN_ID_SPENDS + "=" + spends.getIdSpends(), null);
	}
	
	public void saveSpends(Spends spends) {
		ContentValues values = getContentValueSpendsInsert(spends);
		dataBase.insert(SPEND_TABLE, null, values);
	}
	
	private ContentValues getContentValueInframe(Inframe inframe) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID_INFRAME, inframe.getId());
		values.put(COLUMN_INFRAME_VALUE, inframe.getInframeValue());
		return values;
	}
	
	private ContentValues getContentValueSpends(Spends spends) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID_SPENDS, spends.getIdSpends());
		values.put(COLUMN_NAME_OF_SPEND, spends.getNameOfSpend());
		values.put(COLUMN_DESCRIPTION, spends.getDescription());
		values.put(COLUMN_SPEND_VALUE, spends.getValue());
		values.put(COLUMN_CONSTANTLY_SPEND, spends.getConstantlySpend());
		values.put(COLUMN_PAYDAY, spends.getPayDay());
		values.put(COLUMN_ID_INFRAME, spends.getIdInframe());
		values.put(COLUMN_SPEND_DATE_REGISTER, spends.getDateRegister());
		values.put(COLUMN_SPEND_DUO_DATE, spends.getDuoDate());
		
		return values;
	}
	
	private ContentValues getContentValueSpendsInsert(Spends spends) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_OF_SPEND, spends.getNameOfSpend());
		values.put(COLUMN_DESCRIPTION, spends.getDescription());
		values.put(COLUMN_SPEND_VALUE, spends.getValue());
		values.put(COLUMN_CONSTANTLY_SPEND, spends.getConstantlySpend());
		values.put(COLUMN_PAYDAY, spends.getPayDay());
		values.put(COLUMN_ID_INFRAME, spends.getIdInframe());
		values.put(COLUMN_SPEND_DATE_REGISTER, spends.getDateRegister());
		values.put(COLUMN_SPEND_DUO_DATE, spends.getDuoDate());
		return values;
	}
	
	public List<Inframe> returnAllInframes() {
		String queryReturnAll = new StringBuilder().append("SELECT * FROM ").append(INFRAME_TABLE).toString();
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<Inframe> inframes = buildCursorInframe(cursor);

		return inframes;
	}
	
	public List<Spends> returnAllSpendsByDateMouthly() {
		//getDate according mounth
		String initialPeriodOfSearch = OperationDayTime.dayInitialPeriodOfMounth();
		String finalPeriodOfSearch = OperationDayTime.dayFinalPeriodOfMounth();
		
		String queryFromPeriod = new StringBuilder().append("SELECT * FROM ").append(SPEND_TABLE)
				.append(" WHERE ").append(COLUMN_SPEND_DATE_REGISTER).append(" BETWEEN ")
				.append(initialPeriodOfSearch).append(" AND ").append(finalPeriodOfSearch).toString();
		Cursor cursor = dataBase.rawQuery(queryFromPeriod, null);
		List<Spends> spends = buildCursorSpend(cursor);

		return spends;
	}
	
	public List<Spends> returnAllSpends() {
		String queryReturnAll = new StringBuilder().append("SELECT * FROM ").append(SPEND_TABLE).toString();
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<Spends> spends = buildCursorSpend(cursor);

		return spends;
	}
	
	private List<Inframe> buildCursorInframe(Cursor cursor) {
		List<Inframe> inframes = new ArrayList<Inframe>();
		if(cursor == null)
			return inframes;

		try {

			if (cursor.moveToFirst()) {
				do {

					int indexIdInframe = cursor.getColumnIndex(COLUMN_ID_INFRAME);
					int indexValueInframe = cursor.getColumnIndex(COLUMN_INFRAME_VALUE);

					int idInframe = cursor.getInt(indexIdInframe);
					Double valueInframe = cursor.getDouble(indexValueInframe);

					Inframe inframe = new Inframe(idInframe, valueInframe);

					inframes.add(inframe);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return inframes;
	}
	
	private List<Spends> buildCursorSpend(Cursor cursor) {
		List<Spends> spendList = new ArrayList<Spends>();
		if(cursor == null)
			return spendList;

		try {

			if (cursor.moveToFirst()) {
				do {

					int indexIdSpend = cursor.getColumnIndex(COLUMN_ID_SPENDS);
					int indexNameSpend = cursor.getColumnIndex(COLUMN_NAME_OF_SPEND);
					int indexDescriptionSpend = cursor.getColumnIndex(COLUMN_DESCRIPTION);
					int indexValueSpend = cursor.getColumnIndex(COLUMN_SPEND_VALUE);
					int indexConstantlySpend = cursor.getColumnIndex(COLUMN_CONSTANTLY_SPEND);
					int indexPayDay = cursor.getColumnIndex(COLUMN_PAYDAY);
					int indexDateRegister = cursor.getColumnIndex(COLUMN_SPEND_DATE_REGISTER);
					int indexDuoDate = cursor.getColumnIndex(COLUMN_SPEND_DUO_DATE);
					int indexIdInframe = cursor.getColumnIndex(COLUMN_ID_INFRAME);

					int idSpend = cursor.getInt(indexIdInframe);
					String nameSpend = cursor.getString(indexNameSpend);
					String descriptionSpend = cursor.getString(indexDescriptionSpend);
					Double valueSpend = cursor.getDouble(indexValueSpend);
					String constantlySpend = cursor.getString(indexConstantlySpend);
					String payday = cursor.getString(indexPayDay);
					String registerDay = cursor.getString(indexDateRegister);
					String duoDate = cursor.getString(indexDuoDate);
					int idInframeOnSpend = cursor.getInt(indexIdSpend);

					Spends spend = new Spends(payday, descriptionSpend, nameSpend, null, 
							constantlySpend, idInframeOnSpend, idSpend, valueSpend, registerDay, duoDate);

					spendList.add(spend);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return spendList;
	}
}
