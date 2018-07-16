package Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.udojava.evalex.Expression;
import org.mariuszgromada.math.mxparser.Function;


public class FunctionMath extends Function {
	private int num_var;
	private Expression exp;
	private FunctionMath der[];
	
	public FunctionMath(String funcao){
		super( funcao );
		exp = new Expression(this.getFunctionExpressionString());
	}
	
	public BigDecimal FinDiff(int DerivativeOrder, String derParam, BigDecimal... parameters){
		BigDecimal resp = null;
		BigDecimal p0,p1,p2,p3,p4;
		BigDecimal h = new BigDecimal("0.001");/*0.001*/
		
		//--------------------------------------------

		Expression e = new Expression( this.getFunctionExpressionString() );
		e.setPrecision(1000);
		int indexDerParam = -1;
		
		for(int i=0; i<this.getParametersNumber(); i++){
			if(!this.getParameterName(i).equals(derParam))
				e.with( this.getParameterName(i) , parameters[i]);
			else
				indexDerParam = i;
		}
		//-----------Points----------------------------------			
		e.with(derParam, parameters[indexDerParam].subtract(h.multiply(new BigDecimal("2.0"))));
		p1 = e.eval();
		e.with(derParam, parameters[indexDerParam].subtract(h));
		p2 = e.eval();
		e.with(derParam, parameters[indexDerParam]);
		p0 = e.eval();
		e.with(derParam, parameters[indexDerParam].add(h));
		p3 = e.eval();
		e.with(derParam, parameters[indexDerParam].add(h.multiply(new BigDecimal("2.0"))));
		p4 = e.eval();
			
		if( DerivativeOrder == 1 ){			      			
			resp = p1.subtract( p2.multiply(new BigDecimal("8")) ).add(p3.multiply(new BigDecimal("8"))).subtract(p4);
			resp = resp.divide(h.multiply(new BigDecimal("12")), 10, BigDecimal.ROUND_HALF_UP );
		}
		
		if( DerivativeOrder == 2){
			resp = p1.multiply(new BigDecimal("-1")).add(p2.multiply(new BigDecimal("16"))).subtract(p0.multiply(new BigDecimal("30")))
					.add(p3.multiply(new BigDecimal("16"))).subtract(p4);
			h = h.pow(2);
			resp = resp.divide(h.multiply(new BigDecimal("12")), 10, BigDecimal.ROUND_HALF_UP );	
		}
		
		
		return resp;
		
	}
		
	public BigDecimal calculate(BigDecimal... parameters) {
		
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
