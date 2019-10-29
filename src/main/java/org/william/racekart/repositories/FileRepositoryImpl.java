package org.william.racekart.repositories;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.william.racekart.converter.Converter;
import org.william.racekart.converter.EntityConverter;
import org.william.racekart.converter.HeadersException;
import org.william.racekart.domain.PilotRaceLog;
import org.william.racekart.domain.RaceResult;
import org.william.racekart.util.TimeConverterUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileRepositoryImpl implements FileRepository {

    private static FileRepositoryImpl fileRepository = null;
    private static final String TAP_SEPARATOR = "\t";
    private static final String SEPARATOR = "\\s\\s+";
    private static final String LINE_BREAKER = "\n";
    private static final List<String> OUTPUT_HEADERS = Collections.unmodifiableList(Arrays.asList("Posição Chegada", "Código Piloto", "Nome Piloto", "Qtde Voltas Completadas",
            "Tempo Total de Prova", "Melhor Volta", "Velocidade Média", "Tempo Após o Vencedor"));


    /**
     * read file considering separated words with tabs (\t)
     *
     * @param path path to  input file
     */
    @Override
    public <TYPE> List<TYPE> read(String path, Class<TYPE> typeClass) {
        try {
            List<TYPE> dtoList = new ArrayList<>();

            BufferedReader bufferedReader = new BufferedReader(getReader(path));
            List<String> words = new ArrayList<>();
            String lineFetched = null;
            String[] wordsArray;

            Converter<TYPE> inputConverter = new EntityConverter<TYPE>(bufferedReader.readLine().trim().split(SEPARATOR), typeClass);
            checkHeaders(inputConverter);
            while ((lineFetched = bufferedReader.readLine()) != null) {
                wordsArray = lineFetched.trim().split(SEPARATOR);
                dtoList.add(inputConverter.convert(wordsArray));
            }

            bufferedReader.close();

            return dtoList;
        } catch (IOException e) {
            throw new InputException(path);
        }
    }

    private <TYPE> void checkHeaders(Converter<TYPE> inputConverter) {
        Set<String> ignoreHeaders = inputConverter.getIgnoreHeadersFromClass();
        if (!ignoreHeaders.isEmpty())
            throw new HeadersException(new ArrayList<>(ignoreHeaders).toArray(new String[ignoreHeaders.size()]));
    }

    private Reader getReader(String path) {
        try {
            InputStream inputStream = path == null ? System.in : new FileInputStream(path);
            return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        } catch (java.io.FileNotFoundException e) {
            throw new FileNotFoundException(path);
        }
    }

    private Writer getWriter(String path) {
        try {
            OutputStream outputStream = path == null ? System.out : new FileOutputStream(path);
            return new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        } catch (java.io.FileNotFoundException e) {
            throw new FileNotFoundException(path);
        }
    }

    @Override
    public void write(String path, RaceResult raceResult) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(getWriter(path));
            bufferedWriter.write(String.join(TAP_SEPARATOR, OUTPUT_HEADERS));
            bufferedWriter.write(LINE_BREAKER);
            for (PilotRaceLog pilotRaceLog : raceResult.getPilotRaceLogs()) {
                List<String> row = new ArrayList<>(OUTPUT_HEADERS.size());
                row.add(pilotRaceLog.getPosition().toString());
                row.add(pilotRaceLog.getPilot().getCode());
                row.add(pilotRaceLog.getPilot().getName());
                row.add(pilotRaceLog.getCompletedLaps().toString());
                row.add(TimeConverterUtil.getTimeByLong(pilotRaceLog.getRaceTime()));
                row.add(TimeConverterUtil.getTimeByLong(pilotRaceLog.getBestLap()));
                row.add(pilotRaceLog.getAverageSpeed().toString());
                row.add(TimeConverterUtil.getTimeByLong(pilotRaceLog.getWinnerDifference()));
                bufferedWriter.write(String.join(TAP_SEPARATOR, row));
                bufferedWriter.write(LINE_BREAKER);
            }
            bufferedWriter.write(MessageFormat.format("Melhor Volta: {0} - {1}  {2} \n", raceResult.getBestLap().getPilot().getCode(), raceResult.getBestLap().getPilot().getName(),
                    TimeConverterUtil.getTimeByLong(raceResult.getBestLap().getLapDuration())));
            bufferedWriter.close();

        } catch (IOException e) {
            throw new OutputException(path);
        }
    }

    public static FileRepositoryImpl getInstance() {
        if (fileRepository == null) {
            fileRepository = new FileRepositoryImpl();
        }
        return fileRepository;
    }
}
