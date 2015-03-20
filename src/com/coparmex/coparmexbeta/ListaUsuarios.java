package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaUsuarios extends Activity implements OnItemClickListener{

	ListView lv;
	ayudanteBD aBD;
	SQLiteDatabase db = null;
	ArrayList<ItemUsuarios> items = new ArrayList<ItemUsuarios>();
	public static String TELEFONO="";
	
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
		setContentView(R.layout.main_lista_empresas);
		
		lv = (ListView)findViewById(R.id.listView);
        lv.setOnItemClickListener(this); 
        creaLista();
        
      //------------------
        //Menu Toogle
//          menuDrawerToogle =  new ActionBarDrawerToggle(
//           this,navDrawer,R.drawable.ic_drawer,1,1
//          );
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
                    Intent noticias = new Intent(ListaUsuarios.this,ListaUsuarios.class);
                      startActivity(noticias);

                  }else if(position == 2){ //Formaci�n
                      Intent formacion = new Intent(ListaUsuarios.this,ListaUsuarios.class);
                      startActivity(formacion);

                  }else if(position == 3){//Red de Negocio
                      Intent redNegocio = new Intent(ListaUsuarios.this,MainActivity.class);
                      startActivity(redNegocio);

                  }else if(position == 4){//Evento
                       Intent evento = new Intent(ListaUsuarios.this,ListaUsuarios.class);
                      startActivity(evento);

                  }else if(position == 5){//Cerrar Sesion
                       Intent cerrarSesion = new Intent(ListaUsuarios.this,ListaUsuarios.class);
                      startActivity(cerrarSesion);
                  }

              }
          });
          //--------------
	    
	}
	
	private void creaLista(){
		int pos=0;
		try {
			aBD = new ayudanteBD(this, "usuariosBD", null, 1);
			db = aBD.getReadableDatabase();
			if (db != null) {
				//Vector<String> resultado2 = new Vector<String>();
				
				ArrayList<ItemUsuarios> itemsCompra=new ArrayList<ItemUsuarios>();
				Cursor cursor = db.rawQuery("SELECT * from usuarios", null);
				while (cursor.moveToNext()) {
					String nombre=cursor.getString(1);
					String texto="Empresa: "+cursor.getString(2)+"\nCorreo: "+cursor.getString(3)+"\nTelefono: "+cursor.getString(0);
					//(idTelefono TEXT primary key,nombre TEXT,nomEmpresa TEXT,correo INTEGER,pass TEXT)";
				    itemsCompra = obtenerItems(pos,nombre,texto);
				    pos++;
				}
				cursor.close();
				ItemUserAdapter adapter = new ItemUserAdapter(this, itemsCompra);
		         
			    lv.setAdapter(adapter);
			}

		} catch (Exception e) {
			Toast.makeText(this, "Consulte al administrado", Toast.LENGTH_SHORT).show();

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private ArrayList<ItemUsuarios> obtenerItems(int pos, String nombre, String texto) {
	             
	    items.add(new ItemUsuarios(pos, nombre, texto, "drawable/user3"));
	         
	    return items;
	  }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ViewGroup grupo = (ViewGroup) arg1;
		TextView tv = (TextView) grupo.getChildAt(2);
		Toast.makeText(this, arg2+"", Toast.LENGTH_SHORT).show();
		
		System.out.println("Entre");
		String id=tv.getText().toString().replaceAll(" ", ";");
		System.out.println("pase ID");
		String arreglo[]=id.split(";");
		System.out.println("Pase Arreglo");
		String tel=arreglo[3];
		System.out.println(tel);
		Intent int1=new Intent(this,InformacionGeneral.class);
		Bundle bolsa=new Bundle();
		bolsa.putString("tel",tel);
		int1.putExtras(bolsa);
		startActivity(int1);
	}
}
