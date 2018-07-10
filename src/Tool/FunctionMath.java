package Tool;

import org.mariuszgromada.math.mxparser.Function;


public class FunctionMath extends Function {
	int num_var;
	Function der[];
	
	public FunctionMath(String funcao){
		super( funcao );
	}
	
	public Function[] getPartialDer(){
		der = new Function[this.getParametersNumber()];
		
		for(int i =0; i<this.getParametersNumber() ; i++ ){
			der[i] = new Function(this.getFunctionDescription()+ "= der("+this.getFunctionExpressionString()+", "+ this.getParameterName(i) +")") ;
		}
		
		return der;
	}
	
	public String swap(String m[]){
		String sw = new String( super.getFunctionExpressionString() );
	
		for(int i=0; i < super.getArgumentsNumber(); i++){
			sw = sw.replaceAll(super.getArgument(i).getArgumentName(), "("+m[i]+")");
		}
	
		return sw;
	}

	public String getFunctionDescription(){
		
		if(this.getParameterName(0) == "")
			return null;
		
		String param = new String(this.getFunctionName() + "(" +this.getParameterName(0));
		
		for(int k=1; k<this.getParametersNumber() ; k++)
			param+= ","+this.getParameterName(k);
		
		param+=")";
		
		return param;
	}
	
	public static double Norma(double vect[]){
		double max = Math.abs(vect[0]);
		
		for(int i=1; i<vect.length; i++){
			if( Math.abs(vect[i]) > max )
				max = Math.abs(vect[i]);
		}
		
		return max;
	}
	
	public static double[] DiferençaEntreVetores(double vect1[], double vect2[]){
		double dif[] = new double[vect1.length];
		
		if(vect1.length != vect2.length)
			new Exception();
		
		for(int i=0; i<vect1.length ; i++){
			dif[i] = vect1[i] - vect2[i];
		}
		
		return dif;
	}
		
}
