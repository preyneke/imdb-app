package com.imdbapp.app.batch.titles;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.entities.TitlesFromFile;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.item.ItemProcessor;



public class TitlesDataValidator implements ItemProcessor<TitlesFromFile, Titles> {

    public Titles process(TitlesFromFile title) throws Exception
    {

        Titles titleFromFile = Titles.builder()
                .originalTitle(title.getOriginalTitle())
                .primaryTitle(title.getPrimaryTitle())
                .titleType(title.getTitleType())
                .isAdult(title.getIsAdult())
                .tconst(title.getTconst())
                .startYear(NumberUtils.isCreatable(title.getStartYear())? NumberUtils.toLong(title.getStartYear()):null)
                .endYear(NumberUtils.isCreatable(title.getEndYear())? NumberUtils.toLong(title.getEndYear()): null)
                .runtime(NumberUtils.isCreatable(title.getRuntime())? NumberUtils.toLong(title.getRuntime()):null)
                .genres(title.getGenres())
                .build();

        System.out.println("Inserting title : " + title);
        return titleFromFile;
    }
}
