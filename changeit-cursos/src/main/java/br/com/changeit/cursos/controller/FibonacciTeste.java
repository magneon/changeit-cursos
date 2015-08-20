package br.com.changeit.cursos.controller;

public class FibonacciTeste {
	
	String fibonacci = "0 1 1 2 3 5 8 13 21 34 55 89";
	
	public static void main(String[] args) {
		FibonacciTeste fibonacciTeste = new FibonacciTeste();
		
		Long begin = System.currentTimeMillis();
		System.out.println(fibonacciTeste.fibo(0, 1, 250, 0));
//		System.out.println(fibonacciTeste.fibo2());
		Long end = System.currentTimeMillis();
		
		System.out.println("Tempo total da execução do algoritmo: " + (end - begin) / 1000 + " segundos.");
	}
	
	public Integer fibo(int x, int y, int qnt, int count) {
		if (count <= qnt) {
			int t = x;
			x = x + y;
			count++;
			return fibo(x, t, qnt, count);
		} else {
			return x + y;
		}
	}
	
	public Integer fibo2() {
		int x = 0;
		int y = 1;
		int temp = 0;
		
		for (int i = 0; i <= 250; i++) {
			temp = x;
			x = x + y;
			y = temp;
		}
		
		return x;
	}

}
