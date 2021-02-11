package com.examen.motos.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examen.motos.bean.ListadoMarcaPrecio;
import com.examen.motos.bean.Result;
import com.examen.motos.bean.Root;

@Service
public class MotosImpl implements IMotos {

	private Root getResultados(Integer offset) {
		String url = "https://api.mercadolibre.com/sites/MLA/search?category=MLA1763&offset=" + offset;
		RestTemplate restTemplate = new RestTemplate();
		Root resultados = restTemplate.getForObject(url, Root.class);
		return resultados;
	}

	@Override
	@SuppressWarnings("deprecation")
	public Double getPromedios(ArrayList<Integer> list) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		Double sum = 0.0;
		try {
			for (int i : list) {
				sum += i;
			}

			if (!list.isEmpty()) {
				String v = df2.format(sum / list.size());
				v = v.replaceAll(",", ".");
				return new Double(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
	}

	@Async
	@Override
	public CompletableFuture<ListadoMarcaPrecio> getListadoMarcaPrecio(Integer offset) {
		try {
			ListadoMarcaPrecio lmp = new ListadoMarcaPrecio();

			for (Result res : getResultados(offset).getResults()) {
				if (res.getCondition().equalsIgnoreCase("new")) {

					String marca = res.getAttributes().get(2).getValue_name();
					Integer price = res.getPrice();

					if (res.getCurrency_id().equalsIgnoreCase("ARS")) {
						if (lmp.getMarcapromedioARS().containsKey(marca)) {
							lmp.getMarcapromedioARS().get(marca).add(price);
						} else {
							ArrayList<Integer> priceList = new ArrayList<Integer>();
							priceList.add(price);
							lmp.getMarcapromedioARS().put(marca, priceList);
						}
					}

					if (res.getCurrency_id().equalsIgnoreCase("USD")) {
						if (lmp.getMarcapromedioUSD().containsKey(marca)) {
							lmp.getMarcapromedioUSD().get(marca).add(price);
						} else {
							ArrayList<Integer> priceList = new ArrayList<Integer>();
							priceList.add(price);
							lmp.getMarcapromedioUSD().put(marca, priceList);
						}
					}
				}
			}
			return CompletableFuture.completedFuture(lmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
