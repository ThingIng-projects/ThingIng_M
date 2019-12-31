package com.thinging.project.persistance;

import com.thinging.project.eventManagement.request.EventRequest;
import com.thinging.project.eventManagement.type.ServiceType;
import com.thinging.project.events.model.EventDataValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EventDataConverter implements AttributeConverter<EventDataValue,String> {

    private static final char valueSeperator = '>';

    @Override
    public String convertToDatabaseColumn(EventDataValue eventDataValue) {

        String value = ServiceType.eventDataAsString(eventDataValue.getEventData(),eventDataValue.getServiceType());
        if(value == null) throw new IllegalArgumentException("Invalid type");
        String valueToSave = value + valueSeperator + eventDataValue.getServiceType();

        return valueToSave;
    }

    @Override
    public EventDataValue convertToEntityAttribute(String s) {

        String type = s.substring( s.lastIndexOf(valueSeperator) + 1 );
        String value = s.substring( 0, s.lastIndexOf(valueSeperator) );
        EventRequest savedValue = ServiceType.stringAsEventData(value,type);
        if(savedValue == null) throw  new IllegalArgumentException("Invalid value");

        EventDataValue eventDataValue = new EventDataValue();
        eventDataValue.setServiceType(Enum.valueOf(ServiceType.class,type));
        eventDataValue.setEventData(savedValue);

        return eventDataValue;
    }


}
