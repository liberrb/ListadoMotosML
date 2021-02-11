package com.examen.motos.response;

import java.util.HashMap;

public class ListadoMotosResponse {
	
	private HashMap<String, Double> promediosARS = new HashMap<String, Double>();
	private HashMap<String, Double> promediosUSD = new HashMap<String, Double>();
	
	public HashMap<String, Double> getPromediosARS() {
		return promediosARS;
	}
	public void setPromediosARS(HashMap<String, Double> promediosARS) {
		this.promediosARS = promediosARS;
	}
	public HashMap<String, Double> getPromediosUSD() {
		return promediosUSD;
	}
	public void setPromediosUSD(HashMap<String, Double> promediosUSD) {
		this.promediosUSD = promediosUSD;
	}



}
