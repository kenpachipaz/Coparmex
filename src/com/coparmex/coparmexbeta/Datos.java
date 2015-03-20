package com.coparmex.coparmexbeta;



import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Datos extends ActionBarActivity implements OnClickListener{
	private TextView tvEmpresa, tvInfo, tvSitio;
	private ImageButton ivLlamar, ivEmail;
	private ImageView ivEmpresa;
	private AyudanteDB aDB;
	private SQLiteDatabase db;
	private String BD="empresas", telefono="", asunto="Mas informacion",sitio="", url="http://www.google.com";
	private String[] email= new String[1];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_datos);
		
		tvEmpresa= (TextView) findViewById(R.id.textView1);
		tvInfo= (TextView) findViewById(R.id.textView2);
		tvSitio= (TextView) findViewById(R.id.textView3);
		
		ivEmpresa= (ImageView) findViewById(R.id.imageView1);
		ivEmail= (ImageButton) findViewById(R.id.imageButton1);
		ivLlamar= (ImageButton) findViewById(R.id.imageButton2);
		
		Typeface fuente = Typeface.createFromAsset(getAssets(), "Fuentes/dispensations.ttf");
		
		tvEmpresa.setTypeface(fuente);
		tvInfo.setTypeface(fuente);
		tvSitio.setTypeface(fuente);
		
		tvEmpresa.setText(MainActivity.nombreEmpresa);
		
		ivLlamar.setOnClickListener(this);
		ivEmail.setOnClickListener(this);
		tvSitio.setOnClickListener(this);
		
		
		int imageResource = getResources().getIdentifier(MainActivity.URLImagen, null, getPackageName());
	    ivEmpresa.setImageDrawable(getResources().getDrawable(imageResource));
	    
	    tvEmpresa.setText(MainActivity.nombreEmpresa);
	    
	    try{
			aDB= new AyudanteDB(this, BD, null, 1);
			db=aDB.getReadableDatabase();
			if(db != null){
				Cursor cursor=db.rawQuery("SELECT * FROM empresa WHERE nombre='"+MainActivity.nombreEmpresa+"'", null);
				cursor.moveToNext();
				tvInfo.setText(cursor.getString(5)+"\n"+"Ubicados en: "+cursor.getString(1)+"\n");				
				sitio=cursor.getString(4);
				telefono= cursor.getString(2);
				email[0]= cursor.getString(3);				
				SpannableString content = new SpannableString(sitio);
				content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
				tvSitio.append(content);
				cursor.close();
				
			}
		}catch(SQLException ex){
			System.out.println("Error: "+ex.getMessage());
		}finally{
			if(db!=null)
				db.close();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i= new Intent(this, MainActivity.class);
			startActivity(i);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	public void llamar(String telefono){
		try{
			Intent i=new Intent(Intent.ACTION_CALL);
			i.setData(Uri.parse("tel:"+telefono));
			startActivity(i);
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	public void enviaEmail(String[] email, String asunto){
		Intent intentEmail= new Intent(Intent.ACTION_SEND);
		intentEmail.setData(Uri.parse("mailto:"));
		intentEmail.putExtra(Intent.EXTRA_EMAIL, email);
		intentEmail.putExtra(Intent.EXTRA_SUBJECT, asunto);
		intentEmail.setType("message/rfc822");
		startActivity(Intent.createChooser(intentEmail, "Email"));
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.imageButton1:
				llamar(telefono);
				break;
			case R.id.imageButton2:
				enviaEmail(email, asunto);
				Toast.makeText(getApplicationContext(), email[0], Toast.LENGTH_LONG).show();
				break;
			case R.id.textView3:
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse("http://"+sitio));
				startActivity(i);
				break;
		}
		
	}
}
