package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


public class Principal extends ActionBarActivity implements OnClickListener {

	ImageButton btnRed, btnDirectorio, btnChat, btnRegistrar;
	Intent intent;
	
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
        setContentView(R.layout.activity_principal);
        
        btnRed=(ImageButton)findViewById(R.id.imageButton);
        btnDirectorio=(ImageButton)findViewById(R.id.imageButton2);
        btnChat=(ImageButton)findViewById(R.id.imageButton3);
        btnRegistrar=(ImageButton)findViewById(R.id.imageButton4);
        
        btnRed.setOnClickListener(this);
        btnDirectorio.setOnClickListener(this);
        btnChat.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        
        //------------------
      //Menu Toogle
//        menuDrawerToogle =  new ActionBarDrawerToggle(
//         this,navDrawer,R.drawable.ic_drawer,1,1
//        );
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
                  Intent noticias = new Intent(Principal.this,ListaUsuarios.class);
                    startActivity(noticias);

                }else if(position == 2){ //Formación
                    Intent formacion = new Intent(Principal.this,ListaUsuarios.class);
                    startActivity(formacion);

                }else if(position == 3){//Red de Negocio
                    Intent redNegocio = new Intent(Principal.this,MainActivity.class);
                    startActivity(redNegocio);

                }else if(position == 4){//Evento
                     Intent evento = new Intent(Principal.this,ListaUsuarios.class);
                    startActivity(evento);

                }else if(position == 5){//Cerrar Sesion
                     Intent cerrarSesion = new Intent(Principal.this,ListaUsuarios.class);
                    startActivity(cerrarSesion);
                }

            }
        });
        //--------------
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton:
			intent=new Intent(this,MainActivity.class);
			startActivity(intent);
			break;

		case R.id.imageButton2:
			break;
			
		case R.id.imageButton3:
			intent=new Intent(this,ListaUsuarios.class);
			startActivity(intent);
			break;
			
		case R.id.imageButton4:
			intent=new Intent(this,RegistroUsuarios.class);
			startActivity(intent);
			break;
		}
		
	}
}
