package main;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;

import Mono.Metodos;
import MultiVariable.SolveMinimum;
import Tool.ExpressionMath;
import Tool.FunctionMath;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos.BuscaDicotomica("x^2 + 2", -3, 6, 0.01, 0.1);
		Metodos.BuscaFibonacci("x^2 - 3*x + 2", -1, 3, 0.1);
		Metodos.BuscaFibonacci("x*sin(4*x)", 0, 3, 0.1);
	
		
		/* Function */
		Function f = new Function("f(x1,x2) = der(x1^2 + x2, x2)");	         
		mXparser.consolePrintln("Res : " + f.getFunctionExpressionString() + " = " + f.calculate(5,96));

		/*double[] a= {1 , 0};
		SolveMinimum.CoordenadasCiclicas("f(x1,x2) = x1^2 + 2*x2 + 0.1*(x1^2 + x2^2 - 1)^2", a, 0.0001);*/
		
	}

}
