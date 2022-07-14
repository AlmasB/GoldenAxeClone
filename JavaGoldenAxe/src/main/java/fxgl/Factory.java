package fxgl;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import static com.almasb.fxgl.dsl.FXGL.*;
import static infra.Settings.PREFERRED_SCREEN_HEIGHT;
import static infra.Settings.PREFERRED_SCREEN_WIDTH;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Factory implements EntityFactory {

    @Spawns("gameCanvas")
    public Entity newGameCanvas(SpawnData data) {
        var canvas = new WritableImage(PREFERRED_SCREEN_WIDTH, PREFERRED_SCREEN_HEIGHT);

        return entityBuilder(data)
                .type(EntityType.GAME_CANVAS)
                .with("canvas", canvas)
                .view(new ImageView(canvas))
                .build();
    }
}
