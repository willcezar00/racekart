package org.william.racekart.repositories;

import org.william.racekart.util.EntityConverter;
import org.william.racekart.util.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {


    private static final String TAP_SEPARATOR = "\t";
    /**
     * read file considering separated words with tabs (\t)
     * @param path path to  input file
     */
    @Override
    public <TYPE> List<TYPE> read(String path,  Class<TYPE> typeClass) {
        try{
            List<TYPE> dtoList = new ArrayList<>();

            BufferedReader buferReader = new BufferedReader(new FileReader(path));
            List<String> words = new ArrayList<>();
            String lineFetched = null;
            String[] wordsArray;

            Converter<TYPE> inputConverter = new EntityConverter<TYPE>(buferReader.readLine().split(TAP_SEPARATOR), typeClass);

            while((lineFetched = buferReader.readLine()) != null){
                    wordsArray = lineFetched.split(TAP_SEPARATOR);
                    dtoList.add(inputConverter.convert(wordsArray));
            }

            buferReader.close();

            return dtoList;

        }catch(Exception e){
            e.printStackTrace();
             return null;
        }
    }

    @Override
    public void write() {

    }
}