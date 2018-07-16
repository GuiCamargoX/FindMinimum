package Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.udojava.evalex.Expression;
import org.mariuszgromada.math.mxparser.Function;


public class FunctionMath extends Function {
	private int num_var;
	private Expression exp;
	private FunctionMath der[];
	
	private static final BigDecimal SQRT_DIG = new BigDecimal(150);
	private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

	
	public FunctionMath(String funcao){
		super( funcao );
		exp = new Expression(this.getFunctionExpressionString());		
	}
		
	public BigDecimal calculate(BigDecimal... parameters) {
		String bodyFunction = new String( this.getFunctionExpressionString() );
		
		if( bodyFunction.contains("der1(") ){
			BigDecimal x = parameters[0];
		
			bodyFunction = bodyFunction.replaceAll("\\s+","");
			
			String func = null;
			String derParam = null;
			
			Pattern pattern = Pattern.compile("^der1\\(([\\w\\d\\p{Punct}]+),(\\p{Alnum}+)\\)");//^der\\(([\\w\\d]+),(\\w)\\)
			Matcher m = pattern.matcher(bodyFunction);
		    if (m.find( )) {
				func = m.group(1);
				derParam = m.group(2);
		    }else {
		          System.out.println("NO MATCH");
		    }
		      
			//--------------------------------------------

			Expression e = new Expression(func);
			e.setPrecision(1000);
			int indexDerParam = -1;
			
			for(int i=0; i<this.getParametersNumber(); i++){
				if(!this.getParameterName(i).equals(derParam))
					e.with( this.getParameterName(i) , parameters[i]);
				else
					indexDerParam = i;
			}
			//-----------Points----------------------------------
			BigDecimal p1,p2,p3,p4;
			BigDecimal h = new BigDecimal("0.00000000001");
			
			System.out.println( parameters[indexDerParam].subtract(h.multiply(new BigDecimal("2.0"))).pow(3) ) ;
			e.with(derParam, parameters[indexDerParam].subtract(h.multiply(new BigDecimal("2.0"))));
			p1 = e.eval();
			e.with(derParam, parameters[indexDerParam].subtract(h));
			p2 = e.eval();
			e.with(derParam, parameters[indexDerParam].add(h));
			p3 = e.eval();
			e.with(derParam, parameters[indexDerParam].add(h.multiply(new BigDecimal("2.0"))));
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

	/**
	 * Private utility method used to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti 
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
	    BigDecimal fx = xn.pow(2).add(c.negate());
	    BigDecimal fpx = xn.multiply(new BigDecimal(2));
	    BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
	    xn1 = xn.add(xn1.negate());
	    BigDecimal currentSquare = xn1.pow(2);
	    BigDecimal currentPrecision = currentSquare.subtract(c);
	    currentPrecision = currentPrecision.abs();
	    if (currentPrecision.compareTo(precision) <= -1){
	        return xn1;
	    }
	    return sqrtNewtonRaphson(c, xn1, precision);
	}

	/**
	 * Uses Newton Raphson to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti 
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	public static BigDecimal bigSqrt(BigDecimal c){
	    return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
	}
	
	private static double setPrecision(double toBeTruncated,int scale){
		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
				.setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
		//System.out.println(truncatedDouble);
		return truncatedDouble;
	}
		
}
