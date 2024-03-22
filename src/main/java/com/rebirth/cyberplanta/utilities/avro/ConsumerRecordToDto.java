package com.rebirth.cyberplanta.utilities.avro;

import java.io.IOException;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.rebirth.cyberplanta.utilities.arrays.ArrayUtilsComponent;

class ConsumerRecordToDto {

    private ConsumerRecordToDto() {
        throw new UnsupportedOperationException("U can't create an instance of this class");
    }

    static <T> T consumerRecordWithByteValueToDto(
            ArrayUtilsComponent arrayUtilsComponent,
            DatumReader<T> dtoDatumReader,
            ConsumerRecord<String, byte[]> consumerRecord
    ) throws IOException {
        byte[] data = consumerRecord.value();
        // To remove the schema Id | The first 5 bytes defined the schema Id
        byte[] fixedData = arrayUtilsComponent.removeBytesIdsFromAvroRawData(data);
        Decoder decoder = DecoderFactory.get().binaryDecoder(fixedData, null);
        return dtoDatumReader.read(null, decoder);
    }

}
