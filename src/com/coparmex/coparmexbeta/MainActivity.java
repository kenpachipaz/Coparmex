package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
	public static String nombreEmpresa="", URLImagen="";
	private String[] urlImagenes={"drawable/crea","drawable/de_acero","drawable/mision","drawable/sirloin","drawable/prueba"};
	private ListView listaDirectorio;
	private ItemDirectorio ItemD;
	private AyudanteDB aDB;
	private SQLiteDatabase db;
	private String DB="empresas";
	private Resources r;
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
        setContentView(R.layout.activity_main);
        
        ItemD= new ItemDirectorio();
        listaDirectorio= (ListView) findViewById(R.id.listView);
        
        final ArrayList<ItemDirectorio> itemDir= obtenerItems();
        
        r= getResources();
        
        ItemDirectorioAdapter adaptador= new ItemDirectorioAdapter(this, itemDir);
        
        listaDirectorio.setAdapter(adaptador);
        
        listaDirectorio.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long l) {
				ItemD= itemDir.get(position);
				URLImagen=ItemD.getRutaImagen();
				nombreEmpresa= ItemD.getNombre();
				iniciarActividad(Datos.class);
			}
        	
        	
		});
        
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
                Intent noticias = new Intent(MainActivity.this,ListaUsuarios.class);
                  startActivity(noticias);

              }else if(position == 2){ //Formación
                  Intent formacion = new Intent(MainActivity.this,ListaUsuarios.class);
                  startActivity(formacion);

              }else if(position == 3){//Red de Negocio
                  Intent redNegocio = new Intent(MainActivity.this,MainActivity.class);
                  startActivity(redNegocio);

              }else if(position == 4){//Evento
                   Intent evento = new Intent(MainActivity.this,ListaUsuarios.class);
                  startActivity(evento);

              }else if(position == 5){//Cerrar Sesion
                   Intent cerrarSesion = new Intent(MainActivity.this,ListaUsuarios.class);
                  startActivity(cerrarSesion);
              }

          }
      });
      //--------------
        
    }

    public void iniciarActividad(Class actividad){
    	Intent i= new Intent(this, actividad);
    	startActivity(i);
    	finish();
    }
    private ArrayList<ItemDirectorio> obtenerItems(){
    	ArrayList<ItemDirectorio> items= new ArrayList<ItemDirectorio>();
    	
    	int contador=0;
		try{
			aDB= new AyudanteDB(this, DB, null, 1);
			db= aDB.getReadableDatabase();
			if(db != null){
				Cursor cursor=db.rawQuery("SELECT nombre FROM empresa", null);
				while(cursor.moveToNext()){
					if(contador < 3)
						items.add(new ItemDirectorio(1, urlImagenes[contador], cursor.getString(0)));
					else
						items.add(new ItemDirectorio(1, urlImagenes[4], cursor.getString(0)));
					
					contador++;
				}
				cursor.close();
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}finally{
			if(db!=null)						
				db.close();
		}
    	
    	/*items.add(new ItemDirectorio(1, , "Corporativo Reza Agreda, S.C"));
    	items.add(new ItemDirectorio(2, "drawable/de_acero", "De acero, S.A. de C.V."));
    	items.add(new ItemDirectorio(3, "drawable/mision", "Hoteles mision"));
    	items.add(new ItemDirectorio(4, "drawable/sirloin", "Sirlion stckade"));*/
		
    	
    	return items;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	iniciarActividad(RegistroEmpresa.class);
        }
        return super.onOptionsItemSelected(item);
    }
}
