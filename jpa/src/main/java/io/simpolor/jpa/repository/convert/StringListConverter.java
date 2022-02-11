package io.simpolor.jpa.repository.convert;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {

        if(CollectionUtils.isEmpty(strings)){
            return Strings.EMPTY;
        }

        return String.join(SPLIT_CHAR, strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {

        if(StringUtils.isEmpty(string)){
            return Collections.EMPTY_LIST;
        }

        return Arrays.asList(string.split(SPLIT_CHAR));
    }
}

