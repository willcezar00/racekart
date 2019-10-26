package org.william.racekart.util;

public class EntityConverter<TYPE> implements Converter<TYPE> {

    @Override
    public TYPE convert(String[] line){
       return null;
    }


    public EntityConverter(String[] header, Class<TYPE> typeClass){

    }


}
