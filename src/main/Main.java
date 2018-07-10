package main;
import Mono.Metodos;
import MultiVariable.SolveMinimum;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos.BuscaDicotomica("x^2 + 2", -3, 6, 0.01, 0.1);
		Metodos.BuscaFibonacci("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x*sin(4*x)", 0, 3, 0.1);
	
		
		/* Function */
		double[] a= {0 , 3};
		SolveMinimum.Gradiente("f(x1,x2) = (x1-2)^4 + (x1 - 2*x2)^2", a, 0.1);

		/*double[] a= {1 , 0};
		SolveMinimum.CoordenadasCiclicas("f(x1,x2) = x1^2 + 2*x2 + 0.1*(x1^2 + x2^2 - 1)^2", a, 0.0001);*/
		
	}

}
