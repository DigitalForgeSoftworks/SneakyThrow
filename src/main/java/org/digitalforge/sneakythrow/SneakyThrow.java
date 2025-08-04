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

public class SneakyThrow {

    private static final boolean UNWRAP = Boolean.getBoolean("digitalforge.sneakythrow.unwrap");

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> RuntimeException sneak(Throwable throwable) throws T {

        Throwable t = throwable;

        if(UNWRAP) {
            while ((t instanceof ExecutionException) || (t instanceof CompletionException) || (t instanceof InvocationTargetException)) {
                if (t.getCause() == null) {
                    break;
                }
                t = t.getCause();
            }
        }

        throw (T)t;

    }

}
