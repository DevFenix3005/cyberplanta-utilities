package com.rebirth.cyberplanta.utilities.mappers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.rebirth.cyberplanta.commons.domain.entities.EnvironmentalMeasurement;
import com.rebirth.cyberplanta.commons.mappers.wrappers.EnvironmentalConversionWrapper;
import com.rebirth.cyberplanta.utilities.avro.AvroUtilsComponent;
import com.rebirth.cyberplanta.utilities.datetime.DateTimeUtilsComponent;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { AvroUtilsComponent.class, DateTimeUtilsComponent.class },
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface EnvironmentalMeasureMapper {

    @Mapping(target = "environmentalMeasurementAvroDTO", source = "environmentConsumerRecord")
    EnvironmentalConversionWrapper.Target kafkaConsumerRecordToAvroDto(EnvironmentalConversionWrapper.Source source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "temperature", source = "target.temperature")
    @Mapping(target = "temperatureUnit", source = "target.temperatureUnit")
    @Mapping(target = "humidity", source = "target.humidity")
    @Mapping(target = "measurementDateTaken", source = "target.timestamp")
    EnvironmentalMeasurement avroDtoWrappedToMongoDocument(EnvironmentalConversionWrapper.Target target, String kafkaKey);

    default EnvironmentalMeasurement kafkaConsumerRecordToMongoDocument(ConsumerRecord<String, byte[]> environmentConsumerRecord) {
        String key = environmentConsumerRecord.key();
        EnvironmentalConversionWrapper.Source source = new EnvironmentalConversionWrapper.Source(environmentConsumerRecord);
        EnvironmentalConversionWrapper.Target target = this.kafkaConsumerRecordToAvroDto(source);
        return this.avroDtoWrappedToMongoDocument(target, key);
    }

}
