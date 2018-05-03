
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos.BuscaUniforme("x^2 - 3*x + 2", -1, 3, 0.5);
		Metodos.BuscaDicotomica("x^2 + 2", -3, 6, 0.01, 0.1);
		Metodos.SecaoAurea("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x^2 + 2*x", -3, 5, 0.2);
	}

}
