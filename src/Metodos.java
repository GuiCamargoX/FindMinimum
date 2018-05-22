import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Vector;
import org.mariuszgromada.math.mxparser.Argument;


import Tool.ExpressionMath;

public class Metodos {
	
	public static void BuscaUniforme(String funcao, double a, int b,  double delta) {
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
		System.out.println( setPrecision(p, 4) );
		
	}
	
	public static void BuscaDicotomica(String funcao, double A, double B,  double delta, double epson){
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
		
		System.out.println( setPrecision( (a+b)/2 , 4) );
		
	}
	
	public static void SecaoAurea(String funcao, double A, double B,  double epson ){
		double fq, fp, p , q;
		double a=A , b = B;
		ExpressionMath fx = new ExpressionMath(funcao, new Argument("x")) ;
		int cont=0;
		double alfa= 0.61803;
		double beta= 0.38197;
		
		while( (b-a)>= epson){
			
			p = a + beta*(b-a);
			q = a + alfa*(b-a);
			
			fp= fx.calculate(p);
			fq= fx.calculate(q);
		
			if( fp < fq)
				 b=q;
			 else
				 a=p;			
			cont++;
		}
		
		System.out.println( setPrecision( (a+b)/2 , 4) + "-----"+ cont );
		
	}
	
	public static void BuscaFibonacci(String funcao, double A, double B,  double epson ){
		double fq, fp, p , q;
		double a=A , b = B;
		ExpressionMath fx = new ExpressionMath(funcao, new Argument("x")) ;
		
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

			fp= fx.calculate(p);
			fq= fx.calculate(q);
		
			if( fp < fq)
				 b=q;
			 else
				 a=p;	
 		}
		
	
		
		System.out.println( setPrecision( (a+b)/2 , 4) );
		
		
	}
	
	public static void Bisseccao(String funcao, double A, double B, double epson) {
		double a= A, b = B;
		double x = (a+b)/2;
		double resder;
		ExpressionMath fx = new ExpressionMath("der("+funcao+", x)", new Argument("x")) ;
		while((b-a) > epson) {
			resder = fx.calculate(x);
			if(resder < 0)
				a = (a+b)/2;
			else
				b = (a+b)/2;
			
			x= (a+b)/2;
		}
		
		System.out.println(setPrecision(x, 4));
	}
	
	public static void Newton(String funcao, double A, double B, double epson) {
		double x = A;
		ExpressionMath fx1 = new ExpressionMath("der("+funcao+", x)", new Argument("x")) ;
		ExpressionMath fx2 = new ExpressionMath("der(der("+funcao+", x), x)", new Argument("x")) ;
		double resder1=100, resder2, aux=resder1;
		while(aux>epson) {
			resder1 = fx1.calculate(x);
			resder2 = fx2.calculate(x);
			
			x = x - (resder1/resder2);
			if(resder1 < 0) aux=-resder1;
			else aux = resder1;
		}
		
		System.out.println(setPrecision(x, 4));
	}
	
	private static double setPrecision(double toBeTruncated,int scale){
		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
				.setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
		//System.out.println(truncatedDouble);
		return truncatedDouble;
	}
	
}