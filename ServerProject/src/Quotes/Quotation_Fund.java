package Quotes;

import java.util.Date;

public class Quotation_Fund extends Quotation{
	
	

	
	private String nomeGestore;
	private String categoriaAssociati;
	private String benchmarkDichiarato;
	private float ultimoPrezzo;
	private Date dataUltimoPrezzo;
	private float prezzoPrecedente;
	private String valuta;
	private float variazionePercentuale;
	private float variazioneAssoluta;
	private float performance1Mese;
	private float performance3Mesi;
	private float performance1Anno;
	private float performance3Anni;
	
	
	public Quotation_Fund(String isin) {
		super(isin);
		this.setType("Fund");
	}
	
	public String getNomeGestore() {
		return nomeGestore;
	}
	public void setNomeGestore(String nomeGestore) {
		this.nomeGestore = nomeGestore;
	}
	public String getCategoriaAssociati() {
		return categoriaAssociati;
	}
	public void setCategoriaAssociati(String categoriaAssociati) {
		this.categoriaAssociati = categoriaAssociati;
	}
	public String getBenchmarkDichiarato() {
		return benchmarkDichiarato;
	}
	public void setBenchmarkDichiarato(String benchmarkDichiarato) {
		this.benchmarkDichiarato = benchmarkDichiarato;
	}
	public float getUltimoPrezzo() {
		return ultimoPrezzo;
	}
	public void setUltimoPrezzo(float ultimoPrezzo) {
		this.ultimoPrezzo = ultimoPrezzo;
	}
	public Date getDataUltimoPrezzo() {
		return dataUltimoPrezzo;
	}
	public void setDataUltimoPrezzo(Date dataUltimoPrezzo) {
		this.dataUltimoPrezzo = dataUltimoPrezzo;
	}
	public float getPrezzoPrecedente() {
		return prezzoPrecedente;
	}
	public void setPrezzoPrecedente(float prezzoPrecedente) {
		this.prezzoPrecedente = prezzoPrecedente;
	}
	public String getValuta() {
		return valuta;
	}
	public void setValuta(String valuta) {
		this.valuta = valuta;
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
	public float getPerformance1Mese() {
		return performance1Mese;
	}
	public void setPerformance1Mese(float performance1Mese) {
		this.performance1Mese = performance1Mese;
	}
	public float getPerformance3Mesi() {
		return performance3Mesi;
	}
	public void setPerformance3Mesi(float performance3Mesi) {
		this.performance3Mesi = performance3Mesi;
	}
	public float getPerformance1Anno() {
		return performance1Anno;
	}
	public void setPerformance1Anno(float performance1Anno) {
		this.performance1Anno = performance1Anno;
	}
	public float getPerformance3Anni() {
		return performance3Anni;
	}
	public void setPerformance3Anni(float performance3Anni) {
		this.performance3Anni = performance3Anni;
	}
	
	


}




