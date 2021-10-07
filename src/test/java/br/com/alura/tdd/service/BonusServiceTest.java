package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	private BonusService service;

	@BeforeEach
	public void inicializar() {
		service = new BonusService();
	}

	@Test
	void bonusDeveriaSerZeroParaSalarioMuitoAlto() {
		assertThrows(IllegalArgumentException.class,
				() -> service.calcularBonus(new Funcionario("John Doe", LocalDate.now(), new BigDecimal("25000"))));
	}

	@Test
	void bonusDeveriaSerDezPorCentoDoSalario() {
		BigDecimal bonus = service.calcularBonus(new Funcionario("John Doe", LocalDate.now(), new BigDecimal("2500")));
		assertEquals(new BigDecimal("250.00"), bonus);
	}

	@Test
	void bonusDeveriaSerDezPorCentoParaSalarioDeExatamente10Mil() {
		BigDecimal bonus = service.calcularBonus(new Funcionario("John Doe", LocalDate.now(), new BigDecimal("10000")));
		assertEquals(new BigDecimal("1000.00"), bonus);
	}

}
