package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RegistroEmpresa extends ActionBarActivity implements OnClickListener{
	private EditText edNombre, edNumero, edEmail, edSitioWeb, edUbicacion, edBreveDes;
	private Button btnRegistrar;
	private AyudanteDB aDB;
	private SQLiteDatabase db;
	private String BD="empresas";
	
	//----------
		private DrawerLayout navDrawer;
	    private ListView navLista;
	    private String [] titulos;
	    private ArrayList<ItemObj> navItems;
	    private TypedArray navIconos;
	    private ActionBarDrawerToggle menuDrawerToogle;
	    NavigationAdapter navigationAdapter;
	    //----------
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_registro_empresa);
		
		edNombre= (EditText) findViewById(R.id.editText1);
		edUbicacion= (EditText) findViewById(R.id.editText2);
		edNumero= (EditText) findViewById(R.id.editText3);		
		edEmail= (EditText) findViewById(R.id.editText4);
		edSitioWeb= (EditText) findViewById(R.id.editText5);
		edBreveDes= (EditText) findViewById(R.id.editText6);
	
		btnRegistrar= (Button) findViewById(R.id.button1);
		
		Typeface fuente = Typeface.createFromAsset(getAssets(), "Fuentes/dispensations.ttf");
		
		btnRegistrar.setTypeface(fuente);
		btnRegistrar.setOnClickListener(this);
		
		//Menu Toogle
//      menuDrawerToogle =  new ActionBarDrawerToggle(
//       this,navDrawer,R.drawable.ic_drawer,1,1
//      );
      //DrawerLayout
      navDrawer =  (DrawerLayout)findViewById(R.id.drawer_layout);
      //Lista

      navDrawer.setDrawerListener(menuDrawerToogle);
      //getActionBar().setDisplayHomeAsUpEnabled(true);

      navLista = (ListView)findViewById(R.id.lista);
      //Declaramos el header el cual serael layout de header.xml
      View header = getLayoutInflater().inflate(R.layout.header,null);
      //Establecemos el header
      navLista.addHeaderView(header);
      //Tomamos listado de imgs desde drawable
      navIconos = getResources().obtainTypedArray(R.array.nav_iconos);
      //Tomamos listado de titulos desde el String-array delos recursos @string/nav_options
      titulos = getResources().getStringArray(R.array.nav_options);
      //Listado de titulos de barra de navegacion
      navItems = new ArrayList<ItemObj>();
      navItems.add( new ItemObj(titulos[0],navIconos.getResourceId(0,-1)));
      navItems.add( new ItemObj(titulos[1],navIconos.getResourceId(1,-1)));
      navItems.add( new ItemObj(titulos[2],navIconos.getResourceId(2,-1)));
      navItems.add( new ItemObj(titulos[3],navIconos.getResourceId(3,-1)));
      navItems.add( new ItemObj(titulos[4],navIconos.getResourceId(4,-1)));
      navigationAdapter = new NavigationAdapter(this,navItems);
      navLista.setAdapter(navigationAdapter);

      navLista.setOnItemClickListener( new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //Noticias
              if(position == 1){
                Intent noticias = new Intent(RegistroEmpresa.this,ListaUsuarios.class);
                  startActivity(noticias);

              }else if(position == 2){ //Formación
                  Intent formacion = new Intent(RegistroEmpresa.this,ListaUsuarios.class);
                  startActivity(formacion);

              }else if(position == 3){//Red de Negocio
                  Intent redNegocio = new Intent(RegistroEmpresa.this,MainActivity.class);
                  startActivity(redNegocio);

              }else if(position == 4){//Evento
                   Intent evento = new Intent(RegistroEmpresa.this,ListaUsuarios.class);
                  startActivity(evento);

              }else if(position == 5){//Cerrar Sesion
                   Intent cerrarSesion = new Intent(RegistroEmpresa.this,ListaUsuarios.class);
                  startActivity(cerrarSesion);
              }

          }
      });
      //--------------
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registro_empresa, menu);
		return true;
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
	public void altaEmpresa(String nombre, String ubicacion, String telefono, String email, String sitio, String breveDes){
		String querySQL="INSERT INTO empresa values('"+nombre+"','"+ubicacion+"','"+telefono+"','"+email+"','"+sitio+"','"+breveDes+"')";
		try{
			aDB= new AyudanteDB(this, BD, null, 1);
			db= aDB.getWritableDatabase();
			if(db != null){
				db.execSQL(querySQL);
				mensaje("Su empresa fue registrada con Ã©xito");
			}
		}catch(SQLException ex){
			mensaje("Problemas al dar de alta la empresa");
			System.out.println(ex.getMessage());
		}finally{
			if(db!=null)						
				db.close();
		}
	}
	public void mensaje(String msg){
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}


	@Override
	public void onClick(View v) {
		if(edNombre.getText().equals("") || edNumero.getText().equals("") || edEmail.getText().equals("") || edUbicacion.getText().equals("") || edSitioWeb.getText().equals("") || edBreveDes.getText().equals("")){
			mensaje("Asegurese de llenar todos los campos");
		}else{
			altaEmpresa(edNombre.getText().toString(), edUbicacion.getText().toString(), edNumero.getText().toString(), edEmail.getText().toString(), edSitioWeb.getText().toString(), edBreveDes.getText().toString());
			edBreveDes.setText("");
			edNombre.setText("");
			edNumero.setText("");
			edUbicacion.setText("");
			edSitioWeb.setText("");
			edEmail.setText("");
		}
		
	}
}
