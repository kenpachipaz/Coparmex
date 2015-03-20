package com.coparmex.coparmexbeta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



public class ayudanteBD extends SQLiteOpenHelper{
	String sentenciaCreacionSQL="CREATE TABLE usuarios (idTelefono TEXT primary key,nombre TEXT,nomEmpresa TEXT,correo TEXT,pass TEXT)";

	public ayudanteBD(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//referencia al query
		db.execSQL(sentenciaCreacionSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	 

}
