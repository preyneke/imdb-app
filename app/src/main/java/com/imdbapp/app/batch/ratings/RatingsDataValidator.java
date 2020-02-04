package com.imdbapp.app.batch.ratings;

import com.imdbapp.app.DAO.entities.Ratings;
import com.imdbapp.app.DAO.entities.RatingsFromFile;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.item.ItemProcessor;


public class RatingsDataValidator  implements ItemProcessor<RatingsFromFile, Ratings> {
    @Override
    public Ratings process(RatingsFromFile ratingsFromFile) throws Exception {
        Ratings ratings = Ratings.builder()
                .tconst(ratingsFromFile.getTconst())
                .averageRating((NumberUtils.isCreatable(ratingsFromFile.getAverageRating()))? NumberUtils.toDouble(ratingsFromFile.getAverageRating()): null)
                .numberOfVotes((NumberUtils.isCreatable(ratingsFromFile.getNumVotes()))? NumberUtils.toLong(ratingsFromFile.getNumVotes()): null).build();
        System.out.println("Inserting ratings:" + ratings);
        return ratings;
    }
}
