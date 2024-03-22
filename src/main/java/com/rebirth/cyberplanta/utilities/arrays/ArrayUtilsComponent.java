package com.rebirth.cyberplanta.utilities.arrays;

import org.springframework.stereotype.Component;

@Component
public class ArrayUtilsComponent {

    public byte[] removeBytesIdsFromAvroRawData(byte[] source) {
        return ByteArrayUtils.removeBytesIdsFromAvroRawData(source);
    }

}
