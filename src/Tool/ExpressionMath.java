package Tool;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;


public class ExpressionMath extends Expression{
	
	public ExpressionMath(String funcao, Argument x) {
		super(funcao, x);
	}
	
	public  double calculate(double x) {
		Argument aux = this.getArgument("x");
		aux.setArgumentValue(x);
		return this.calculate();
	}
	
}
