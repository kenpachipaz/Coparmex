package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroUsuarios extends Activity implements OnClickListener {

	EditText tvNombre,tvNomEmpresa,tvTelefono,tvCorreo,tvContrasena;
	ayudanteBD aBD;
	SQLiteDatabase db = null;
	Button btn;
	
    private DrawerLayout navDrawer;
    private ListView navLista;
    private String [] titulos;
    private ArrayList<ItemObj> navItems;
    private TypedArray navIconos;
    private ActionBarDrawerToggle menuDrawerToogle;
    NavigationAdapter navigationAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro_usuarios);
		
		btn=(Button)findViewById(R.id.button);
		btn.setOnClickListener(this);
		
		tvNombre=(EditText)findViewById(R.id.editText1);
		tvNomEmpresa=(EditText)findViewById(R.id.editText2);
		tvTelefono=(EditText)findViewById(R.id.editText3);
		tvCorreo=(EditText)findViewById(R.id.editText4);
		tvContrasena=(EditText)findViewById(R.id.editText5);
		
		
		//--------------------
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
                    Intent noticias = new Intent(RegistroUsuarios.this,ListaUsuarios.class);
                      startActivity(noticias);

                  }else if(position == 2){ //Formación
                      Intent formacion = new Intent(RegistroUsuarios.this,ListaUsuarios.class);
                      startActivity(formacion);

                  }else if(position == 3){//Red de Negocio
                      Intent redNegocio = new Intent(RegistroUsuarios.this,MainActivity.class);
                      startActivity(redNegocio);

                  }else if(position == 4){//Evento
                       Intent evento = new Intent(RegistroUsuarios.this,ListaUsuarios.class);
                      startActivity(evento);

                  }else if(position == 5){//Cerrar Sesion
                       Intent cerrarSesion = new Intent(RegistroUsuarios.this,ListaUsuarios.class);
                      startActivity(cerrarSesion);
                  }

            }
        });
	}

	@Override
	public void onClick(View v) {
		boolean bandera = true;
		if(v.getId()==R.id.button){
			
			String correo=tvCorreo.getText().toString().trim();
			String nombre=tvNombre.getText().toString().trim();
			String empresa=tvNomEmpresa.getText().toString().trim();
			String telefono=tvTelefono.getText().toString().trim();
			String pass=tvContrasena.getText().toString().trim();
			
			if(correo.compareTo("")==0 ||
					nombre.compareTo("")==0 ||
					empresa.compareTo("")==0 ||
					telefono.compareTo("")==0 ||
					pass.compareTo("")==0){
				
				Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_LONG).show();
				
			}else{
			try {
				aBD = new ayudanteBD(this, "usuariosBD", null, 1);
				db = aBD.getWritableDatabase();
				if (db != null) {
					db.execSQL("INSERT INTO usuarios VALUES ('" + telefono + "','"+ nombre + "','" + empresa + "','" + correo + "','"+pass+"')");
				}
			} catch (SQLiteConstraintException e) {
				Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_LONG).show();
				bandera=false;
			} finally {
				if (db != null)
					db.close();

			}

			if (bandera) {
				Toast.makeText(this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
				Intent menu = new Intent(this, Principal.class);
				startActivity(menu);
			} else {
				tvNombre.setText("");
				tvNomEmpresa.setText("");
				tvTelefono.setText("");
				tvContrasena.setText("");
				tvCorreo.setText("");
			}
			}
		}
		
	}
}
