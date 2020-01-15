package pl.mk5.gdx.fireapp.e2e.tests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Timer;

import pl.mk5.gdx.fireapp.e2e.runner.E2ETest;

public class BadlogicTest extends E2ETest {
    private Texture img;

    @Override
    public void action() {
        img = new Texture("badlogic.jpg");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                success();
            }
        }, 1f);
    }

    @Override
    public void draw(Batch batch) {
        if (img == null) return;
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
