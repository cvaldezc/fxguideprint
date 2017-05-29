/**
 *
 */
package com.icrperusa.guideprint.entity;

import com.icrperusa.guideprint.controller.SettingsMasterController;

/**
 * @author Christian
 *
 */
public class GuideRemission extends SettingsMasterController {

    // define variables
    private String guideid;
    private String ruccliente;
    private String puntollegada;
    private String dotoutput;
    private String traslado;
    private String traruc;
    private String condni;
    private String nropla;
    private String status;
    private String commen;
    private String observation;

    // constructor principal
    public GuideRemission(){}

    /*
     * GETTERS AND SETTERS
     * */

    public String getGuideid() {
        return guideid;
    }
    public void setGuideid(String guideid) {
        this.guideid = guideid;
    }
    public String getRuccliente() {
        return ruccliente;
    }
    public void setRuccliente(String ruccliente) {
        this.ruccliente = ruccliente;
    }
    public String getPuntollegada() {
        return puntollegada;
    }
    public void setPuntollegada(String puntollegada) {
        this.puntollegada = puntollegada;
    }
    public String getDotoutput() {
        return dotoutput;
    }
    public void setDotoutput(String dotoutput) {
        this.dotoutput = dotoutput;
    }
    public String getTraslado() {
        return traslado;
    }
    public void setTraslado(String traslado) {
        this.traslado = traslado;
    }
    public String getTraruc() {
        return traruc;
    }
    public void setTraruc(String traruc) {
        this.traruc = traruc;
    }
    public String getCondni() {
        return condni;
    }
    public void setCondni(String condni) {
        this.condni = condni;
    }
    public String getNropla() {
        return nropla;
    }
    public void setNropla(String nropla) {
        this.nropla = nropla;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCommen() {
        return commen;
    }
    public void setCommen(String commen) {
        this.commen = commen;
    }
    public String getObservation() {
        return observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    private String nota;

}
