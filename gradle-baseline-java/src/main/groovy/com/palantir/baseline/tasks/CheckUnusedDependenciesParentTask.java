/*
 * (c) Copyright 2020 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.baseline.tasks;

import com.palantir.baseline.plugins.BaselineExactDependencies;
import java.util.Collections;
import java.util.Set;
import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.SetProperty;
import org.gradle.api.tasks.Internal;

public class CheckUnusedDependenciesParentTask extends DefaultTask {
    private final SetProperty<String> ignore;

    public CheckUnusedDependenciesParentTask() {
        ignore = getProject().getObjects().setProperty(String.class);
        ignore.set(Collections.emptySet());
    }

    /** Ignores these coordinates for all source sets. */
    public final void ignore(Provider<Set<String>> value) {
        ignore.addAll(value);
    }

    /** Ignores this coordinate for all source sets. */
    public final void ignore(String group, String name) {
        ignore.add(BaselineExactDependencies.ignoreCoordinate(group, name));
    }

    @Internal
    public final Provider<Set<String>> getIgnore() {
        return ignore;
    }
}
