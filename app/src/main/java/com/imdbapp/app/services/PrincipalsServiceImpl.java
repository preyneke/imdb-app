package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Names;
import com.imdbapp.app.DAO.entities.Principals;
import com.imdbapp.app.DAO.repositories.NamesRepository;
import com.imdbapp.app.DAO.repositories.PrincipalsRepository;
import com.imdbapp.app.DAO.repositories.TitlesRepository;
import com.imdbapp.app.DTO.ActorDto;
import com.imdbapp.app.DTO.CastDto;
import com.imdbapp.app.exceptions.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PrincipalsServiceImpl implements PrincipalsService {

    @Resource
    NamesRepository namesRepository;

    @Resource
    PrincipalsRepository principalsRepository;

    @Resource
    TitleService titleService;


    @Override
    public List<CastDto> principalsByTconst(String tconst) {

        //Get Names of Principals Associated to the title
        List<Principals> principals = principalsRepository.findPrincipalsByTconst(tconst);

        // see if names contain actors
        List<Names> namesfromFilm = namesRepository.findNamesByKnownForTitlesContains(tconst);


        //Start CastList
        List<CastDto> cast = principals.stream()
                .map(c -> CastDto.builder()
                        .nconst(c.getNconst())
                        .name((namesRepository.findByNconst(c.getNconst()) != null) ? namesRepository.findByNconst(c.getNconst()).getPrimaryName() : "N/A")
                        .character(c.getCharacters())
                        .job(c.getJob())
                        .build()).collect(Collectors.toList());

        return cast;
    }

    @Override
    public List<ActorDto> listOfActorsByName(String name) throws TitleNotFoundException {
        try {


            List<Names> names = namesRepository.findNamesByPrimaryName(name);
            List<Names> actors = names.stream().filter(a -> a.getPrimaryProfession().contains("actor")).collect(Collectors.toList());

            List<ActorDto> actorDto = actors.stream().map(a -> ActorDto.builder()
                    .name(a.getPrimaryName())
                    .knownForFilms(titleService.getTitlesNameByTconst(split(a.getKnownForTitles())))
                    .build()).collect(Collectors.toList());

            return actorDto;
        } catch (Exception e) {
            throw new TitleNotFoundException("No title with That name found");
        }
    }

        public static List<String> split(String str){
            return Stream.of(str.split(","))
                    .map (elem -> new String(elem))
                    .collect(Collectors.toList());
        }
    }

