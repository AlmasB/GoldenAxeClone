package infra;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Display class.
 * 
 * @author Leonardo Ono (ono.leo80@gmail.com)
 */
public class Display extends JFrame {
    
    private final GameCanvas gameCanvas;
    
    public Display(GameCanvas gameCanvas) {
        gameCanvas.setBackground(Color.BLACK);
        this.gameCanvas = gameCanvas;
    }
    
    public void start() {
        getContentPane().add(gameCanvas);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameCanvas.start();
    }
}
