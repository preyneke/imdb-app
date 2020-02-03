package com.imdbapp.app.batch.names;

import com.imdbapp.app.DAO.entities.Names;
import com.imdbapp.app.DAO.entities.NamesFromFile;
import com.imdbapp.app.DAO.entities.Titles;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.item.ItemProcessor;

public class NamesDataValidator implements ItemProcessor<NamesFromFile, Names> {
    @Override
    public Names process(NamesFromFile namesFromFile) throws Exception {
    Names name = Names.builder().nconst(namesFromFile.getNconst())
        .primaryName(namesFromFile.getPrimaryName())
        .birthYear(NumberUtils.isCreatable(namesFromFile.getBirthYear())? NumberUtils.toLong(namesFromFile.getBirthYear()):null)
        .deathYear(NumberUtils.isCreatable(namesFromFile.getDeathYear())?NumberUtils.toLong(namesFromFile.getDeathYear()):null)
        .knownForTitles(namesFromFile.getKnownForTitles())
        .primaryProfession(namesFromFile.getPrimaryProfession())
        .build();
        System.out.println("Inserting title : " + namesFromFile);
        return name;
    }
}
