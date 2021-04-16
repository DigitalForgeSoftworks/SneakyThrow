/**
 * Copyright ©2021 DigitalForge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.digitalforge.sneakythrow;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

public class SneakyThrowTest {

    @Test
    public void testSneakCompletionException() {

        Assert.assertThrows(RuntimeException.class, () -> {

            RuntimeException expected = new RuntimeException("expected");
            CompletionException ex1 = new CompletionException("ex1", expected);

            throw SneakyThrow.sneak(ex1);

        });

    }

    @Test
    public void testSneakExecutionException() {

        Assert.assertThrows(RuntimeException.class, () -> {

            RuntimeException expected = new RuntimeException("expected");
            ExecutionException ex1 = new ExecutionException("ex1", expected);

            throw SneakyThrow.sneak(ex1);

        });

    }

    @Test
    public void testSneakInvocationTargetException() {

        Assert.assertThrows(RuntimeException.class, () -> {

            RuntimeException expected = new RuntimeException("expected");
            InvocationTargetException ex1 = new InvocationTargetException(expected, "ex1");

            throw SneakyThrow.sneak(ex1);

        });

    }

    @Test
    public void testSneakNestedExceptions() {

        Assert.assertThrows(RuntimeException.class, () -> {

            RuntimeException expected = new RuntimeException("expected");
            InvocationTargetException ex2 = new InvocationTargetException(expected, "ex2");
            CompletionException ex1 = new CompletionException("ex1", ex2);

            throw SneakyThrow.sneak(ex1);

        });

    }

}
