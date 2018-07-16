package main;

import java.math.BigDecimal;

import org.mariuszgromada.math.mxparser.Function;

import Mono.Metodos;
import MultiVariable.SolveMinimum;
import Tool.FunctionMath;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Metodos.BuscaDicotomica("f(x)= x^2 - 3*x + 2", -1, 3, 0.01, 0.1)) ;
		System.out.println(Metodos.Bisseccao("f(x)= x^2 - 3*x + 2", -1, 3, 0.1)) ;
		System.out.println(Metodos.Newton("f(x)= x^2 - 3*x + 2", -1, 3, 0.1, 0) );
		//System.out.println(Metodos.Newton("f(x)= e^x - x^3 + 1", -1, 6, 0.0001, 5) );
		
		FunctionMath p = new FunctionMath("f(x)= e^x - x^3 + 1");
		System.out.println(p.FinDiff(2, "x", new BigDecimal("5")));
		/* Function 
		double[] a= {0 , 3};
		SolveMinimum.Gradiente("f(x1,x2) = (x1-2)^4 + (x1 - 2*x2)^2", a, 0.1);

		/*double[] a= {1 , 0};
		SolveMinimum.CoordenadasCiclicas("f(x1,x2) = x1^2 + 2*x2 + 0.1*(x1^2 + x2^2 - 1)^2", a, 0.0001);*/
		
	}

}
