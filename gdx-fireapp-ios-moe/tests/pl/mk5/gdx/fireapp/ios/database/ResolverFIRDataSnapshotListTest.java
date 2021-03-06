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

package pl.mk5.gdx.fireapp.ios.database;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.moe.natj.general.NatJ;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.List;

import apple.foundation.NSArray;
import apple.foundation.NSMutableArray;
import apple.foundation.NSMutableDictionary;
import bindings.google.firebasedatabase.FIRDataSnapshot;
import pl.mk5.gdx.fireapp.database.OrderByClause;
import pl.mk5.gdx.fireapp.ios.GdxIOSAppTest;

@PrepareForTest({NatJ.class, FIRDataSnapshot.class, NSMutableArray.class, NSMutableDictionary.class})
public class ResolverFIRDataSnapshotListTest extends GdxIOSAppTest {

    @Override
    public void setup() {
        super.setup();
        PowerMockito.mockStatic(FIRDataSnapshot.class);
        PowerMockito.mockStatic(NSMutableArray.class);
        PowerMockito.mockStatic(NSMutableDictionary.class);
    }

    @Test
    public void resolve() {
    }

    @Test
    public void shouldResolveOrderBy() {
        // Given
        OrderByClause orderByClause = new OrderByClause();
        Class dataType = List.class;
        FIRDataSnapshot firDataSnapshot = Mockito.mock(FIRDataSnapshot.class);
        Mockito.when(firDataSnapshot.childrenCount()).thenReturn(2L);
        Mockito.when(firDataSnapshot.value()).thenReturn(Mockito.mock(NSMutableArray.class));

        // When
        boolean shouldResolve = ResolverFIRDataSnapshotList.shouldResolveList(firDataSnapshot);

        // Then
        Assert.assertTrue(shouldResolve);
    }

    @Test
    public void shouldResolveOrderBy2() {
        // Given
        FIRDataSnapshot firDataSnapshot = Mockito.mock(FIRDataSnapshot.class);
        Mockito.when(firDataSnapshot.childrenCount()).thenReturn(2L);
        Mockito.when(firDataSnapshot.value()).thenReturn(Mockito.mock(NSMutableDictionary.class));

        // When
        boolean shouldResolve = ResolverFIRDataSnapshotList.shouldResolveList(firDataSnapshot);

        // Then
        Assert.assertTrue(shouldResolve);
    }

    @Test
    public void shouldResolveOrderBy_fail3() {
        // Given
        OrderByClause orderByClause = new OrderByClause();
        Class dataType = List.class;
        FIRDataSnapshot firDataSnapshot = Mockito.mock(FIRDataSnapshot.class);
        Mockito.when(firDataSnapshot.childrenCount()).thenReturn(0L);
        Mockito.when(firDataSnapshot.value()).thenReturn(Mockito.mock(NSArray.class));

        // When
        boolean shouldResolve = ResolverFIRDataSnapshotList.shouldResolveList(firDataSnapshot);

        // Then
        Assert.assertFalse(shouldResolve);
    }
}