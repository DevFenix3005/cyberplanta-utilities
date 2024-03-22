package com.rebirth.cyberplanta.utilities.arrays;

import java.util.Arrays;

class ByteArrayUtils {

    private static final int ID_SIZE = 5;

    private ByteArrayUtils() {
    }

    static byte[] removeBytesIdsFromAvroRawData(byte[] source) {
        return Arrays.copyOfRange(source, ID_SIZE, source.length);
    }
}
