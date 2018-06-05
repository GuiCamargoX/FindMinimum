package Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Vector;
import org.mariuszgromada.math.mxparser.Argument;
import Tool.ExpressionMath;

public class Metodos {
	
	public static double BuscaUniforme(String funcao, double a, double b,  double delta) {
		double fq, fp, p = a, q;
		ExpressionMath fx = new ExpressionMath(funcao, new Argument("x")) ;
		
		do {
		fp = fx.calculate(p);
		q = p + delta;
		fq = fx.calculate(q);
		p = q;
		}while(fq < fp);
		
		p = p - 2*delta;
		delta = delta/10;
		
		do {
			fp = fx.calculate(p);
			q = p + delta;
			fq = fx.calculate(q);
			p = q;
			}while(fq < fp);
		
		p = p - delta;
		return setPrecision(p, 4);
		
	}
	
	public static double BuscaDicotomica(String funcao, double A, double B,  double delta, double epson){
		double fq, fp, p , q;
		double a=A , b = B;
		ExpressionMath fx = new ExpressionMath(funcao, new Argument("x")) ;
		
		while( (b-a) >= epson){
			 p = (a+b)/2 - delta;
			 q = (a+b)/2 + delta;
			 
			 fp= fx.calculate(p);
			 fq= fx.calculate(q);
			 
			 if( fp < fq)
				 b=q;
			 else
				 a=p;
		}
		
		return setPrecision( (a+b)/2 , 4 );
		
	}
	
	public static double SecaoAurea(String funcao, double A, double B,  double epson ){
		double fq, fp, p , q;
		double a=A , b = B;
		ExpressionMath fx = new ExpressionMath(funcao, new Argument("x")) ;
		double alfa= 0.61803;
		double beta= 0.38197;
		
		if(b<a)
			throw new IllegalArgumentException();
		
		while( (b-a)>= epson){
			
			p = a + beta*(b-a);
			q = a + alfa*(b-a);
			
			fp= fx.calculate(p);
			fq= fx.calculate(q);
		
			if( fp < fq)
				 b=q;
			 else
				 a=p;
		}
		
		return setPrecision((a+b)/2 , 4) ;
		
	}
	
	public static double BuscaFibonacci(String funcao, double A, double B,  double epson ){
		double fq, fp, p , q;
		double a=A , b = B;
		ExpressionMath fx = new ExpressionMath(funcao, new Argument("x")) ;
		
		List<Integer> fib = new Vector<Integer>();
		fib.add(1);
		fib.add(1);
		int k=1;
		double it= (b-a)/epson;
		
		while( fib.get(k) < it){
			fib.add( fib.get(k) + fib.get(k-1) );
			k++;
		}

		for(int i=0; i < k-2 ; i++){
			p = a + (fib.get( k - 2 - i).doubleValue() /fib.get( k - i ).doubleValue() ) * (b - a);
			q = a + (fib.get( k - 1 - i).doubleValue() /fib.get( k - i ).doubleValue() ) * (b - a);

			fp= fx.calculate(p);
			fq= fx.calculate(q);
		
			if( fp < fq)
				 b=q;
			 else
				 a=p;	
 		}
				
		return setPrecision( (a+b)/2 , 4);
		
		
	}
	
	public static double Bisseccao(String funcao, double A, double B, double epson) {
		
		double a= A, b = B , x = 0;
		double resder;
		ExpressionMath fx = new ExpressionMath("der("+funcao+", x)", new Argument("x")) ;
		
		while((b-a) > epson) {
			x = (a+b)/2;
			resder = fx.calculate(x);
			
			if(resder == 0){
				b=a; //sai do loop
			}else{
				if(resder < 0)
					a = x;
				else
					b = x;
			}
			
			
		}
		
		return setPrecision(x, 4);
		
	}
	
	
	public static double Newton(String funcao, double A, double B, double epson) {

		double x = (B+A)/2 ; //chute inicial
		double resder1=100, resder2;
		
		ExpressionMath fx1 = new ExpressionMath("der("+funcao+", x)", new Argument("x") ) ;
		ExpressionMath fx2 = new ExpressionMath("der(der("+funcao+", x), x)", new Argument("x")) ;
		
		
		while( Math.abs(resder1) > epson) {
			resder1 = fx1.calculate(x);
			resder2 = fx2.calculate(x);
			
			if(resder2 == 0)
				throw new IllegalArgumentException();
			
			x = x - (resder1/resder2);
			
		}
		
		return setPrecision(x, 4);
	}
	
	
	private static double setPrecision(double toBeTruncated,int scale){
		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
				.setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
		//System.out.println(truncatedDouble);
		return truncatedDouble;
	}
	
}