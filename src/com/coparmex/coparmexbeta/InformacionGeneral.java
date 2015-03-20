package com.coparmex.coparmexbeta;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InformacionGeneral extends Activity {
	
	TextView tvNombre,tvCompania,tvCorreo,tvTelefono;
	ayudanteBD aBD;
	SQLiteDatabase db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacion_general);
		
		tvNombre=(TextView)findViewById(R.id.textView1);
		tvCompania=(TextView)findViewById(R.id.textView2);
		tvTelefono=(TextView)findViewById(R.id.textView3);
		tvCorreo=(TextView)findViewById(R.id.textView4);
		
		consulta();
	}
	
	private void consulta() {
		Bundle bolsaRecibida=getIntent().getExtras();
		String telefono=bolsaRecibida.getString("tel");
				
		//Toast.makeText(this, telefono, Toast.LENGTH_SHORT).show();
		
		String[] numTel = { telefono.trim() };

		try {
			aBD = new ayudanteBD(this, "usuariosBD", null, 1);
			db = aBD.getReadableDatabase();
			if (db != null) {
				Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE idTelefono=?",numTel);

				while (cursor.moveToNext()) {
					tvNombre.setText("Nombre: "+cursor.getString(1));
					tvCompania.setText("Compa�ia: "+cursor.getString(2));
					tvTelefono.setText("Telefono: "+cursor.getString(0));
					tvCorreo.setText("Correo: "+cursor.getString(3));
				}
				cursor.close();
			}

		} catch (Exception e) {
			Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

		} finally {
			if (db != null)
				db.close();
		}
	}
}
