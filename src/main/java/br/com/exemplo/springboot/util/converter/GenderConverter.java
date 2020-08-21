package br.com.exemplo.springboot.util.converter;

import br.com.exemplo.springboot.enums.Gender;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Short> {

    @Override
    public Short convertToDatabaseColumn(Gender g) {
        if (g == null)
            return null;
        return g.getId();
    }

    @Override
    public Gender convertToEntityAttribute(Short dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 0:
                return Gender.MALE;

            case 1:
                return Gender.FEMALE;
            default:
                throw new IllegalArgumentException("Gender id: " + dbData + " not supported.");
        }
    }

}
