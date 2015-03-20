package com.coparmex.coparmexbeta;
public class ItemObj {
    private String titulo;
    private int icono;
    public ItemObj(String titulo, int icono){
        this.titulo = titulo;
        this.icono = icono;

    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public int getIcono(){
        return icono;
    }
    public void setIcono(int icono){
        this.icono =  icono;
    }
}
