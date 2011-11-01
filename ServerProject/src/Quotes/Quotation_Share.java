package Quotes;

import java.util.Date;

public class Quotation_Share extends Quotation{

	
	private int lottoMinimo;
	private String faseMercato;
	private float prezzoUltimoContratto;
	private float variazionePercentuale;
	private float variazioneAssoluta;
	private Date dataOraUltimoAcquisto;
	private float prezzoAcquisto;
	private float prezzoVendita;
	private int quantitaUltimo;
	private int quantitaAcquisto;
	private int quantitaVendita;
	private int quantitaTotale;
	private float maxOggi;
	private float minOggi;
	private float maxAnno;
	private float minAnno;
	private Date dataMaxAnno;
	private Date dataMinAnno;
	private float chiusuraPrecedente;
	
	public Quotation_Share(String isin) {
		super(isin);
		super.setType("Share");
	}
	

	public int getLottoMinimo() {
		return lottoMinimo;
	}
	public void setLottoMinimo(int lottoMinimo) {
		this.lottoMinimo = lottoMinimo;
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
	public Date getDataOraUltimoAcquisto() {
		return dataOraUltimoAcquisto;
	}
	public void setDataOraUltimoAcquisto(Date dataOraUltimoAcquisto) {
		this.dataOraUltimoAcquisto = dataOraUltimoAcquisto;
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
	public int getQuantitaUltimo() {
		return quantitaUltimo;
	}
	public void setQuantitaUltimo(int quantitaUltimo) {
		this.quantitaUltimo = quantitaUltimo;
	}
	public int getQuantitaAcquisto() {
		return quantitaAcquisto;
	}
	public void setQuantitaAcquisto(int quantitaAcquisto) {
		this.quantitaAcquisto = quantitaAcquisto;
	}
	public int getQuantitaVendita() {
		return quantitaVendita;
	}
	public void setQuantitaVendita(int quantitaVendita) {
		this.quantitaVendita = quantitaVendita;
	}
	public int getQuantitaTotale() {
		return quantitaTotale;
	}
	public void setQuantitaTotale(int quantitaTotale) {
		this.quantitaTotale = quantitaTotale;
	}
	public float getMaxOggi() {
		return maxOggi;
	}
	public void setMaxOggi(float maxOggi) {
		this.maxOggi = maxOggi;
	}
	public float getMinOggi() {
		return minOggi;
	}
	public void setMinOggi(float minOggi) {
		this.minOggi = minOggi;
	}
	public float getMaxAnno() {
		return maxAnno;
	}
	public void setMaxAnno(float maxAnno) {
		this.maxAnno = maxAnno;
	}
	public float getMinAnno() {
		return minAnno;
	}
	public void setMinAnno(float minAnno) {
		this.minAnno = minAnno;
	}
	public Date getDataMaxAnno() {
		return dataMaxAnno;
	}
	public void setDataMaxAnno(Date dataMaxAnno) {
		this.dataMaxAnno = dataMaxAnno;
	}
	public Date getDataMinAnno() {
		return dataMinAnno;
	}
	public void setDataMinAnno(Date dataMinAnno) {
		this.dataMinAnno = dataMinAnno;
	}
	public float getChiusuraPrecedente() {
		return chiusuraPrecedente;
	}
	public void setChiusuraPrecedente(float chiusuraPrecedente) {
		this.chiusuraPrecedente = chiusuraPrecedente;
	}

}
