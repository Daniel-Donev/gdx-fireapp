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

package pl.mk5.gdx.fireapp.promises;

import com.badlogic.gdx.utils.Array;

import pl.mk5.gdx.fireapp.functional.Consumer;

class ConsumerWrapper<T> implements Consumer<T> {

    private final Array<Consumer<T>> consumers = new Array<>();
    private boolean cleanAfterAccept;

    ConsumerWrapper() {
        this.cleanAfterAccept = true;
    }

    @Override
    public void accept(T t) {
        for (Consumer<T> consumer : consumers) {
            consumer.accept(t);
        }
        if (cleanAfterAccept)
            consumers.clear();
    }

    void addConsumer(Consumer<T> consumer) {
        this.consumers.add(consumer);
    }

    boolean isSet() {
        return consumers.size > 0;
    }

    Consumer<T> first() {
        return consumers.first();
    }

    public void setCleanAfterAccept(boolean cleanAfterAccept) {
        this.cleanAfterAccept = cleanAfterAccept;
    }
}
