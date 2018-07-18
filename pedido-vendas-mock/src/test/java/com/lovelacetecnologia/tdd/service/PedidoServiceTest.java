package com.lovelacetecnologia.tdd.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.lovelacetecnologia.tdd.builder.PedidoBuilder;
import com.lovelacetecnologia.tdd.email.NotificadorEmail;
import com.lovelacetecnologia.tdd.exception.StatusPedidoInvalidoException;
import com.lovelacetecnologia.tdd.model.Pedido;
import com.lovelacetecnologia.tdd.model.StatusPedido;
import com.lovelacetecnologia.tdd.repository.Pedidos;
import com.lovelacetecnologia.tdd.sms.NotificadorSms;

public class PedidoServiceTest {

	private PedidoService pedidoService;

	private Pedido pedido;

	@Mock
	private Pedidos pedidos;

	@Mock
	private NotificadorEmail email;

	@Mock
	private NotificadorSms sms;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		List<AcaoLancamentoPedido> acoes = Arrays.asList(pedidos, email, sms);
		pedidoService = new PedidoService(pedidos, acoes);
		pedido = new PedidoBuilder().comValor(100.0).para("Rafael", "rafael@contato.com", "9999-0000").construir();

	}

	@Test
	public void deveCalcularOImposto() throws Exception {

		double imposto = pedidoService.lancar(pedido);
		assertEquals(10.0, imposto, 0.0001);

	}

	@Test
	public void deveSalvarPedidoNoBancoDeDados() throws Exception {

		pedidoService.lancar(pedido);
		verify(pedidos).executar(pedido);

	}

	@Test
	public void deveLancareEEnviarEmail() throws Exception {

		pedidoService.lancar(pedido);
		verify(email).executar(pedido);
	}

	@Test
	public void deveLancarENotificarPorSms() throws Exception {

		pedidoService.lancar(pedido);
		verify(sms).executar(pedido);

	}

	@Test
	public void devePagarPedidoPendente() throws Exception {

		Long codigo = 135L;

		Pedido pedidoPendente = new Pedido();

		pedidoPendente.setStatusPedido(StatusPedido.PENDENTE);

		when(pedidos.buscarPeloCodigo(codigo)).thenReturn(pedidoPendente);

		Pedido pedidoPago = pedidoService.pagar(codigo);

		assertEquals(StatusPedido.PAGO, pedidoPago.getStatusPedido());
	}

	@Test(expected = StatusPedidoInvalidoException.class)
	public void deveNegarPagamento() throws Exception {

		Long codigo = 135L;

		Pedido pedidoPendente = new Pedido();

		pedidoPendente.setStatusPedido(StatusPedido.PAGO);

		when(pedidos.buscarPeloCodigo(codigo)).thenReturn(pedidoPendente);

		pedidoService.pagar(codigo);

	}

}
