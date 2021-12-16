package com.itemis;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultDisplayer {
    private final GalacticToCreditsResultRepository galacticToCreditsResultRepository;
    private final MetalToCreditsResultRepository metalToCreditsResultRepository;
    private static ArrayList<String> result = new ArrayList<>();

    public ResultDisplayer(GalacticToCreditsResultRepository galacticToCreditsResultRepository, MetalToCreditsResultRepository metalToCreditsResultRepository) {
        this.galacticToCreditsResultRepository = galacticToCreditsResultRepository;
        this.metalToCreditsResultRepository = metalToCreditsResultRepository;
    }

    public void displayResult() {
        result.addAll(galacticToCreditsResultRepository.getGalacticToCreditsResult());
        result.addAll(metalToCreditsResultRepository.getGalacticToCreditsResult());

        for ( Object line : result){
            System.out.println(line.toString());
        }
    }

    public void addResult(String result){
        this.result.add(result);
    }
}
