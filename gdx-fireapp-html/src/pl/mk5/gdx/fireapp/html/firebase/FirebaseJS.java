/*
 * Copyright 2017 mk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.mk5.gdx.fireapp.html.firebase;

class FirebaseJS {

    private FirebaseJS() {
        //
    }

    static native void initializeFirebase(String initializationScript) /*-{
        console.log("GdxFireapp: initialization script...");
        eval(initializationScript);
        var interval = $wnd.setInterval(function(){
          if( $wnd.firebase.apps.length > 0 ) {
            console.log("GdxFireapp: app initialized");
            @pl.mk5.gdx.fireapp.html.firebase.FirebaseJS::setFirebaseScriptIsLoaded()();
            $wnd.clearInterval(interval);
          }
        }, 100);
    }-*/;

    static native boolean isFirebaseLoaded() /*-{
        return (typeof $wnd.firebase != 'undefined');
    }-*/;

    static void setFirebaseScriptIsLoaded() {
        FirebaseScriptInformant.setIsLoaded(true);
    }
}
