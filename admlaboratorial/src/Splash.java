

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 * Created on Nov 16, 2002 - 5:21:37 AM
 * @author rafael
 */
public class Splash extends javax.swing.JWindow  
{  
    private JLabel lblImagem;  
    private JLabel textoCarregando;  
    private static JLabel textoDinamico;  
    private static JProgressBar progressBar;  
    
  
    public Splash()  
    {  
        addComponenets();  
    }  
  
    private void addComponenets()  
    {  
        /** 
         * Inicializando as variavaeis utilizadas 
         */  
        progressBar = new JProgressBar();  
        lblImagem = new JLabel();  
        textoCarregando = new JLabel();  
        textoDinamico = new JLabel();  
        /** 
         * Carregando a imagem do Splash e adicionando a imagem ao componente 
         * jLabelSplashImage 
         */  
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
        URL imageUrl = getClass().getResource ("imagens/splash.png");
       
        
        ImageIcon imageIcon = new javax.swing.ImageIcon(imageUrl);  
        lblImagem.setIcon(imageIcon);  
        /** 
         * Definindo dinamicamente o tamando do container segundo o tamanho da imagem. 
         */  
        this.setMinimumSize(new java.awt.Dimension(imageIcon.getIconWidth(),imageIcon.getIconHeight()));  
        lblImagem.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());  
        /** 
         * A definicao do layout=null e importante para possibilitar que os componentes 
         * fiquem sobrescritros em tempo de execucao 
         */  
        getContentPane().setLayout(null);  
        /** 
         * Definindo a localizacao do splash no centro da tela 
         */  
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);  
  
        /** 
         * Setando parametros da variavel jProgressBarSistema 
         */  
        progressBar.setForeground(Color.GREEN);  
        progressBar.setPreferredSize(new java.awt.Dimension(148, 5));  
        progressBar.setBounds(50, 400, 500, 12);  
        progressBar.setBorderPainted(false);  
        progressBar.setIndeterminate(true);  
        /** 
         * Adicionando o jProgressBarSistema a classe SplashJProgressBar 
         */  
        getContentPane().add(progressBar);  
  
        /** 
         * Setando parametros da variavel jProgressBarSistema 
         */  
        textoCarregando.setForeground(Color.BLUE);  
        textoCarregando.setFont(new java.awt.Font("DialogInput", 0, 12));  
        textoCarregando.setText("Carregando...");  
        textoCarregando.setBounds(50, 400, 100, 20);  
        /** 
         * Adicionando o jProgressBarSistema a classe SplashJProgressBar 
         */  
        this.getContentPane().add(textoCarregando);  
  
        /** 
         * Setando parametros da variavel jProgressBarSistema 
         */  
        textoDinamico.setForeground(Color.BLUE);  
        textoDinamico.setFont(new java.awt.Font("DialogInput", 0, 12));  
        textoDinamico.setBounds(360, 285, 230, 20);  
        /** 
         * Adicionando o jProgressBarSistema a classe SplashJProgressBar 
         */  
        this.getContentPane().add(textoDinamico);  
  
        /** 
         * O Ultimo item adicionado no conteiner deve ser o componente que comtem 
         * a imagem do Splah 
         */  
        this.getContentPane().add(lblImagem);  
        this.pack();  
  
    }  
  
    public static void main(String args[])  
    {  
        final Splash splash=new Splash();  
  
        final javax.swing.JProgressBar probar = new javax.swing.JProgressBar();  
        probar.setString("113%");  
        probar.setStringPainted(true);  
        Timer timer=new Timer(50, new ActionListener()  
        {  
            public void actionPerformed(ActionEvent arg0)  
            {  
                probar.setMinimum(0);  
                probar.setMaximum(100);  
                probar.setValue(probar.getValue()+1);  
                textoDinamico.setText("Inicializando Dobba "+String.valueOf(probar.getValue())+"%");  
                if(probar.getValue()==100)  
                {  
                    splash.dispose();  
                    
                }  
            }  
  
        });  
        splash.add(probar);  
        splash.setVisible(true);  
        timer.start();  
    }  
}  