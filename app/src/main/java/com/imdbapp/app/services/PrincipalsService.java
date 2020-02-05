package com.imdbapp.app.services;

import com.imdbapp.app.DTO.ActorDto;
import com.imdbapp.app.DTO.CastDto;
import com.imdbapp.app.exceptions.TitleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PrincipalsService {
    List<CastDto> principalsByTconst(String tconst);

    List<ActorDto> listOfActorsByName(String name) throws TitleNotFoundException;
}
