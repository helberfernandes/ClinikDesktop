

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import br.com.wofsolutions.gui.DeskTop;
import br.com.wofsolutions.gui.FrmLogin;
import br.com.wofsolutions.util.GUIUtil;
import br.com.wofsolutions.util.HibernateUtil;

class ProgressSplashScreen extends JWindow {

   private static final long serialVersionUID = 935801891530361293L;
   private static final Color COLOR_PROGRESS_BACKGROUND = new Color(160, 182, 192);
   private static final Color COLOR_PROGRESS_FORGROUND = new Color(215, 224, 227);
   private final BufferedImage splashScreenImage;
   private final JProgressBar progressbar;
   private final ExecutorService autoProgressExecutor = Executors.newFixedThreadPool(1);
   private FrmLogin frmLogin = new FrmLogin();




   public ProgressSplashScreen(final InputStream theResourceAsStream) {
     this(theResourceAsStream, -1, -1);
   }

   public ProgressSplashScreen(final InputStream theResourceAsStream,
                             final int theMin, final int theMax) {
     super();
  
     try {
       splashScreenImage = ImageIO.read(theResourceAsStream);
     } catch (final IOException e) {
       throw new IllegalArgumentException(String.format("Can't load splashscreen"), e);
     }
     final JPanel contentPanel = new JPanel(new BorderLayout());
    // contentPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
     if (theMin != -1 && theMax != -1) {
       progressbar = new JProgressBar(SwingConstants.HORIZONTAL, theMin, theMax);
     } else {
       progressbar = new JProgressBar(SwingConstants.HORIZONTAL);
       progressbar.setIndeterminate(true);
     }
     //progressbar.setBackground(COLOR_PROGRESS_BACKGROUND);
    // progressbar.setForeground(COLOR_PROGRESS_FORGROUND);
     progressbar.setStringPainted(true);
     final JLabel label = new JLabel(new ImageIcon(splashScreenImage));
     contentPanel.add(label, BorderLayout.CENTER);
     contentPanel.add(progressbar, BorderLayout.SOUTH);
     add(contentPanel);
     pack();
     setLocationRelativeTo(null);
     setAlwaysOnTop(true);
     
     GUIUtil.setLookAndFeel(this);
     
     
     
   }
   public void close() {
     setVisible(false);
    autoProgressExecutor.shutdownNow();
    dispose();
   }

   public void showProgress(final int theValue) {
     setVisible(true);
     progressbar.setValue(theValue);
     if (progressbar.getValue() == 100) {
       setVisible(false);
     }
   }

   public void showProgress(final int theValueTo, final int theEstimatedTimeInSeconds) {
     showProgress(progressbar.getValue(), theValueTo, theEstimatedTimeInSeconds);
   }
   


   public void showProgress(final int theValueFrom, final int theValueTo,
                                 final int theEstimatedTimeInSeconds) {
     setVisible(true);
     autoProgressExecutor.execute(new Runnable() {
         public void run() {
           int numberOfSteps = theValueTo - theValueFrom;
           long timeToWait = TimeUnit.SECONDS.toMillis(theEstimatedTimeInSeconds)
                                    / numberOfSteps;
           for (int i = theValueFrom; i <= theValueTo; i++) {
               progressbar.setValue(i);
               try {
                 TimeUnit.MILLISECONDS.sleep(timeToWait);
               } catch (final InterruptedException e) {
           // ignore
         }
       }
           //System.out.println("Carregando a janela ="+progressbar.getValue());
           if (progressbar.getValue() == 25) {
        	
        	  // DeskTop deskTop = new DeskTop();
        	  
           }
           
           
           
       if (progressbar.getValue() == 100) {
    	 setVisible(false);
         frmLogin.setVisible(true);
       
       }
     }
   });
 }
}