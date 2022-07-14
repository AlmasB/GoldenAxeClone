package fxgl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.input.KeyTrigger;
import com.almasb.fxgl.input.Trigger;
import com.almasb.fxgl.input.TriggerListener;
import infra.Input;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Scale;

import java.util.HashMap;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static infra.Settings.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GoldenAxeApp extends GameApplication {

    private Group ui;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(PREFERRED_SCREEN_WIDTH);
        settings.setHeight(PREFERRED_SCREEN_HEIGHT);
        settings.setTitle("Java Golden Axe");
        settings.setManualResizeEnabled(true);

        settings.addEngineService(GoldenAxeService.class);
    }

    @Override
    protected void initInput() {
        Map<KeyCode, Integer> keyMappings = new HashMap<>();

        keyMappings.put(KeyCode.ENTER, KEY_START_2);
        keyMappings.put(KeyCode.SPACE, KEY_START_1);

        keyMappings.put(KeyCode.W, KEY_PLAYER_1_UP);
        keyMappings.put(KeyCode.S, KEY_PLAYER_1_DOWN);
        keyMappings.put(KeyCode.A, KEY_PLAYER_1_LEFT);
        keyMappings.put(KeyCode.D, KEY_PLAYER_1_RIGHT);

        keyMappings.put(KeyCode.J, KEY_PLAYER_1_ATTACK);
        keyMappings.put(KeyCode.K, KEY_PLAYER_1_JUMP);
        keyMappings.put(KeyCode.L, KEY_PLAYER_1_MAGIC);

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

        // debug

        onKeyDown(KeyCode.T, () -> UI.GO.blink(4));
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());

        spawn("gameCanvas");
    }

    @Override
    protected void initUI() {
        ui = new Group(
                UI.GO
        );
        ui.getTransforms().add(new Scale(RENDER_UPSCALE_RATIO, RENDER_UPSCALE_RATIO, 0, 0));

        addUINode(ui);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
