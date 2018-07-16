package Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Vector;
import Tool.FunctionMath;

public class Metodos {
	static int scale = 10;
	
	public static double BuscaUniforme(String funcao, double a, double b,  double d) {
		BigDecimal fq, fp, q;
		BigDecimal p = BigDecimal.valueOf(a);
		BigDecimal delta = BigDecimal.valueOf(d);
		FunctionMath fx = new FunctionMath(funcao) ;
		
		do {
		fp = fx.calculate(p);
		q = p.add(delta);
		fq = fx.calculate(q);
		p = q;
		}while(fq.compareTo(fp) < 0);
		
		p = p.subtract( delta.multiply(new BigDecimal("2.0")) );
		delta = delta.divide(new BigDecimal("10") );
		
		do {
			fp = fx.calculate(p);
			q = p.add(delta);
			fq = fx.calculate(q);
			p = q;
			}while(fq.compareTo(fp) < 0);
		
		p = p.subtract( delta );
		return p.doubleValue();
		
	}
	
	public static double BuscaDicotomica(String funcao, double A, double B,  double DELTA, double EPSON){
		BigDecimal fq, fp, p , q;
		
		BigDecimal a = BigDecimal.valueOf(A);
		BigDecimal b = BigDecimal.valueOf(B);
		BigDecimal epson = BigDecimal.valueOf(EPSON);
		BigDecimal delta = BigDecimal.valueOf(DELTA);
		BigDecimal erro = b.subtract(a);
		FunctionMath fx = new FunctionMath(funcao) ;
		
		while( erro.compareTo(epson) >= 0){
			 p = a.add(b).divide( new BigDecimal("2")).subtract(delta); /*(a+b)/2 - delta;*/
			 q = a.add(b).divide( new BigDecimal("2")).add(delta); /*(a+b)/2 + delta;*/
			 
			 fp= fx.calculate(p);
			 fq= fx.calculate(q);
			 
			 if( fp.compareTo(fq) < 0)
				 b=q;
			 else
				 a=p;
			 
			 erro = b.subtract(a);
		}
		
		return a.add(b).divide( new BigDecimal("2") ).doubleValue();
		
	}
	
	public static double SecaoAurea(String funcao, double A, double B,  double EPSON ){
		BigDecimal fq, fp, p , q;
		
		BigDecimal a = BigDecimal.valueOf(A);
		BigDecimal b = BigDecimal.valueOf(B);
		BigDecimal epson = BigDecimal.valueOf(EPSON);
		BigDecimal erro = b.subtract(a);
		FunctionMath fx = new FunctionMath(funcao) ;
		
		BigDecimal alfa= new BigDecimal("0.61803");
		BigDecimal beta= new BigDecimal("0.38197");
		
		if(b.compareTo(a) < 0)
			throw new IllegalArgumentException();
		
		while( erro.compareTo(epson) >= 0 ){
			
			p = a.add( beta.multiply(erro) ) ;/*a + beta*(b-a);*/
			q = a.add( alfa.multiply(erro) ) ;/*a + alfa*(b-a);*/
			
			fp= fx.calculate(p);
			fq= fx.calculate(q);
		
			if( fp.compareTo(fq) < 0)
				 b=q;
			 else
				 a=p;
		
			erro = b.subtract(a);
		}
		
		return a.add(b).divide( new BigDecimal("2") ).doubleValue();
		
	}
	
	public static double BuscaFibonacci(String funcao, double A, double B,  double EPSON ){
		BigDecimal fq, fp, p , q;
		
		BigDecimal a = BigDecimal.valueOf(A);
		BigDecimal b = BigDecimal.valueOf(B);
		BigDecimal erro = b.subtract(a);
		BigDecimal fibo1,fibo2,fiboR;
		FunctionMath fx = new FunctionMath(funcao) ;
		
		List<Integer> fib = new Vector<Integer>();
		fib.add(1);
		fib.add(1);
		int k=1;
		double it= (B-A)/EPSON;
		
		while( fib.get(k) < it){
			fib.add( fib.get(k) + fib.get(k-1) );
			k++;
		}

		for(int i=0; i < k-2 ; i++){
			 fibo1 = new BigDecimal( fib.get( k - 2 - i).toString() );
			 fibo2 = new BigDecimal( fib.get( k - i ).toString() );
			 fiboR = fibo1.divide( fibo2, scale , BigDecimal.ROUND_HALF_UP );
			 p = a.add( fiboR.multiply(erro) );
			/*p = a + (fib.get( k - 2 - i).doubleValue() /fib.get( k - i ).doubleValue() ) * (b - a);*/
			
			fibo1 = new BigDecimal( fib.get( k - 1 - i).toString());
			fiboR = fibo1.divide(fibo2, scale , BigDecimal.ROUND_HALF_UP);
			q = a.add( fiboR.multiply(erro) );
			/*q = a + (fib.get( k - 1 - i).doubleValue() /fib.get( k - i ).doubleValue() ) * (b - a);*/

			fp= fx.calculate(p);
			fq= fx.calculate(q);
		
			if( fp.compareTo(fq) < 0 )
				 b=q;
			 else
				 a=p;
		
			erro = b.subtract(a);
 		}
				
		return a.add(b).divide( new BigDecimal("2") ).doubleValue();
		
	}
	

	public static double Bisseccao(String funcao, double A, double B, double EPSON) {
		
		BigDecimal a = BigDecimal.valueOf(A);
		BigDecimal b = BigDecimal.valueOf(B);
		BigDecimal epson = BigDecimal.valueOf(EPSON);
		BigDecimal erro = b.subtract(a);
		BigDecimal x = null, resder ;
		
		
		FunctionMath fx = new FunctionMath(funcao) ;
		
		while( erro.compareTo(epson) > 0) {
			x = a.add(b).divide( new BigDecimal("2") );
			resder = fx.FinDiff(1, fx.getParameterName(0), x) ;
			
			if(resder.compareTo(new BigDecimal("0")) == 0){
				b=a; //sai do loop
			}else{
				if(resder.compareTo(new BigDecimal("0")) < 0)
					a = x;
				else
					b = x;
			}
			
			erro = b.subtract(a);	
		}
		
		return x.doubleValue();
		
	}
	

	public static double Newton(String funcao, double A, double B, double EPSON, double x0) {
		BigDecimal a = BigDecimal.valueOf(A);
		BigDecimal b = BigDecimal.valueOf(B);
		BigDecimal epson = BigDecimal.valueOf(EPSON);

		BigDecimal x = null;
		BigDecimal resder1 = new BigDecimal("100");
		BigDecimal resder2;

		x = BigDecimal.valueOf(x0) ; //valor inicial
		
		FunctionMath fx = new FunctionMath(funcao) ;
		
		while( resder1.abs().compareTo(epson) > 0) {
			resder1 = fx.FinDiff(1, fx.getParameterName(0) , x);
			resder2 = fx.FinDiff(2, fx.getParameterName(0), x);
			
			if(resder2.compareTo(new BigDecimal("0")) == 0)
				throw new IllegalArgumentException();
			
			x = x.subtract(resder1.divide( resder2 , 10, BigDecimal.ROUND_HALF_UP) );/*x - (resder1/resder2);*/
			
		}
		
		return x.doubleValue();
	}

	
}