package main_server;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Andrej
 */
public class AppStyles {

    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());

            setButtonStyle();
            setTextFieldStyle();
            setComboBoxStyle();
            setTableStyle();
            setModernFont();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

   
    public static void setModernFont() {
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 12));
        UIManager.put("Button.font", new Font("Roboto", Font.BOLD, 12));
        UIManager.put("Label.font", new Font("Roboto", Font.PLAIN, 12));
        UIManager.put("TextField.font", new Font("Roboto", Font.PLAIN, 12));
        UIManager.put("TextArea.font", new Font("Roboto", Font.PLAIN, 12));
    }

    private static void setButtonStyle() {

        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Button.arc", 15);  
        UIManager.put("Button.shadow", new Color(50, 50, 50, 50)); 
        UIManager.put("Button.select", new Color(135, 206, 250));
        UIManager.put("Button.rollover", true);
    }

    private static void setTextFieldStyle() {
        UIManager.put("TextField.background", new Color(255, 255, 255, 200)); 
        UIManager.put("TextField.foreground", Color.BLACK);  
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(new Color(200, 200, 200)));  

    }

    private static void setComboBoxStyle() {
        UIManager.put("ComboBox.background", new Color(240, 255, 240, 200)); 
        UIManager.put("ComboBox.foreground", Color.BLACK);  
        UIManager.put("ComboBox.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(new Color(200, 200, 200)));
    }

    private static void setTableStyle() {
        UIManager.put("Table.background", new Color(255, 255, 255)); 
        UIManager.put("Table.gridColor", new Color(200, 200, 200));  
        UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 12));
        UIManager.put("Table.selectionBackground", new Color(144, 238, 144));  
        UIManager.put("Table.selectionForeground", Color.BLACK);  
    }


}
