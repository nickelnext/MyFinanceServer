package Quotes;

import java.util.Date;

public class Quotation_Bond extends Quotation{

	
	private String valuta;
	private String mercato;
	private String faseMercato;
	private float prezzoUltimoContratto;
	private float variazionePercentuale;
	private float variazioneAssoluta;
	private Date dataUltimoContratto;
	private int volumeUltimo;
	private int volumeAcquisto;
	private int volumeVendita;
	private float prezzoAcquisto;
	private float prezzoVendita;
	private int volumeTotale;
	private float maxOggi;
	private float maxAnno;
	private float minOggi;
	private float minAnno;
	private Date dataMinAnno;
	private Date dataMaxAnno;
	private float aperturaChiusuraPrecedente;
	private Date scadenza;
	private Date dataStaccoCedola;
	private float cedola;
	private int lottoMinimo;
	

	public Quotation_Bond(String isin) {
		super(isin);
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
	public void setPrezzoUltimoContratto(float prezzoUltimoContratto) {
		this.prezzoUltimoContratto = prezzoUltimoContratto;
	}
	public float getVariazionePercentuale() {
		return variazionePercentuale;
	}
	public void setVariazionePercentuale(float variazionePercentuale) {
		this.variazionePercentuale = variazionePercentuale;
	}
	public float getVariazioneAssoluta() {
		return variazioneAssoluta;
	}
	public void setVariazioneAssoluta(float variazioneAssoluta) {
		this.variazioneAssoluta = variazioneAssoluta;
	}
	public Date getDataUltimoContratto() {
		return dataUltimoContratto;
	}
	public void setDataUltimoContratto(Date dataUltimoContratto) {
		this.dataUltimoContratto = dataUltimoContratto;
	}
	public int getVolumeUltimo() {
		return volumeUltimo;
	}
	public void setVolumeUltimo(int volumeUltimo) {
		this.volumeUltimo = volumeUltimo;
	}
	public int getVolumeAcquisto() {
		return volumeAcquisto;
	}
	public void setVolumeAcquisto(int volumeAcquisto) {
		this.volumeAcquisto = volumeAcquisto;
	}
	public int getVolumeVendita() {
		return volumeVendita;
	}
	public void setVolumeVendita(int volumeVendita) {
		this.volumeVendita = volumeVendita;
	}
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(float prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public float getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(float prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	public int getVolumeTotale() {
		return volumeTotale;
	}
	public void setVolumeTotale(int volumeTotale) {
		this.volumeTotale = volumeTotale;
	}
	public float getMaxOggi() {
		return maxOggi;
	}
	public void setMaxOggi(float maxOggi) {
		this.maxOggi = maxOggi;
	}
	public float getMaxAnno() {
		return maxAnno;
	}
	public void setMaxAnno(float maxAnno) {
		this.maxAnno = maxAnno;
	}
	public float getMinOggi() {
		return minOggi;
	}
	public void setMinOggi(float minOggi) {
		this.minOggi = minOggi;
	}
	public float getMinAnno() {
		return minAnno;
	}
	public void setMinAnno(float minAnno) {
		this.minAnno = minAnno;
	}
	public Date getDataMinAnno() {
		return dataMinAnno;
	}
	public void setDataMinAnno(Date dataMinAnno) {
		this.dataMinAnno = dataMinAnno;
	}
	public Date getDataMaxAnno() {
		return dataMaxAnno;
	}
	public void setDataMaxAnno(Date dataMaxAnno) {
		this.dataMaxAnno = dataMaxAnno;
	}
	public float getAperturaChiusuraPrecedente() {
		return aperturaChiusuraPrecedente;
	}
	public void setAperturaChiusuraPrecedente(float aperturaChiusuraPrecedente) {
		this.aperturaChiusuraPrecedente = aperturaChiusuraPrecedente;
	}
	public Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	public Date getDataStaccoCedola() {
		return dataStaccoCedola;
	}
	public void setDataStaccoCedola(Date dataStaccoCedola) {
		this.dataStaccoCedola = dataStaccoCedola;
	}
	public float getCedola() {
		return cedola;
	}
	public void setCedola(float cedola) {
		this.cedola = cedola;
	}
	public int getLottoMinimo() {
		return lottoMinimo;
	}
	public void setLottoMinimo(int lottoMinimo) {
		this.lottoMinimo = lottoMinimo;
	}
	
}
