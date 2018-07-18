package com.lovelacetecnologia.tdd.email;

import com.lovelacetecnologia.tdd.model.Pedido;
import com.lovelacetecnologia.tdd.service.AcaoLancamentoPedido;

public class NotificadorEmail implements AcaoLancamentoPedido  {
	

	@Override
	public void executar(Pedido pedido) {
		System.out.println("Enviando e-mail ....");
		
	}
}
