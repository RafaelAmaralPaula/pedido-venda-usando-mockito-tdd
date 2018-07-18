package com.lovelacetecnologia.tdd.service;

import java.util.List;

import com.lovelacetecnologia.tdd.exception.StatusPedidoInvalidoException;
import com.lovelacetecnologia.tdd.model.Pedido;
import com.lovelacetecnologia.tdd.model.StatusPedido;
import com.lovelacetecnologia.tdd.repository.Pedidos;

public class PedidoService {

	private List<AcaoLancamentoPedido> acoes;
	private Pedidos pedidos;
	
	public PedidoService(Pedidos pedido , List<AcaoLancamentoPedido> acoes) {
		this.pedidos = pedido;
		this.acoes = acoes;

	}

	public double lancar(Pedido pedido) {

		double imposto = pedido.getValor() * 0.1;


		acoes.forEach(a -> a.executar(pedido));
		
		return imposto;
	}

	public Pedido pagar(Long codigo) {
		
		Pedido pedido = pedidos.buscarPeloCodigo(codigo);
		
		if(!pedido.getStatusPedido().equals(StatusPedido.PENDENTE)){
			throw new StatusPedidoInvalidoException("");
		}
		
		pedido.setStatusPedido(StatusPedido.PAGO);
		
		return pedido;
	}

}
