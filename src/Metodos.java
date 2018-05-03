import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Vector;

import org.mariuszgromada.math.mxparser.*;

public class Metodos {
	
	public static void BuscaUniforme(String funcao, double a, int b,  double delta) {
		double fq, fp, p = a, q;
		Argument x = new Argument("x");
		Expression fx = new Expression(funcao,x);
		
		do {
		x.setArgumentValue(p);
		fp = fx.calculate();
		q = p + delta;
		x.setArgumentValue(q);
		fq = fx.calculate();
		p = q;
		}while(fq < fp);
		
		p = p - 2*delta;
		delta = delta/10;
		
		do {
			x.setArgumentValue(p);
			fp = fx.calculate();
			q = p + delta;
			x.setArgumentValue(q);
			fq = fx.calculate();
			p = q;
			}while(fq < fp);
		
		p = p - delta;
		System.out.println( setPrecision(p, 4) );
		
	}
	
	public static void BuscaDicotomica(String funcao, double A, double B,  double delta, double epson){
		double fq, fp, p , q;
		double a=A , b = B;
		Argument x = new Argument("x");
		Expression fx = new Expression(funcao, x );
		
		while( (b-a) >= epson){
			 p = (a+b)/2 - delta;
			 q = (a+b)/2 + delta;
			 
			 x.setArgumentValue(p);
			 fp= fx.calculate();
			 x.setArgumentValue(q);
			 fq= fx.calculate();
			 
			 if( fp < fq)
				 b=q;
			 else
				 a=p;
		}
		
		System.out.println( setPrecision( (a+b)/2 , 4) );
		
	}
	
	public static void SecaoAurea(String funcao, double A, double B,  double epson ){
		double fq, fp, p , q;
		double a=A , b = B;
		Argument x = new Argument("x");
		Expression fx = new Expression(funcao, x );
		
		double alfa= 0.61803;
		double beta= 0.38197;
		
		while( (b-a)>= epson){
			
			p = a + beta*(b-a);
			q = a + alfa*(b-a);
			
			x.setArgumentValue(p);
			fp= fx.calculate();
			x.setArgumentValue(q);
			fq= fx.calculate();
		
			if( fp < fq)
				 b=q;
			 else
				 a=p;			
			
		}
		
		System.out.println( setPrecision( (a+b)/2 , 4) );
		
	}
	
	public static void BuscaFibonacci(String funcao, double A, double B,  double epson ){
		double fq, fp, p , q;
		double a=A , b = B;
		Argument x = new Argument("x");
		Expression fx = new Expression(funcao, x );
		
		List<Integer> fib = new Vector<Integer>();
		fib.add(1);
		fib.add(1);
		int k=1;
		double it= (b-a)/epson;
		
		while( fib.get(k) < it){
			fib.add( k+1 , fib.get(k) + fib.get(k-1) );
			k++;
		}

		for(int i=0; i < k-2 ; i++){
			p = a + (fib.get( k - 2 - i).doubleValue() /fib.get( k - i ).doubleValue() ) * (b - a);
			q = a + (fib.get( k - 1 - i).doubleValue() /fib.get( k - i ).doubleValue() ) * (b - a);

			x.setArgumentValue(p);
			fp= fx.calculate();
			x.setArgumentValue(q);
			fq= fx.calculate();
		
			if( fp < fq)
				 b=q;
			 else
				 a=p;	
 		}
		
		System.out.println( setPrecision( (a+b)/2 , 4) );
		
		
	}
	
	private static double setPrecision(double toBeTruncated,int scale){
		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
				.setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
		//System.out.println(truncatedDouble);
		return truncatedDouble;
	}
	
}