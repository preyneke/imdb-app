package com.imdbapp.app.DAO.sprocs;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopGenresStoredProcedure extends StoredProcedure {

    public static final String topGenresSproc = "titles_getTopGenres";
    public static final String QUERY_RESULTS = rowmapper;

    public TopGenresStoredProcedure(JdbcTemplate jdbcTemplate) {

        super(jdbcTemplate, topGenresSproc);
        RowMapper rowMapper = new
        declareParameter(new SqlReturnResultSet(QUERY_RESULTS, rowma) -> {return rs.("canLogin");}));
        declareParameter(new SqlParameter("genre", Types.VARCHAR));
        declareParameter(new SqlParameter("numberOfResults", Types.INTEGER));

        // now compile stored proc
        compile();
    }

    public boolean canLogin(Integer siloID, String login){

        TrustAccountLimitsDTO DTO = new TrustAccountLimitsDTO();
        Map data = executeSproc(siloID,login);
        ArrayList results = (ArrayList) data.get(QUERY_RESULTS);
        boolean canLogin = (Boolean) results.get(0);

        return canLogin;
    }

    public Map executeSproc(Integer siloID, String login) {
        Map inParams = new HashMap();
        inParams.put("siloID", siloID);
        inParams.put("login", login);
        Map out = execute(inParams);
        return out;
    }
}

   class TopGenresRowMapper implements RowMapper{

       @Override
       public Object mapRow(ResultSet resultSet, int i) throws SQLException {
           return null;
       }
   }