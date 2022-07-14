package fxgl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.input.KeyTrigger;
import com.almasb.fxgl.input.Trigger;
import com.almasb.fxgl.input.TriggerListener;
import infra.GameCanvas;
import infra.Input;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static infra.Settings.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GoldenAxeApp extends GameApplication {

    private static final Map<KeyCode, Integer> keyMappings = new HashMap<>();

    private Input gameInput;

    private ImageView fxglCanvas;
    public WritableImage fxglImage;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(PREFERRED_SCREEN_WIDTH);
        settings.setHeight(PREFERRED_SCREEN_HEIGHT);
        settings.setManualResizeEnabled(true);
        settings.setScaleAffectedOnResize(false);

        settings.addEngineService(GoldenAxeService.class);
    }

    public static class StopEngineService extends EngineService {
        @Override
        public void onExit() {
            // TODO: stop game and dispose
            //display.dispose();
        }
    }

    @Override
    protected void initInput() {
        keyMappings.put(KeyCode.ENTER, KEY_START_2);
        keyMappings.put(KeyCode.SPACE, KEY_START_1);

        keyMappings.put(KeyCode.W, KEY_PLAYER_1_UP);
        keyMappings.put(KeyCode.S, KEY_PLAYER_1_DOWN);
        keyMappings.put(KeyCode.A, KEY_PLAYER_1_LEFT);
        keyMappings.put(KeyCode.D, KEY_PLAYER_1_RIGHT);

        keyMappings.put(KeyCode.J, KEY_PLAYER_1_ATTACK);
        keyMappings.put(KeyCode.K, KEY_PLAYER_1_JUMP);
        keyMappings.put(KeyCode.L, KEY_PLAYER_1_MAGIC);

        gameInput = new Input();

        getInput().addTriggerListener(new TriggerListener() {
            @Override
            protected void onAction(Trigger trigger) {

            }

            @Override
            protected void onActionBegin(Trigger trigger) {
                if (trigger.isKey()) {
                    var key = ((KeyTrigger) trigger).getKey();

                    if (keyMappings.containsKey(key)) {
                        Input.KEYS_PRESSED.add(keyMappings.get(key));
                    }
                }
            }

            @Override
            protected void onActionEnd(Trigger trigger) {
                if (trigger.isKey()) {
                    var key = ((KeyTrigger) trigger).getKey();

                    if (keyMappings.containsKey(key)) {
                        Input.KEYS_PRESSED.remove(keyMappings.get(key));
                        Input.KEYS_PRESSED_CONSUMED.remove(keyMappings.get(key));
                    }
                }
            }
        });
    }

    @Override
    protected void initGame() {
        SwingUtilities.invokeLater(() -> {
            runOnce(() -> {
                fxglImage = new WritableImage(800, 600);
                fxglCanvas = new ImageView(fxglImage);

                addUINode(fxglCanvas);
            }, Duration.seconds(1));
        });
    }

//    public static class Display extends JFrame {
//
//        private final GameCanvas gameCanvas;
//
//        public Display(GameCanvas gameCanvas) {
//            gameCanvas.setBackground(Color.BLACK);
//            this.gameCanvas = gameCanvas;
//        }
//
//        public void start() {
//            getContentPane().add(gameCanvas);
//            pack();
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            //gameCanvas.start();
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
