import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import br.com.wofsolutions.gui.FrmLogin;


public class Testrel {
public static void main(String[] args) {
	
	try {
		Runtime.getRuntime().exec("java -splash:imagens/splash.png");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	modoDois();
	
}
public static void modoDois(){
	  //Criar uma referencia pro objeto SplashScreen
	  SplashScreen minhaSplash = SplashScreen.getSplashScreen();
	
	  //Se carregado corretamente então vamos em frente
	  if(minhaSplash == null){
		    System.err.println("Especifique o nome da imagem através do parâmetro \"-splash:\" " 

		                    + "ou dentro do arquivo manifest. Certifique que a imagem exista.");
		    System.exit(1);
		  }

	  //Pra este modo, vamos precisar das informações da imagem carregada
	  final Image img = Toolkit.getDefaultToolkit().getImage(Splash.class.getResource("/imagens/splash.png"));
	  //Vamos também criar um frame sem borda que será colocado sobre a splash
	  final JFrame splashFrame = new JFrame();
	  //Vamos forçar que este frame não tenha uma aparência convecional, ou
	  //seja, que ele não se pareça com uma janela tipica do sistema operacional
	  splashFrame.setUndecorated(true);

	  //Vamos criar um painel pra janela que acabamos de criar
	  final JPanel splashPanel = new JPanel(){
	    //Vamos redefinir o método painComponent, pro nosso caso,
	    //colocando o imagem da splash que capturamos anteriormente
	    @Override
	    public void paintComponent(Graphics g)
	    {
	      g.drawImage(img, 0, 0, null);
	    }
	  };

	  //Vamos colocar um progress bar no painel que acabamos de criar
	  final JProgressBar progressBar = new JProgressBar();
	  //O componente JProgressBar nos permite escrever uma mensagem dentro
	  //do componente, informando o status da operação.
	  progressBar.setStringPainted(true);

	  //O painel que criamos JPanel precisar de um gerenciador de layout,
	  //isso nos permitirá posicionar corretamente os componentes que
	  //adicionamos no painel, neste caso, o progress bar
	  splashPanel.setLayout(new BorderLayout());
	  //Agora que já temos um gerenciador de layout pro painel, vamos
	  //adicionar o componente progress bar e posiciona-lo - vamos posiciona-lo
	  //na parte inferior da imagem (sul)
	  splashPanel.add(progressBar, BorderLayout.SOUTH);
	        
	  //Criamos o painel com a imagem + o componente progress bar. Vamos agora
	  //adicionar este painel em nossa janela
	  splashFrame.add(splashPanel);
	  //Vamos definir a posição e tamanho desta janela
	  splashFrame.setBounds(minhaSplash.getBounds());
	  //e é claro, torna-la visivel
	  splashFrame.setVisible(true);

	  //A classe SwingWorker é utilizada aqui para atualização do SplashPanel
	  //Essa classe é sempre utilizada quando se tem uma tarefa rodando em
	  //background e necessita atualizar alguma interface de usuário.
	  //Obs: É a primeira vez que estou vendo o uso da classe SwingWorker,
	  //podemos em uma outra ocasião criar um post específico pra ela.
	  new SwingWorker(){

	    @Override
	    protected Void doInBackground() throws Exception{
	      try{
	        for(int i=0; i<=100; i=i+2){
	          publish(i);
	          Thread.sleep(100);
	        }
	      }
	      catch(InterruptedException e)
	      {
	      }
	      return null;
	    }

	    //É na redefinição do método process que atualizamos o componente
	    //progress bar: valor e mensagem.
	    @Override
	    protected void process(List passos){
	    	List<Integer> p=passos;
	      for(Integer i : p){
	        progressBar.setString("Carregando módulo: " + i);
	        progressBar.setValue(i);
	        splashPanel.repaint();
	      }
	    }

	    //Utilizamos o método done pra finalizamos a operação de nossa
	    //janela (falsa splash). Diferente da splash original que desaparece
	    //quando uma nova janela se torna visivel, precisamos forçar nossa
	    //falsa janela splash a desaparecer no termino do processo.
	    @Override
	    protected void done(){
	      splashFrame.setVisible(false);
	      java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	          new FrmLogin().setVisible(true);
	        }
	      });
	    }

	  }.execute();
	}

}
