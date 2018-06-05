package Testes;

import static org.junit.Assert.*;
import org.junit.Test;
import Mono.Metodos;

public class MetodosTest {

	@Test
	public void testBuscaUniforme() {		/*expected*/ /*object*/
		assertEquals("Result must be 1.50", 1.5 , Metodos.BuscaUniforme("x^2 - 3*x + 2", -1, 3, 0.5), 0);
		
		assertEquals("Result must be -1.00", -1.00 , Metodos.BuscaUniforme("x^2 + 2*x", -3, 6, 0.5), 0);
	}

	@Test
	public void testSecaoAurea() {
		assertEquals("Result must be 1.5147", 1.5147 , Metodos.SecaoAurea("x^2 - 3*x + 2", -1, 3, 0.1), 0);
		
		assertEquals("Result must be -0.9852", -0.9852 , Metodos.SecaoAurea("x^2 + 2*x", -3, 6, 0.1), 0);
	}

}
