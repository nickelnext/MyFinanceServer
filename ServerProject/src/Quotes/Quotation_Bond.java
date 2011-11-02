package Quotes;

import java.util.Date;

public class Quotation_Bond extends Quotation{

	
	protected String valuta;
	protected String mercato;
	protected String faseMercato;
	protected float prezzoUltimoContratto;
	protected float variazionePercentuale;
	protected float variazioneAssoluta;
	protected Date dataUltimoContratto;
	protected int volumeUltimo;
	protected int volumeAcquisto;
	protected int volumeVendita;
	protected float prezzoAcquisto;
	protected float prezzoVendita;
	protected int volumeTotale;
	protected float maxOggi;
	protected float maxAnno;
	protected float minOggi;
	protected float minAnno;
	protected Date dataMinAnno;
	protected Date dataMaxAnno;
	protected float aperturaChiusuraPrecedente;
	protected Date scadenza;
	protected Date dataStaccoCedola;
	protected float cedola;
	protected int lottoMinimo;
	

	public Quotation_Bond() {
		super();
		super.setType("Bond");
	}
	
	public String getValuta() {
		return valuta;
	}
	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	public String getMercato() {
		return mercato;
	}
	public void setMercato(String mercato) {
		this.mercato = mercato;
	}
	public String getFaseMercato() {
		return faseMercato;
	}
	public void setFaseMercato(String faseMercato) {
		this.faseMercato = faseMercato;
	}
	public float getPrezzoUltimoContratto() {
		return prezzoUltimoContratto;
	}
	public void setPrezzoUltimoContratto(String string) {
		this.prezzoUltimoContratto = repFloat(string);
	}
	

	public float getVariazionePercentuale() {
		return variazionePercentuale;
	}
	public void setVariazionePercentuale(String string) {
		this.variazionePercentuale = repFloat(string);
	}
	public float getVariazioneAssoluta() {
		return variazioneAssoluta;
	}
	public void setVariazioneAssoluta(String string) {
		this.variazioneAssoluta = repFloat(string);
	}
	public Date getDataUltimoContratto() {
		return dataUltimoContratto;
	}
	public void setDataUltimoContratto(String string) {
		this.dataUltimoContratto = formatDate(string);
	}
	public int getVolumeUltimo() {
		return volumeUltimo;
	}
	public void setVolumeUltimo(String string) {
		this.volumeUltimo = repInteger(string);
	}
	public int getVolumeAcquisto() {
		return volumeAcquisto;
	}
	public void setVolumeAcquisto(String string) {
		this.volumeAcquisto = repInteger(string);
	}
	public int getVolumeVendita() {
		return volumeVendita;
	}
	public void setVolumeVendita(String string) {
		this.volumeVendita = repInteger(string);
	}
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(String string) {
		this.prezzoAcquisto = repFloat(string);
	}
	public float getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(String string) {
		this.prezzoVendita = repFloat(string);
	}
	public int getVolumeTotale() {
		return volumeTotale;
	}
	public void setVolumeTotale(String string) {
		this.volumeTotale = repInteger(string);
	}
	

	public float getMaxOggi() {
		return maxOggi;
	}
	public void setMaxOggi(String string) {
		this.maxOggi = repFloat(string);
	}
	public float getMaxAnno() {
		return maxAnno;
	}
	public void setMaxAnno(String string) {
		this.maxAnno = repFloat(string);
	}
	public float getMinOggi() {
		return minOggi;
	}
	public void setMinOggi(String string) {
		this.minOggi = repFloat(string);
	}
	public float getMinAnno() {
		return minAnno;
	}
	public void setMinAnno(String string) {
		this.minAnno = repFloat(string);
	}
	public Date getDataMinAnno() {
		return dataMinAnno;
	}
	public void setDataMinAnno(String string) {
		this.dataMinAnno = formatDate(string);
	}
	public Date getDataMaxAnno() {
		return dataMaxAnno;
	}
	public void setDataMaxAnno(String string) {
		this.dataMaxAnno = formatDate(string);
	}
	public float getAperturaChiusuraPrecedente() {
		return aperturaChiusuraPrecedente;
	}
	public void setAperturaChiusuraPrecedente(String string) {
		this.aperturaChiusuraPrecedente = repFloat(string);
	}
	public Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(String string) {
		this.scadenza = formatDate(string);
	}
	public Date getDataStaccoCedola() {
		return dataStaccoCedola;
	}
	public void setDataStaccoCedola(String string) {
		this.dataStaccoCedola = formatDate(string);
	}

	public float getCedola() {
		return cedola;
	}
	public void setCedola(String string) {
		this.cedola = repFloat(string);
	}
	public int getLottoMinimo() {
		return lottoMinimo;
	}
	public void setLottoMinimo(String string) {
		this.lottoMinimo = repInteger(string);
	}

}
