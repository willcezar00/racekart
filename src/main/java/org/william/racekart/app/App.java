package org.william.racekart.app;

import lombok.Getter;
import lombok.Setter;
import org.william.racekart.domain.RaceResult;
import org.william.racekart.services.RaceResultService;
import org.william.racekart.services.RaceResultServiceImpl;

@Getter
@Setter
public class App {

    private RaceResultService raceResultService;

    private App() {
        setRaceResultService(RaceResultServiceImpl.getInstance());
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        String inputPath = args.length > 0 ? args[0] : null;
        String outputPath = args.length > 1 ? args[1] : null;
        RaceResult raceResult = app.getRaceResultService().getResults(inputPath);
        app.getRaceResultService().writeResults(outputPath, raceResult);
    }
}
