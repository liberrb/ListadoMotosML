package com.examen.motos.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class ListadoMarcaPrecio {
	
	private HashMap<String, ArrayList<Integer>> MarcapromedioARS = new HashMap<String, ArrayList<Integer>>();
	private HashMap<String, ArrayList<Integer>> MarcapromedioUSD = new HashMap<String, ArrayList<Integer>>();
	
	public HashMap<String, ArrayList<Integer>> getMarcapromedioARS() {
		return MarcapromedioARS;
	}
	public void setMarcapromedioARS(HashMap<String, ArrayList<Integer>> marcapromedioARS) {
		MarcapromedioARS = marcapromedioARS;
	}
	public HashMap<String, ArrayList<Integer>> getMarcapromedioUSD() {
		return MarcapromedioUSD;
	}
	public void setMarcapromedioUSD(HashMap<String, ArrayList<Integer>> marcapromedioUSD) {
		MarcapromedioUSD = marcapromedioUSD;
	}

	

}
