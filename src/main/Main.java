package main;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import Mono.Metodos;
import MultiVariable.SolveMinimum;
import Tool.FunctionMath;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos.BuscaDicotomica("x^2 + 2", -3, 6, 0.01, 0.1);
		Metodos.BuscaFibonacci("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x*sin(4*x)", 0, 3, 0.1);
		System.out.println( Metodos.Bisseccao("((1.6088 + x*1.0) - 2)^4 + ((1.6088 + x*1.0) - 2*(0.8479 + x*0.0))^2 + 0.1*((1.6088 + x*1.0)^2 - (0.8479 + x*0.0))^2", -5, 0, 0.0001) );
		//System.out.println( Metodos.Newton("x^2 - 3*x +2 ", -1, 3, 0.01) );
		
		double[] a= {0.9904 , 0.8421};
		SolveMinimum.CoordenadasCiclicas("f(x1,x2) = (x1 - 2)^4 + (x1 - 2*x2)^2 + 100*(x1^2 - x2)^2", a, 0.0001);
		
	}

}
