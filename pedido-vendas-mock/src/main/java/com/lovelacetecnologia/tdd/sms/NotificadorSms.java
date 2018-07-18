package com.lovelacetecnologia.tdd.sms;

import com.lovelacetecnologia.tdd.model.Pedido;
import com.lovelacetecnologia.tdd.service.AcaoLancamentoPedido;

public class NotificadorSms  implements AcaoLancamentoPedido{
	
	@Override
	public void executar(Pedido pedido) {
		// TODO Auto-generated method stub
		System.out.println("Enviando SMS ....");
		
	}
}
