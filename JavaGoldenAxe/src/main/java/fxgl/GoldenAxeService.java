package fxgl;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import infra.Audio;
import infra.GoldenAxeGame;
import infra.Offscreen;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fxgl.EntityType.GAME_CANVAS;
import static infra.Settings.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GoldenAxeService extends EngineService {

    private BufferedImage buffer = new BufferedImage(PREFERRED_SCREEN_WIDTH, PREFERRED_SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private Offscreen offscreen = new Offscreen(CANVAS_WIDTH, CANVAS_HEIGHT);

    private GoldenAxeGame game = new GoldenAxeGame();

    private WritableImage fxglCanvas;

    @Override
    public void onGameReady(PropertyMap vars) {
        fxglCanvas = FXGL.getGameWorld()
                .getSingleton(GAME_CANVAS)
                .getObject("canvas");

        game.start();
    }

    @Override
    public void onGameUpdate(double tpf) {
        game.update((long) (tpf * 1_000_000_000L));
        game.fixedUpdate();

        Graphics2D g = (Graphics2D) buffer.getGraphics();

        game.draw(offscreen.getG2d());

        g.drawImage(offscreen.getImage(), 0, 0, PREFERRED_SCREEN_WIDTH, PREFERRED_SCREEN_HEIGHT, null);

        // draw to FXGL canvas
        SwingFXUtils.toFXImage(buffer, fxglCanvas);

        g.dispose();
    }

    @Override
    public void onExit() {
        Audio.close();
    }
}
