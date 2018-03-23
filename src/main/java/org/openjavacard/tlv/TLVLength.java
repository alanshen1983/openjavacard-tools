/*
 *  openjavacard-tools: OpenJavaCard development tools
 *  Copyright (C) 2018  Ingo Albrecht (prom@berlin.ccc.de)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 *
 */

package org.openjavacard.tlv;

import org.openjavacard.util.BinUtil;

public class TLVLength {

    private static final byte LENGTH_LONG_MASK = (byte)0x80;
    private static final byte LENGTH_LONG_FLAG = (byte)0x80;
    private static final byte LENGTH_SIZE_MASK = (byte)0x7F;

    public static final boolean isLongForm(int firstByte) {
        return (firstByte & LENGTH_LONG_MASK) == LENGTH_LONG_FLAG;
    }

    public static final int longLength(int firstByte) {
        return (firstByte & ~LENGTH_SIZE_MASK);
    }

    public static final int lengthSize(int length) {
        if(length > 32767) {
            throw new IllegalArgumentException("Length " + length + " is to large");
        } if(length > 127) {
            return 3;
        } else {
            return 1;
        }
    }

    public static final byte[] lengthBytes(int length) {
        if(length > 32767) {
            throw new IllegalArgumentException("Length " + length + " is to large");
        } if(length > 127) {
            byte[] res = new byte[3];
            res[0] = 2;
            BinUtil.setShort(res, 1, (short)length);
            return res;
        } else {
            return new byte[] { (byte)length };
        }
    }

}
