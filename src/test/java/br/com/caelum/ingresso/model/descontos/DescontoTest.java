package br.com.caelum.ingresso.model.descontos;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;


public class DescontoTest {

	private Filme rogueOne;
	private Sala salaEldorado;
	private Sessao sessao;

	
	
	@Before
	public void preparaDesconto(){
		
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.50"));
		this.salaEldorado = new Sala("Eldorado - Imax", new BigDecimal("20.00"));
		this.sessao = 	new Sessao(LocalTime.parse("10:00:00"), rogueOne, salaEldorado);
	}
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal(){
	
	/**	
		Sala sala = new Sala("Eldorado - Imax", new BigDecimal("20.00"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
		                      "SCI-FI", new BigDecimal("12.50"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		BigDecimal precoEsperado = new BigDecimal("32.50");
		**/
//		List<Ingresso> ingresso = Arrays.asList(semDesconto);
		Ingresso semDesconto = new Ingresso(this.sessao, new SemDesconto());
		
		BigDecimal precoEsperado = new BigDecimal("32.50");
		Assert.assertEquals(precoEsperado, semDesconto.getPreco());
		
		
	}
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos(){
		
		/** 
		Sala sala = new Sala("Eldorado - Imax", new BigDecimal("20.50"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
		                      "SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		**/
		Ingresso descontoParaBancos = new Ingresso(this.sessao, new DescontoParaBancos());
		BigDecimal precoEsperado = new BigDecimal("22.75");
		Assert.assertEquals(precoEsperado, descontoParaBancos.getPreco());
		
	}

	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressosDeEstudante(){
	
		/**
		Sala sala = new Sala("Eldorado - Imax", new BigDecimal("20.50"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
		                      "SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		**/
		Ingresso descontoParaEstudantes = new Ingresso(this.sessao, new DescontoParaEstudantes());
		BigDecimal precoEsperado = new BigDecimal("16.25");
		Assert.assertEquals(precoEsperado, descontoParaEstudantes.getPreco());
		
	}

}
