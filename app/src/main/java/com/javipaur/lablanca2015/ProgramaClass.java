package com.javipaur.lablanca2015;

/**
 * Created by Javipaur on 07/02/2015.
 */
public class ProgramaClass {
    private int ID;
    private String fecha;
    private String hora;
    private String lugar;
    private String tipo;
    private String acto;
    private String favorito;

    public ProgramaClass() {
        setID(0);
        setFecha("");
        setHora("");
        setLugar("");
        setTipo("");
        setActo("");
        setFavorito("");

    }

    public ProgramaClass(int ID, String fecha, String hora, String lugar, String tipo, String acto, String favorito) {
        this.ID = ID;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.tipo = tipo;
        this.acto = acto;
        this.favorito = favorito;
    }

    public ProgramaClass(String fecha, String hora, String lugar, String tipo, String acto, String favorito) {
        setID(0);
        setFecha(fecha);
        setHora(hora);
        setLugar(lugar);
        setTipo(tipo);
        setActo(acto);
        setFavorito(favorito);
    }



    public ProgramaClass(String fecha) {
        this.fecha = fecha;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getActo() {
        return acto;
    }

    public void setActo(String acto) {
        this.acto = acto;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }
}
