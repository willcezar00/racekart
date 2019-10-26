package org.william.racekart.converter;

import java.lang.reflect.Field;
import java.util.*;

public class EntityConverter<TYPE> implements Converter<TYPE> {

    private Map<Integer, String>  headerPosition = new HashMap<>();

    @Override
    public TYPE convert(String[] row){
       return null;
    }


    public EntityConverter(String[] headers, Class<TYPE> typeClass){
        for(int position = 0; position < headers.length; position++){
            headerPosition.put(position, headers[position]);
        }

        Set<String>  headersSet = new HashSet<>(Arrays.asList(headers));

        for(Field field : typeClass.getDeclaredFields()){
        }
    }


}
