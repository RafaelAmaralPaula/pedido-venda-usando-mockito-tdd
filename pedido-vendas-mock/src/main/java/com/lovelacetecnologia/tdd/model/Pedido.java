package com.lovelacetecnologia.tdd.model;

public class Pedido {

	private double valor;
	private Cliente cliente;
	private StatusPedido statusPedido;

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
