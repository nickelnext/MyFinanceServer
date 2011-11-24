package Quotes;

import java.util.Date;

import Utils.UtilFuncs;

public class Quotation_Share extends Quotation{

	
	protected int lottoMinimo;
	protected String faseMercato;
	protected float prezzoUltimoContratto;
	protected float variazionePercentuale;
	protected float variazioneAssoluta;
	protected Date dataOraUltimoAcquisto;
	protected float prezzoAcquisto;
	protected float prezzoVendita;
	protected int quantitaUltimo;
	protected int quantitaAcquisto;
	protected int quantitaVendita;
	protected int quantitaTotale;
	protected float maxOggi;
	protected float minOggi;
	protected float maxAnno;
	protected float minAnno;
	protected Date dataMaxAnno;
	protected Date dataMinAnno;
	protected float chiusuraPrecedente;
	
	public Quotation_Share() {
		super();
		super.setType(Type.SHARE);
	}
	

	public int getLottoMinimo() {
		return lottoMinimo;
	}
	public void setLottoMinimo(String string) {
		this.lottoMinimo = UtilFuncs.repInteger(string);
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
		this.prezzoUltimoContratto = UtilFuncs.repFloat(string);
	}
	public float getVariazionePercentuale() {
		return variazionePercentuale;
	}
	public void setVariazionePercentuale(String string) {
		this.variazionePercentuale = UtilFuncs.repFloat(string);
	}
	public float getVariazioneAssoluta() {
		return variazioneAssoluta;
	}
	public void setVariazioneAssoluta(String string) {
		this.variazioneAssoluta = UtilFuncs.repFloat(string);
	}
	public Date getDataOraUltimoAcquisto() {
		return dataOraUltimoAcquisto;
	}
	public void setDataOraUltimoAcquisto(String string) {
		this.dataOraUltimoAcquisto = UtilFuncs.formatDateIt(string);
	}
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(String string) {
		this.prezzoAcquisto = UtilFuncs.repFloat(string);
	}
	public float getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(String string) {
		this.prezzoVendita = UtilFuncs.repFloat(string);
	}
	public int getQuantitaUltimo() {
		return quantitaUltimo;
	}
	public void setQuantitaUltimo(String string) {
		this.quantitaUltimo = UtilFuncs.repInteger(string);
	}
	public int getQuantitaAcquisto() {
		return quantitaAcquisto;
	}
	public void setQuantitaAcquisto(String string) {
		this.quantitaAcquisto = UtilFuncs.repInteger(string);
	}
	public int getQuantitaVendita() {
		return quantitaVendita;
	}
	public void setQuantitaVendita(String string) {
		this.quantitaVendita = UtilFuncs.repInteger(string);
	}
	public int getQuantitaTotale() {
		return quantitaTotale;
	}
	public void setQuantitaTotale(String string) {
		this.quantitaTotale = UtilFuncs.repInteger(string);
	}
	public float getMaxOggi() {
		return maxOggi;
	}
	public void setMaxOggi(String string) {
		this.maxOggi = UtilFuncs.repFloat(string);
	}
	public float getMinOggi() {
		return minOggi;
	}
	public void setMinOggi(String string) {
		this.minOggi = UtilFuncs.repFloat(string);
	}
	public float getMaxAnno() {
		return maxAnno;
	}
	public void setMaxAnno(String maxAnno) {
		this.maxAnno = UtilFuncs.repFloat(maxAnno);
	}
	public float getMinAnno() {
		return minAnno;
	}
	public void setMinAnno(String minAnno) {
		this.minAnno = UtilFuncs.repFloat(minAnno);
	}
	public Date getDataMaxAnno() {
		return dataMaxAnno;
	}
	public void setDataMaxAnno(String dataMaxAnno) {
		this.dataMaxAnno = UtilFuncs.formatDateIt(dataMaxAnno);
	}
	public Date getDataMinAnno() {
		return dataMinAnno;
	}
	public void setDataMinAnno(String dataMinAnno) {
		this.dataMinAnno = UtilFuncs.formatDateIt(dataMinAnno);
	}
	public float getChiusuraPrecedente() {
		return chiusuraPrecedente;
	}
	public void setChiusuraPrecedente(String string) {
		this.chiusuraPrecedente = UtilFuncs.repFloat(string);
	}

}
