package com.itemis;

import java.util.List;

public class MetalService {
    private MetalCreditsRepository metalCreditsRepository;
    private GalacticRomanRepository galacticRomanRepository;

    public MetalService(MetalCreditsRepository metalCreditsRepository, GalacticRomanRepository galacticRomanRepository) {
        this.metalCreditsRepository = metalCreditsRepository;
        this.galacticRomanRepository = galacticRomanRepository;
    }

    public void x (List<String> galacticElements, String metal, int credits) throws HandledException {
        //Verify if galactic value already exists
        //Calculated metal credits
        // store metal credits value in repository
        for(int i = 0; i < galacticElements.size(); i++){

            if(galacticRomanRepository.getGalacticRomanValues(galacticElements.get(i)) == null){
                throw new HandledException();

            }
        }




        }
}
