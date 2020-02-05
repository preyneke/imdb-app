package com.imdbapp.app.DAO.sprocs;

import com.imdbapp.app.DTO.TopTitlesDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopGenresStoredProcedure extends StoredProcedure {

    public static final String topGenresSproc = "titles_getTopGenres";
    public static final String QUERY_RESULTS = "RESULTS";

    public TopGenresStoredProcedure(JdbcTemplate jdbcTemplate) {

        super(jdbcTemplate, topGenresSproc);
        RowMapper rowMapper = new TopGenresRowMapper();
        declareParameter(new SqlReturnResultSet(QUERY_RESULTS, rowMapper));
        declareParameter(new SqlParameter("genre", Types.VARCHAR));
        declareParameter(new SqlParameter("numberOfResults", Types.INTEGER));

        // now compile stored proc
        compile();
    }

    public List<TopTitlesDto> topTitlesByGenre(String genre, int numberOfResults){

        String likeGenre = new String("%"+ genre+"%");
        Map data = executeSproc(likeGenre,numberOfResults);
        List<TopTitlesDto> listOfTitles = (List<TopTitlesDto>) data.get(QUERY_RESULTS);


        return listOfTitles;
    }

    public Map executeSproc(String genre, int numberOfResults) {
        Map inParams = new HashMap();
        inParams.put("genre", genre);
        inParams.put("numberOfResults", numberOfResults);
        Map out = execute(inParams);
        return out;
    }
}

   class TopGenresRowMapper implements RowMapper{

       @Override
       public TopTitlesDto mapRow(ResultSet resultSet, int row) throws SQLException {

           TopTitlesDto topTitlesDto = TopTitlesDto.builder()
                   .number(resultSet.getLong("number"))
                   .tconst(resultSet.getString("tConst"))
                   .originalTitle(resultSet.getString("originalTitle"))
                   .averageRating(resultSet.getDouble("averageRating"))
                   .numberOfVotes(resultSet.getLong("numberOfVotes"))
                   .build();
           return topTitlesDto;
       }
   }