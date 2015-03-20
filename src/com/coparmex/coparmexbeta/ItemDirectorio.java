package com.coparmex.coparmexbeta;

public class ItemDirectorio {
	 protected long id;
	  protected String rutaImagen;
	  protected String nombre;
	  
	  public ItemDirectorio(){
		  
	  }
	public ItemDirectorio(long id, String rutaImagen, String nombre){
		this.id=id;
		this.rutaImagen=rutaImagen;
		this.nombre= nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	  
	  
}
