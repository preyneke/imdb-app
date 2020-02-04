package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Principals;
import com.imdbapp.app.DAO.repositories.NamesRepository;
import com.imdbapp.app.DAO.repositories.PrincipalsRepository;
import com.imdbapp.app.DTO.CastDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("principalsServiceImpl")
public class PrincipalsServiceImpl implements PrincipalsService {

    @Autowired
    NamesRepository namesRepository;

    @Autowired
    PrincipalsRepository principalsRepository;



    @Override
    public List<CastDto> principalsByTconst(String tconst) {

        //Get Names of Principals Associated to the title
        List<Principals> principals = principalsRepository.findPrincipalsByTconst(tconst);


        //Start CastList
       List<CastDto> cast = principals.stream()
                                      .map( c -> CastDto.builder()
                                              .nconst(c.getNconst())
                                              .name((namesRepository.findByNconst(c.getNconst()) != null)? namesRepository.findByNconst(c.getNconst()).getPrimaryName():"N/A")
                                              .character(c.getCharacters())
                                              .job(c.getJob())
                                              .build()).collect(Collectors.toList());

        return cast;
    }
}
