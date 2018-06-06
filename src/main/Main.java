package main;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import Mono.Metodos;
import Tool.FunctionMath;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos.BuscaDicotomica("x^2 + 2", -3, 6, 0.01, 0.1);
		Metodos.BuscaFibonacci("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x*sin(4*x)", 0, 3, 0.1);
		System.out.println( Metodos.Bisseccao("(10 + x*5)*2 + (x)", -11, 0, 0.00001) );
		//System.out.println( Metodos.Newton("x^2 - 3*x +2 ", -1, 3, 0.01) );
		
		
		
		
		
		
		
		String m[] = {"10 + x*5", "x^10"};
		FunctionMath f = new FunctionMath("f(x1,x2) = x1*2 + x2");

		System.out.println(f.swap(m));
		
		/*double[] a= {1 , 9};
		MultiExpressionMath t= new MultiExpressionMath("x1 + x2 ", 2);
		System.out.println(t.calculate(a));*/
	}

}
