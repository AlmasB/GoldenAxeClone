package fxgl;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.core.concurrent.Async;
import com.almasb.fxgl.dsl.FXGL;
import infra.GoldenAxeGame;
import infra.Offscreen;
import infra.Settings;
import javafx.embed.swing.SwingFXUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static infra.Settings.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GoldenAxeService extends EngineService {

    private BufferedImage buffer = new BufferedImage(PREFERRED_SCREEN_WIDTH, PREFERRED_SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private Offscreen offscreen = new Offscreen(CANVAS_WIDTH, CANVAS_HEIGHT);

    private GoldenAxeGame game = new GoldenAxeGame();

    @Override
    public void onGameReady(PropertyMap vars) {
        game.start();
    }

    @Override
    public void onGameUpdate(double tpf) {
        game.update((long) (tpf * 1_000_000_000L));
        game.fixedUpdate();

        Graphics2D g = (Graphics2D) buffer.getGraphics();
        //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        game.draw(offscreen.getG2d());

        g.drawImage(offscreen.getImage(), 0, 0, PREFERRED_SCREEN_WIDTH, PREFERRED_SCREEN_HEIGHT, null);

//        if (keepAspectRatio) {
//            g.clearRect(0, 0, getWidth(), getHeight());
//            g.drawImage(offscreen.getImage()
//                    , sizeWithAspectRatio.x, sizeWithAspectRatio.y
//                    , sizeWithAspectRatio.width, sizeWithAspectRatio.height
//                    , null);
//        }
//        else {
//            g.drawImage(offscreen.getImage(), 0, 0, getWidth(), getHeight(), null);
//        }

        Async.INSTANCE.startAsyncFX(() -> {
            SwingFXUtils.toFXImage(buffer, FXGL.<GoldenAxeApp>getAppCast().fxglImage);
        }).await();

        g.dispose();
    }

//    private class MainLoop implements Runnable {
//
//
//
//        @Override
//        public void run() {
//            long currentTime = System.nanoTime();
//            long lastTime = currentTime;
//            long delta;
//            long unprocessedTime = 0;
//            while (running) {
//                currentTime = System.nanoTime();
//                delta = currentTime - lastTime;
//                unprocessedTime += delta;
//                lastTime = currentTime;
//                goldenAxeGame.update(delta);
//                while (unprocessedTime >= Settings.TIMER_PER_FRAME) {
//                    unprocessedTime -= Settings.TIMER_PER_FRAME;
//                    goldenAxeGame.fixedUpdate();
//                }
//
//                //Graphics2D g = (Graphics2D) bs.getDrawGraphics();
//
//                bs.show();
//            }
//        }
//    }
}
