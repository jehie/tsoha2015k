/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.tavara;

import java.sql.Date;

/**
 *
 * @author Jesse
 */
public class Tavara {

    private long id;
    private String nimi;
    private int hinta;
    private boolean saatavilla;
    private int varatossa;
    private String kuvaus;
    private Date julkaistu;

    public int getHinta() {
        return hinta;
    }

    public void setHinta(int hinta) {
        this.hinta = hinta;
    }

    public boolean isSaatavilla() {
        return saatavilla;
    }

    public void setSaatavilla(boolean saatavilla) {
        this.saatavilla = saatavilla;
    }

    public int getVaratossa() {
        return varatossa;
    }

    public void setVaratossa(int varatossa) {
        this.varatossa = varatossa;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public Date getJulkaistu() {
        return julkaistu;
    }

    public void setJulkaistu(Date julkaistu) {
        this.julkaistu = julkaistu;
    }

    public Date getLisatty() {
        return lisatty;
    }

    public void setLisatty(Date lisatty) {
        this.lisatty = lisatty;
    }
    private Date lisatty;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Tavara(long id, String content) {
        this.id = id;
        this.nimi = content;
    }

}
