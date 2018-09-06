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

package mk.gdx.firebase.html.database.queries;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.modules.junit4.rule.PowerMockRule;

import mk.gdx.firebase.database.validators.ArgumentsValidator;
import mk.gdx.firebase.database.validators.OnConnectionValidator;
import mk.gdx.firebase.html.database.Database;

public class ConnectionStatusQueryTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

    @Test
    public void runJS() {
        // If use @PrepareForTest(query...), JaCoCo can't get coverage
    }

    @Test
    public void createArgumentsValidator() {
        // Given
        ConnectionStatusQuery query = new ConnectionStatusQuery(Mockito.mock(Database.class));

        // When
        ArgumentsValidator argumentsValidator = query.createArgumentsValidator();

        // Then
        Assert.assertTrue(argumentsValidator instanceof OnConnectionValidator);
    }
}