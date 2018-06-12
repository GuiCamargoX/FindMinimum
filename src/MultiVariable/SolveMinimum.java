package MultiVariable;

import Mono.Metodos;
import Tool.FunctionMath;

public class SolveMinimum {
	
	public static void CoordenadasCiclicas(String funcao, double point[], double epson ){
		double direcao[][] = new double[point.length][point.length];
		double x[] = point;												//ponto inicial
		double y[][] = new double[point.length][point.length];
		double lambda, erro=1000;
		
		//create direções coordenadas
		for(int i=0; i < point.length ; i++){
			direcao[i][i] = 1;
		}
		
		FunctionMath fx= new FunctionMath(funcao);
		FunctionMath fy= new FunctionMath("w(y,lambda,d) = y + lambda*d");
		
		while( erro > epson){
			
			copyArrayToRow( y, x, 0);
			
			for(int j=0; j < point.length; j++){
				lambda = minOmega(y[j],direcao[j],fx);
				
				for(int k=0; k < point.length; k++){
					if(j+1 == point.length)
						y[j][k] = fy.calculate(y[j][k],lambda, direcao[j][k]);
					else
						y[j+1][k] = fy.calculate(y[j][k],lambda, direcao[j][k]);
				}
			
			}
			
			double dif[]= FunctionMath.DiferençaEntreVetores(y[point.length-1], y[0]); 
			erro = FunctionMath.Norma(dif);
			
			copyLastRowToArray(y,x); 
		}
		
		for(double valor: x){
			System.out.println(valor);
		}
		
	}
	
	private static double minOmega( double y[], double d[], FunctionMath fx){
		String omPos[] = new String[y.length];
		double OmegaPos, OmegaNeg;
		double Tamanh_passo = 1.5;
		double inter= 5;
		
		
		for(int i=0; i < y.length ; i++){
			omPos[i] = new String(String.valueOf(y[i]) + " + x*" + String.valueOf(d[i])); //w(y,delta,d) = y + delta*d
		}
		
		String funcaoOmega = fx.swap(omPos);
		
		FunctionMath pos = new FunctionMath("f(x) =" + funcaoOmega);
		
		OmegaPos = pos.calculate(Tamanh_passo);
		OmegaNeg = pos.calculate(-1*Tamanh_passo);
		
		if(OmegaPos < OmegaNeg){
			return Metodos.Bisseccao(funcaoOmega, 0, inter, 0.00001);
		}else{
			return Metodos.Bisseccao(funcaoOmega, -1*inter, 0, 0.00001);
		}
	
	}
	
	
	public static void copyArrayToRow(double matrix[][], double vect[], int row){
	    
		if (vect.length != matrix.length)
	        throw new IllegalArgumentException("Invalid array length");
		
		System.arraycopy( vect, 0, matrix[row], 0, vect.length);
	}
	
	
	public static void copyLastRowToArray(double matrix[][], double vect[]){
	    
		if (vect.length != matrix.length)
	        throw new IllegalArgumentException("Invalid array length");
		
		System.arraycopy( matrix[matrix.length-1], 0, vect, 0, matrix.length);
	}
	
}
