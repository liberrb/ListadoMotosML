package com.examen.motos.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.examen.motos.bean.ListadoMarcaPrecio;

public interface IMotos {
	
	public CompletableFuture<ListadoMarcaPrecio> getListadoMarcaPrecio(Integer offset);
	public Double getPromedios(ArrayList<Integer> list);
}
