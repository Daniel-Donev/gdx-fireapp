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

package pl.mk5.gdx.fireapp.database.validators;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;

import pl.mk5.gdx.fireapp.distributions.DatabaseDistribution;
import pl.mk5.gdx.fireapp.functional.Function;

/**
 * Validates arguments for {@link DatabaseDistribution#transaction(Class, Function)} }
 */
public class RunTransactionValidator implements ArgumentsValidator {

    private static final String MESSAGE1 = "Database#transaction needs at least 2 arguments";
    private static final String MESSAGE2 = "The first argument should be class type";
    private static final String MESSAGE3 = "The second argument should be Function";
    private static final String NUMBER_TYPE_SHOULD_BE_LONG_OR_DOUBLE = "Numeric type should be Long or Double.";

    @Override
    public void validate(Array<Object> arguments) {
        if (arguments.size < 2)
            throw new IllegalArgumentException(MESSAGE1);
        if (!(arguments.get(0) instanceof Class))
            throw new IllegalArgumentException(MESSAGE2);
        if (!(arguments.get(1) instanceof Function))
            throw new IllegalArgumentException(MESSAGE3);
        if (ClassReflection.isAssignableFrom(Number.class, (Class) arguments.get(0))
                && arguments.get(0) != Double.class
                && arguments.get(0) != Long.class
        ) {
            throw new IllegalArgumentException(NUMBER_TYPE_SHOULD_BE_LONG_OR_DOUBLE);
        }
    }
}
