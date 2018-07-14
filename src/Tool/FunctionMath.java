package Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.udojava.evalex.AbstractFunction;
import com.udojava.evalex.Expression;
import org.mariuszgromada.math.mxparser.Function;


public class FunctionMath extends Function {
	private int num_var;
	private Expression exp;
	private FunctionMath der[];
	double H = 10.21;//0.3473;
	
	public FunctionMath(String funcao){
		super( funcao );
		exp = new Expression(this.getFunctionExpressionString());
		
		/*exp.addFunction(new AbstractFunction("der", 2) {
		    @Override
		    public BigDecimal eval(List<BigDecimal> parameters) {
		    	        exp.with("x", new BigDecimal("2"));
						BigDecimal avg = new BigDecimal(0);

						return parameters.get(2);
		    }
		    
		});*/

		
	}
		
	public BigDecimal calculate(BigDecimal... parameters) {
		String bodyFunction = new String( this.getFunctionExpressionString() );
		
		if( bodyFunction.contains("der(") ){
			bodyFunction = bodyFunction.replaceAll("\\s+","");
			
			String func = null;
			String derParam = null;
			
			Pattern pattern = Pattern.compile("^der\\(([\\w\\d\\p{Punct}]+),(\\p{Alnum}+)\\)");//^der\\(([\\w\\d]+),(\\w)\\)
			Matcher m = pattern.matcher(bodyFunction);
		    if (m.find( )) {
				func = m.group(1);
				derParam = m.group(2);
		    }else {
		          System.out.println("NO MATCH");
		    }
		      
			//--------------------------------------------

			Expression e = new Expression(func);
			int indexDerParam = -1;
			
			for(int i=0; i<this.getParametersNumber(); i++){
				if(!this.getParameterName(i).equals(derParam))
					e.with( this.getParameterName(i) , parameters[i]);
				else
					indexDerParam = i;
			}
			//-----------Points----------------------------------
			BigDecimal p1,p2,p3,p4;
			BigDecimal h = BigDecimal.valueOf(H);
			e.with(derParam, parameters[indexDerParam].subtract(h.multiply(new BigDecimal("2"))));
			p1 = e.eval();
			e.with(derParam, parameters[indexDerParam].subtract(h));
			p2 = e.eval();
			e.with(derParam, parameters[indexDerParam].add(h));
			p3 = e.eval();
			e.with(derParam, parameters[indexDerParam].add(h.multiply(new BigDecimal("2"))));
			p4 = e.eval();
			
			//System.out.println(matcher.group(1));
			
			BigDecimal resp;
			resp = p1.subtract( p2.multiply(new BigDecimal("8")) ).add(p3.multiply(new BigDecimal("8"))).subtract(p4);
			resp = resp.divide(h.multiply(new BigDecimal("12")), 10, BigDecimal.ROUND_HALF_UP );
			
			return resp;
		}
		
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
