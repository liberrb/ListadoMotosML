package com.examen.motos.controller;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.motos.bean.ListadoMarcaPrecio;
import com.examen.motos.response.ListadoMotosResponse;
import com.examen.motos.service.IMotos;

@RestController
public class MotosController {
	
	@Autowired
	IMotos motos;
	
	/**
	 * @author para correrlo GET http://localhost:8888/examen/listadoMotosML
	 */
	@GetMapping(value = "/examen/listadoMotosML", produces = "application/json")
	public CompletableFuture<ListadoMotosResponse> getListadoMultiThread(){
		try {
			ListadoMotosResponse response = new ListadoMotosResponse();
			ListadoMarcaPrecio listAux = new ListadoMarcaPrecio();
			Integer count = 0;
	        ArrayList<CompletableFuture<ListadoMarcaPrecio>> tList = new ArrayList<>();
	        
	        while(count < 900) {
	        	tList.add( motos.getListadoMarcaPrecio(count) );
	        	count += 50;
	        }
			
	        for( CompletableFuture<ListadoMarcaPrecio> pp : tList) {
	        	for (Entry<String, ArrayList<Integer>> entry : pp.get().getMarcapromedioARS().entrySet()) {
	        		if( listAux.getMarcapromedioARS().containsKey( entry.getKey() ) ){
	        			listAux.getMarcapromedioARS().get(entry.getKey()).addAll(entry.getValue());
	        		} else {
	        			listAux.getMarcapromedioARS().put(entry.getKey(), entry.getValue());
					}
				}
				
				for (Entry<String, ArrayList<Integer>> entry : pp.get().getMarcapromedioUSD().entrySet()) {
					if( listAux.getMarcapromedioUSD().containsKey( entry.getKey() ) ){
	        			listAux.getMarcapromedioUSD().get(entry.getKey()).addAll(entry.getValue());
	        		} else {
	        			listAux.getMarcapromedioUSD().put(entry.getKey(), entry.getValue());
					}
				}
	        }
	        
	        for (Entry<String, ArrayList<Integer>> entry : listAux.getMarcapromedioARS().entrySet()) {
				response.getPromediosARS().put(entry.getKey(), motos.getPromedios(entry.getValue()));
			}
			
			for (Entry<String, ArrayList<Integer>> entry : listAux.getMarcapromedioUSD().entrySet()) {
				response.getPromediosUSD().put(entry.getKey(), motos.getPromedios(entry.getValue()));
			}
			
			System.out.println( response.getPromediosARS() );
			System.out.println( response.getPromediosUSD() );
			return CompletableFuture.completedFuture(response);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
