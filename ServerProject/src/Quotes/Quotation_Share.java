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
	public void setLottoMinimo(String string) {
		this.lottoMinimo = repInteger(string);
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
	public Date getDataOraUltimoAcquisto() {
		return dataOraUltimoAcquisto;
	}
	public void setDataOraUltimoAcquisto(String string) {
		this.dataOraUltimoAcquisto = formatDate(string);
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
	public int getQuantitaUltimo() {
		return quantitaUltimo;
	}
	public void setQuantitaUltimo(String string) {
		this.quantitaUltimo = repInteger(string);
	}
	public int getQuantitaAcquisto() {
		return quantitaAcquisto;
	}
	public void setQuantitaAcquisto(String string) {
		this.quantitaAcquisto = repInteger(string);
	}
	public int getQuantitaVendita() {
		return quantitaVendita;
	}
	public void setQuantitaVendita(String string) {
		this.quantitaVendita = repInteger(string);
	}
	public int getQuantitaTotale() {
		return quantitaTotale;
	}
	public void setQuantitaTotale(String string) {
		this.quantitaTotale = repInteger(string);
	}
	public float getMaxOggi() {
		return maxOggi;
	}
	public void setMaxOggi(String string) {
		this.maxOggi = repFloat(string);
	}
	public float getMinOggi() {
		return minOggi;
	}
	public void setMinOggi(String string) {
		this.minOggi = repFloat(string);
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
	public void setChiusuraPrecedente(String string) {
		this.chiusuraPrecedente = repFloat(string);
	}

}
