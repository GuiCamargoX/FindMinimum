package Tool;

import org.mariuszgromada.math.mxparser.Function;


public class FunctionMath extends Function {
	int num_var;
	
	public FunctionMath(String funcao){
		super( funcao );
	}
	
	public String swap(String m[]){
		String sw = new String( super.getFunctionExpressionString() );
	
		for(int i=0; i < super.getArgumentsNumber(); i++){
			sw = sw.replace(super.getArgument(i).getArgumentName(), "("+m[i]+")");
		}
	
		return sw;
	}
	
	
}
