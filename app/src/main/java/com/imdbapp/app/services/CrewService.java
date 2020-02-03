package com.imdbapp.app.services;

import com.imdbapp.app.DTO.CrewDto;

import java.util.List;

public interface CrewService {
List<CrewDto> crewMembersByTconst(String tconst);
}
