package pl.mk5.gdx.fireapp.e2e.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import pl.mk5.gdx.fireapp.GdxFIRAuth;
import pl.mk5.gdx.fireapp.auth.GdxFirebaseUser;
import pl.mk5.gdx.fireapp.functional.BiConsumer;
import pl.mk5.gdx.fireapp.functional.Consumer;
import pl.mk5.gdx.fireapp.e2e.runner.E2ETest;

public class AuthCreateUserEmailPasswordTest extends E2ETest {

    public static final String USER = "test@test.com";
    public static final String PASSWORD = "secret";

    @Override
    public void action() {
        GdxFIRAuth.inst()
                .createUserWithEmailAndPassword(USER, PASSWORD.toCharArray())
                .then(new Consumer<GdxFirebaseUser>() {
                    @Override
                    public void accept(GdxFirebaseUser gdxFirebaseUser) {
                        success();
                    }
                })
                .fail(new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String s, Throwable throwable) {
                        Gdx.app.log("AuthCreateUserEmail", "create user error: " + s);
                        if( s.contains("The email address is already in use by another account")
                                || s.contains("EMAIL_EXISTS")
                                || s.contains("DEFAULT")
                        ){
                            GdxFIRAuth.inst().getCurrentUser().delete().subscribe();
                            success();
                        }
                    }
                });
    }

    @Override
    public void draw(Batch batch) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {

    }
}
