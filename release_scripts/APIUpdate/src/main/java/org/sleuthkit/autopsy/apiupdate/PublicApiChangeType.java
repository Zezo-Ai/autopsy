/*
 * Autopsy Forensic Browser
 *
 * Copyright 2023 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
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
package org.sleuthkit.autopsy.apiupdate;

import java.util.Comparator;
import org.apache.commons.lang3.ObjectUtils;

/**
 * A public API change type (no change, compatible change, incompatible change).
 */
public enum PublicApiChangeType implements Comparator<PublicApiChangeType> {
    NONE(0), INTERNAL_CHANGE(1), COMPATIBLE_CHANGE(2), INCOMPATIBLE_CHANGE(3);

    private final int level;

    /**
     * COnstructor.
     *
     * @param level The level for the api change (none is min, incompatible is
     * max).
     */
    PublicApiChangeType(int level) {
        this.level = level;
    }

    /**
     *
     * @return The level for the api change (none is min, incompatible is max).
     */
    public int getLevel() {
        return level;
    }

    @Override
    public int compare(PublicApiChangeType o1, PublicApiChangeType o2) {
        o1 = ObjectUtils.defaultIfNull(o1, PublicApiChangeType.NONE);
        o2 = ObjectUtils.defaultIfNull(o2, PublicApiChangeType.NONE);
        return Integer.compare(o1.getLevel(), o2.getLevel());
    }
}
