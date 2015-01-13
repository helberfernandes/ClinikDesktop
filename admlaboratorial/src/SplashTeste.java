import java.io.InputStream;

import javax.swing.JFrame;


public class SplashTeste {

	public void inicia_o_programa(){
		final JFrame splash = new JFrame();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				splash.setVisible(true);
				
			}
			
			//final InputStream imageStream4 = ProgressSplashScreenTest.class.getResourceAsStream("/imagens/splash.png");
		});
		splash.dispose();
	}
	
	
}
