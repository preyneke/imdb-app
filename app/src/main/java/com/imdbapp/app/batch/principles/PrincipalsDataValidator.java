package com.imdbapp.app.batch.principles;

import com.imdbapp.app.DAO.entities.Principals;
import com.imdbapp.app.DAO.entities.PrincipalsFromFile;
import org.springframework.batch.item.ItemProcessor;

import java.util.Arrays;

public class PrincipalsDataValidator implements ItemProcessor<PrincipalsFromFile, Principals> {
    @Override
    public Principals process(PrincipalsFromFile principalsFromFile) throws Exception {
       Principals principals = Principals.builder()
               .tconst(principalsFromFile.getTconst())
               .nconst(principalsFromFile.getNconst())
               .category(principalsFromFile.getCategory())
               .job(principalsFromFile.getJob())
               .characters(principalsFromFile.getCharacters()).build();
        System.out.println("Inserting Principle "+ principals);
        return principals;
    }
}
