
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos.BuscaUniforme("x^2 - 3*x + 2", -1, 3, 0.5);
		Metodos.BuscaDicotomica("x^2 + 2", -3, 6, 0.01, 0.1);
		Metodos.SecaoAurea("sin(3*x) + x^2", -1, 0, 0.001);
		Metodos.BuscaFibonacci("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x*sin(4*x)", 0, 3, 0.1);
		Metodos.Bisseccao("sin(3*x) + x^2", -1, 0, 0.001);
		Metodos.Newton("x^2 - 3*x + 2", -1, 3, 0.5);
	}

}
