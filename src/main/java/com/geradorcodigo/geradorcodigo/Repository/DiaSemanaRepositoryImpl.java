package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.geradorcodigo.geradorcodigo.Model.DiaSemana;

@Repository
public class DiaSemanaRepositoryImpl implements DiaSemanaRepository{

    private static String SELECT_DIAS_SEMANA = "select * from mc_dia_semana";
    private static String SELECT_DIA_SEMANA_ID = "select * from mc_dia_semana where id = ?";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<DiaSemana> obterDiasSemana(){

        return jbdcTemplate.query(SELECT_DIAS_SEMANA, new RowMapper<DiaSemana>(){

            @Override
            public DiaSemana mapRow(ResultSet rs, int rownumber) throws SQLException{

                DiaSemana diaSemana = new DiaSemana();
                diaSemana.setId(rs.getInt("id"));
                diaSemana.setDia(rs.getString("dia"));
                
                return diaSemana;

            }
        });
    }

    public DiaSemana obterDiaSemanaPorId(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_DIA_SEMANA_ID, new Object[] {id}, new RowMapper<DiaSemana>() {
            @Override
            public DiaSemana mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                DiaSemana diaSemana = new DiaSemana();

                diaSemana.setId(rs.getInt("id"));
                diaSemana.setDia(rs.getString("dia"));

                return diaSemana;
            }
        });

    }
     
}
