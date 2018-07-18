package com.lovelacetecnologia.tdd.repository;

import com.lovelacetecnologia.tdd.model.Pedido;
import com.lovelacetecnologia.tdd.service.AcaoLancamentoPedido;

public class Pedidos implements AcaoLancamentoPedido {

	@Override
	public void executar(Pedido pedido) {
		System.out.println("Salvando no banco de dados....");

	}
	
	public Pedido buscarPeloCodigo(Long codigo) {
		return new Pedido();
	}

}
