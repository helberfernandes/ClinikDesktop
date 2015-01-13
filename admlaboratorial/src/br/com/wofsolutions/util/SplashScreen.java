
/*
 * (C) 2004 - Geotechnical Software Services
 * 
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program; if not, write to the Free 
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, 
 * MA  02111-1307, USA.
 */
package br.com.wofsolutions.util;



import java.net.URL;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JWindow;

import br.com.wofsolutions.gui.FrmLogin;



/**
 * Class representing an application splash screen.
 * <p>
 * Typical usage:
 * <pre>
 *   SplashScreen splashScreen = new SplashScreen ("/com/company/splash.jpg");
 *   splashScreen.open (3000);
 * </pre>
 * 
 * @author <a href="mailto:info@geosoft.no">GeoSoft</a>
 */   
public class SplashScreen extends JWindow
{
  private Image  image_;
  private int    x_, y_, width_, height_;
  private JProgressBar prbProgresso = null;
	 private int step = 0; 
	 FrmLogin frmLogin = new FrmLogin();


  /**
   * Create a new splash screen object of the specified image.
   * The image file is located and referred to through the deployment, not
   * the local file system; A typical value might be "/com/company/splash.jpg".
   * 
   * @param imageFileName  Name of image file resource to act as splash screen.
   */
  public SplashScreen (String imageFileName)
  {
    super (new Frame());

    try {
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      
      URL imageUrl = getClass().getResource (imageFileName);
      image_ = toolkit.getImage (imageUrl);
     getPrbProgresso();
      
      /** 
       * Setando parametros da variavel jprbProgressoSistema 
       */  
      prbProgresso.setForeground(Color.GREEN);  
      prbProgresso.setPreferredSize(new java.awt.Dimension(8, 5));  
      prbProgresso.setBounds(100, 500, 10, 12);  
      prbProgresso.setBorderPainted(false);  
    //  prbProgresso.setIndeterminate(true);  
      /** 
       * Adicionando o jprbProgressoSistema a classe SplashJprbProgresso 
       */  
      getContentPane().add(prbProgresso);  
      
      MediaTracker mediaTracker = new MediaTracker (this);
     
      mediaTracker.addImage (image_, 0);
      mediaTracker.waitForID (0);

    
      
      width_  = image_.getWidth (this);
      height_ = image_.getHeight (this);

      Dimension screenSize = toolkit.getScreenSize();
      
      x_ = (screenSize.width  - width_)  / 2;
      y_ = (screenSize.height - height_) / 2;
    }
    catch (Exception exception) {
      exception.printStackTrace();
      image_ = null;
    }
  }

  

  /**
   * Open the splash screen and keep it open for the specified duration
   * or until close() is called explicitly.
   */
  public void open (int nMilliseconds)
  {
    if (image_ == null) return;
    
    Timer timer = new Timer (Integer.MAX_VALUE, new ActionListener() {
     
        public void actionPerformed (ActionEvent event) {
        //  ((Timer) event.getSource()).stop();
          step++; 
          prbProgresso.setValue(step);  
         
         
          
          close();
        };
      });
    
    timer.setInitialDelay (nMilliseconds);
    timer.start();

    setBounds (x_, y_, width_, height_);
    setVisible (true);
  }



  /**
   * Close the splash screen.
   */
  public void close()
  {
	  if (step == 100) {  
    setVisible (false);
    dispose();
    frmLogin.setVisible(true);
	  }  
  }
  
  private JProgressBar getPrbProgresso() {  
      if (prbProgresso == null) {  
          prbProgresso = new JProgressBar();  
      }  
      return prbProgresso;  
  }

  
  /**
   * Paint the splash screen window.
   * 
   * @param graphics  The graphics instance.
   */
  public void paint (Graphics graphics) 
  {  
    if (image_ == null) return;
    graphics.drawImage (image_, 0, 0, width_, height_, this);
  }
}

