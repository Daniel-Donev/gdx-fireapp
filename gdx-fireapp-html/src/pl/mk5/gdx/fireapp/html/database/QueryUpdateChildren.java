/*
 * Copyright 2018 mk
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

package pl.mk5.gdx.fireapp.html.database;

import java.util.Map;

import pl.mk5.gdx.fireapp.database.validators.ArgumentsValidator;
import pl.mk5.gdx.fireapp.database.validators.UpdateChildrenValidator;
import pl.mk5.gdx.fireapp.promises.FuturePromise;

class QueryUpdateChildren extends GwtDatabaseQuery {

    QueryUpdateChildren(Database databaseDistribution, String databasePath) {
        super(databaseDistribution, databasePath);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void runJS() {
        if (promise == null) {
            update(databasePath, MapTransformer.mapToJSON((Map<String, Object>) arguments.get(0)));
        } else {
            updateWithPromise(databasePath, MapTransformer.mapToJSON((Map<String, Object>) arguments.get(0)), (FuturePromise) promise);
        }
    }

    @Override
    protected ArgumentsValidator createArgumentsValidator() {
        return new UpdateChildrenValidator();
    }

    static native void update(String reference, String stringValue) /*-{
        var val;
        try{
            val = JSON.parse(stringValue);
        }catch(err){
            val = stringValue;
        }
        $wnd.firebase.database().ref(reference).update(val);
    }-*/;

    static native void updateWithPromise(String reference, String stringValue, FuturePromise promise) /*-{
        var val;
        try{
            val = JSON.parse(stringValue);
        }catch(err){
            val = stringValue;
        }
        $wnd.firebase.database().ref(reference).update(val).then(function(){
            promise.@pl.mk5.gdx.fireapp.promises.FuturePromise::doComplete(Ljava/lang/Object;)(null);
        })['catch'](function(error){
             promise.@pl.mk5.gdx.fireapp.promises.FuturePromise::doFail(Ljava/lang/Throwable;)(@java.lang.Exception::new(Ljava/lang/String;)(error.message));
        });
    }-*/;
}
