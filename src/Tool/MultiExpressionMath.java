package Tool;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;


public class MultiExpressionMath extends Expression {
	int num_var;
	
	public MultiExpressionMath(String funcao, int num_var) {
		this.num_var = num_var;
		createArguments();
		super.setExpressionString(funcao);
	}
	
	
	private void createArguments(){
		for(int i=1; i<= num_var; i++){
			super.addArguments(new Argument("x"+i));
		}
	}
	
	public double calculate(double vect[]){
		Argument aux;
		
		for(int i=0; i<num_var; i++){
			aux= super.getArgument(i);
			aux.setArgumentValue(vect[i]);
		}
		
		return super.calculate();
	}
	
}
