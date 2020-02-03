package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Crew;
import com.imdbapp.app.DAO.entities.Names;
import com.imdbapp.app.DAO.repositories.CrewRepository;
import com.imdbapp.app.DAO.repositories.NamesRepository;
import com.imdbapp.app.DTO.CrewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service("crewServiceImpl")
public class CrewServiceImpl implements CrewService {
    @Autowired
    CrewRepository crewRepository;

    @Autowired
    NamesRepository namesRepository;

    @Override
    public List<CrewDto> crewMembersByTconst(String tconst) {

        Crew crewNames = crewRepository.findByTconst(tconst);
        List<String> directors = (crewNames.getDirectors()!=null)? Arrays.asList(crewNames.getDirectors().split("\\s*,\\s*")): Arrays.asList("");
        List<String> writersList = (crewNames.getWriters()!=null)? Arrays.asList(crewNames.getDirectors().split("\\s*,\\s*")): Arrays.asList();
       List<CrewDto> crew = directors.stream().map( c -> CrewDto.builder().nconst(c).primaryName((namesRepository.findByNconst(c) != null)? namesRepository.findByNconst(c).getPrimaryName(): "N/A").creditedAs("Director").build()).collect(Collectors.toList());
       List<CrewDto> writers = (!writersList.isEmpty())? writersList.stream().map(w -> CrewDto.builder().nconst(w).primaryName((namesRepository.findByNconst(w) != null)? namesRepository.findByNconst(w).getPrimaryName(): "N/A").creditedAs("writer").build()).collect(Collectors.toList()): null;
        (!writers.isEmpty())?crew.addAll(writers): return crew;
        return crew;
    }
}
