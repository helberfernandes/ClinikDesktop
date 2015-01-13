import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Main {
	public static void main(String[] args) {
//		Splash splash = new Splash();
//		splash.Mostrar();
		
//		SplashScreen screen = new SplashScreen("/imagens/splash.png");
//		
//		screen.open(3000); 
		
		
//		  ExecutorService autoProgressExecutor = Executors.newFixedThreadPool(1);
//		  
//		
//		 autoProgressExecutor.execute(new Runnable() {
//			 
//	         public void run() {
//	        	 teste1();
//	  		   System.out.println("meio");
//	  		   teste2();
//	         }
//		 });
		 
		
		
		ThreadBarraDeProgresso1 barraDeProgresso1 = new ThreadBarraDeProgresso1();
		
		ThreadBarraDeProgresso2 barraDeProgresso2 = new ThreadBarraDeProgresso2();
		  
		
		barraDeProgresso1.start();
		barraDeProgresso2.start();
	}
	
	
	public static synchronized void teste1(){
		System.out.println("teste 1");
	}
	

	public static synchronized void teste2(){
		System.out.println("teste 2");
	}
}


 class ThreadBarraDeProgresso1 extends Thread {
	public void run() {
		System.out.println("teste 1");
		
	}
}
 
 
 class ThreadBarraDeProgresso2 extends Thread {
	public void run() {
		System.out.println("teste 2");
		
	}
}
