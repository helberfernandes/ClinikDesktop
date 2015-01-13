package br.com.wofsolutions.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyCellRenderer extends DefaultTableCellRenderer {
	private Color whiteColor = new Color(254, 254, 254);  
    private Color alternateColor = new Color(235, 235, 235);  
    private Color selectedColor = new Color(61, 128, 223);  

    @Override  
    public Component getTableCellRendererComponent(JTable table,  
            Object value, boolean selected, boolean focused, int row,  
            int column) {  

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);  

        Color bg;  
        if (!selected)  
            bg = (row % 2 == 0 ? alternateColor : whiteColor);  
        else  
            bg = selectedColor;  
        
        setBackground(bg);
      
        setForeground(selected ? Color.white : Color.black);  
        /* 
        if (value instanceof ImageIcon) { 
            setIcon((ImageIcon) value); 
            setText(""); 
        } else 
            setIcon(null); 
       */  
        return this;  
    }  
}
