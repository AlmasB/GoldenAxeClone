package fxgl.ui;

import infra.Audio;
import infra.Resource;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public final class GoView extends Parent {

    private boolean isAnimating = false;

    public GoView() {
        var image = Resource.getImageFX("go");

        getChildren().add(new ImageView(image));

        setTranslateX(193);
        setTranslateY(42);
        setOpacity(0);
    }

    public void blink(int times) {
        if (isAnimating)
            return;

        isAnimating = true;

        animationBuilder()
                .onFinished(() -> {
                    setOpacity(0);
                    isAnimating = false;
                })
                .onCycleFinished(() -> Audio.playSound("go"))
                .repeat(times)
                .duration(Duration.seconds(0.3))
                .fadeIn(this)
                .buildAndPlay();
    }
}
