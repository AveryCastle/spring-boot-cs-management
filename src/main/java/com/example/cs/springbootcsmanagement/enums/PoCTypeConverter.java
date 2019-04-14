package com.example.cs.springbootcsmanagement.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PoCTypeConverter implements AttributeConverter<PoCType, String> {

    @Override
    public String convertToDatabaseColumn(PoCType poCType) {
        return poCType.getDescription();
    }

    @Override
    public PoCType convertToEntityAttribute(String dbData) {
        return PoCType.ofLegacyCode(dbData);
    }
}
