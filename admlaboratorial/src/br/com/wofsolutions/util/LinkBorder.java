package br.com.wofsolutions.util;

import java.awt.Component;  
import java.awt.Graphics;  
import javax.swing.border.AbstractBorder;  
  
/** 
* Constroi uma borda para uso em links. 
* @author Eder Peixoto 
* @version 1.01 - 20/04/2009. 
*/  
public class LinkBorder extends AbstractBorder{  
    /** 
     * Pinta uma borda apenas por baixo do componente, na mesma <code>Color</code> do foreground 
     *do <code>component</code>. 
     * @param c the component for which this border is being painted 
     * @param g the paint graphics 
     * @param x the x position of the painted border 
     * @param y the y position of the painted border 
     * @param width the width of the painted border 
     * @param height the height of the painted border 
     */  
    @Override  
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){  
        g.setColor( c.getForeground() );  
        g.drawLine(0, height - 1, width, height - 1);       //Border Bottom  
    }//Fim do metodo.  
}//Fim da classe.  
