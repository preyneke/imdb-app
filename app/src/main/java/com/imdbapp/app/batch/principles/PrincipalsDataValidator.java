package com.imdbapp.app.batch.principles;

import com.imdbapp.app.DAO.entities.Principals;
import com.imdbapp.app.DAO.entities.PrincipalsFromFile;
import com.imdbapp.app.constants.Constants;
import org.springframework.batch.item.ItemProcessor;


public class PrincipalsDataValidator implements ItemProcessor<PrincipalsFromFile, Principals> {
    @Override
    public Principals process(PrincipalsFromFile principalsFromFile) throws Exception {
       Principals principals = Principals.builder()
               .tconst(principalsFromFile.getTconst())
               .nconst(principalsFromFile.getNconst())
               .ordering(principalsFromFile.getOrdering())
               .category(principalsFromFile.getCategory())
               .job(!principalsFromFile.getJob().contains(Constants.NULL_REFERENCE)? principalsFromFile.getJob(): null)
               .characters(!principalsFromFile.getCharacters().contains(Constants.NULL_REFERENCE)? principalsFromFile.getCharacters():null).build();
        System.out.println("Inserting Principle "+ principals);
        return principals;
    }
}
