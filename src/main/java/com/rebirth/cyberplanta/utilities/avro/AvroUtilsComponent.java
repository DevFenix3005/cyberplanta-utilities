package com.rebirth.cyberplanta.utilities.avro;

import java.io.IOException;

import org.apache.avro.io.DatumReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rebirth.cyberplanta.commons.avro.model.EnvironmentalMeasurementAvroDTO;
import com.rebirth.cyberplanta.utilities.arrays.ArrayUtilsComponent;

@Component
public class AvroUtilsComponent {

    private final ArrayUtilsComponent arrayUtilsComponent;

    private final DatumReader<EnvironmentalMeasurementAvroDTO> environmentalMeasurementAvroDTODatumReader;

    @Autowired
    public AvroUtilsComponent(ArrayUtilsComponent arrayUtilsComponent,
            DatumReader<EnvironmentalMeasurementAvroDTO> environmentalMeasurementAvroDTODatumReader) {
        this.arrayUtilsComponent = arrayUtilsComponent;
        this.environmentalMeasurementAvroDTODatumReader = environmentalMeasurementAvroDTODatumReader;
    }

    public EnvironmentalMeasurementAvroDTO consumerRecordWithByteValueToDto(
            ConsumerRecord<String, byte[]> consumerRecord
    ) throws IOException {
        return ConsumerRecordToDto.consumerRecordWithByteValueToDto(
                arrayUtilsComponent, environmentalMeasurementAvroDTODatumReader, consumerRecord
        );
    }

}
