package com.imdbapp.app.batch.crew;

import com.imdbapp.app.DAO.entities.Crew;
import com.imdbapp.app.DAO.entities.CrewFromFile;
import com.imdbapp.app.constants.Constants;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class CrewDataValidator implements ItemProcessor<CrewFromFile, Crew> {
    @Autowired
    Constants constants;
    @Override
    public Crew process(CrewFromFile crewFromFile) throws Exception {
    Crew crew = Crew.builder()
        .tconst(crewFromFile.getTconst())
        .directors(!crewFromFile.getDirectors().contains(Constants.NULL_REFERENCE)? crewFromFile.getDirectors() : null)
        .writers(!crewFromFile.getWriters().contains(Constants.NULL_REFERENCE) ? crewFromFile.getWriters() : null)
        .build();
        System.out.println("Inserting crew : " + crew);
        return  crew;

    }
}
