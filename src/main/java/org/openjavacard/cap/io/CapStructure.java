/*
 * openjavacard-tools: OpenJavaCard Development Tools
 * Copyright (C) 2015-2018 Ingo Albrecht, prom@berlin.ccc.de
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */

package org.openjavacard.cap.io;

import java.io.IOException;

public abstract class CapStructure {

    private CapStructure mParent;

    protected CapStructure() {
    }

    public abstract void read(CapStructureReader reader) throws IOException;

}
