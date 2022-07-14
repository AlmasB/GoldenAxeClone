package infra;

import com.almasb.fxgl.core.concurrent.Async;
import com.almasb.fxgl.dsl.FXGL;
import fxgl.GoldenAxeApp;
import javafx.embed.swing.SwingFXUtils;

import static infra.Settings.*;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * GameCanvas class.
 * 
 * @author Leonardo Ono (ono.leo80@gmail.com)
 */
public class GameCanvas extends Canvas {
    
//    private final Dimension preferredSize = new Dimension(PREFERRED_SCREEN_WIDTH, PREFERRED_SCREEN_HEIGHT);
//
//    private Offscreen offscreen = new Offscreen(CANVAS_WIDTH, CANVAS_HEIGHT);
//    private final GoldenAxeGame goldenAxeGame;
//    private BufferStrategy bs;
//    private boolean running;
//    private Thread gameLoopThread;
//    private final Rectangle sizeWithAspectRatio = new Rectangle();
//
//    public GameCanvas(GoldenAxeGame goldenAxeGame) {
//        this.goldenAxeGame = goldenAxeGame;
//    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        return preferredSize;
//    }
//
//    public void start() {
//        updateAspectRatioDimension();
//        createBufferStrategy(2);
//        bs = getBufferStrategy();
//        offscreen = new Offscreen(CANVAS_WIDTH, CANVAS_HEIGHT);
//        goldenAxeGame.start();
//        running = true;
//        gameLoopThread = new Thread(new MainLoop());
//        gameLoopThread.setDaemon(true);
//        gameLoopThread.start();
//        addKeyListener(new Input());
//    }
//
//    public void updateAspectRatioDimension() {
//        int left = 0;
//        int width = getWidth();
//        int height = (int) (width / ASPECT_RATIO);
//        int top = (int) ((getHeight() - height) / 2);
//        if (top < 0) {
//            top = 0;
//            height = getHeight();
//            width = (int) (height * ASPECT_RATIO);
//            left = (int) ((getWidth() - width) / 2);
//        }
//        sizeWithAspectRatio.setBounds(left, top, width, height);
//    }
    

}
