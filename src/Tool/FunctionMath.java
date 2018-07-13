package Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.udojava.evalex.AbstractFunction;
import com.udojava.evalex.Expression;
import org.mariuszgromada.math.mxparser.Function;


public class FunctionMath extends Function {
	private int num_var;
	private Expression exp;
	private FunctionMath der[];
	
	public FunctionMath(String funcao){
		super( funcao );
		exp = new Expression(this.getFunctionExpressionString());
		
		exp.addFunction(new AbstractFunction("der", 2) {
		    @Override
		    public BigDecimal eval(List<BigDecimal> parameters) {
						if (parameters.size() == 0) {
							try {
								throw new Exception("average requires at least one parameter");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						BigDecimal avg = new BigDecimal(0);
						
						for (BigDecimal parameter : parameters) {
								avg = avg.add(parameter);
						}
						return avg.divide(new BigDecimal(parameters.size()));
		    }
		    
		});

		
	}
		
	public BigDecimal calculate(BigDecimal... parameters) {
		
		/*if( this.getFunctionExpressionString().contains("der") ){
			double val[] = new double[parameters.length];
			int i=0;
			
			for(BigDecimal x:parameters){
				val[i] = x.doubleValue(); i++;
			}
			
			return BigDecimal.valueOf(this.calculate(val));
		}*/
		
		for(int i=0; i<this.getParametersNumber(); i++)
			exp.with( this.getParameterName(i) , parameters[i]);
		
		return exp.eval();
	}
	
	public FunctionMath[] getPartialDer(){
		der = new FunctionMath[this.getParametersNumber()];
		
		for(int i =0; i<this.getParametersNumber() ; i++ ){
			der[i] = new FunctionMath(this.getFunctionDescription()+ "= der("+this.getFunctionExpressionString()+", "+ this.getParameterName(i) +")") ;
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
	
	private static double setPrecision(double toBeTruncated,int scale){
		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
				.setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
		//System.out.println(truncatedDouble);
		return truncatedDouble;
	}
		
}
