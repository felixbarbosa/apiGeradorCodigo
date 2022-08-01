package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.geradorcodigo.geradorcodigo.Model.Objetivo;

@Repository
public class ObjetivoRepositoryImpl implements ObjetivoRepository{

    private static String SELECT_OBJETIVOS = "select * from mc_objetivo";
    private static String SELECT_OBJETIVO_ID = "select * from mc_objetivo where id = ?";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Objetivo obterObjetivoPorId(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_OBJETIVO_ID, new Object[] {id}, new RowMapper<Objetivo>() {
            @Override
            public Objetivo mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Objetivo objetivo = new Objetivo();

                objetivo.setId(rs.getInt("id"));
                objetivo.setObjetivo(rs.getString("objetivo"));

                return objetivo;
            }
        });

    }

    public List<Objetivo> obterObjetivos(){

        return jbdcTemplate.query(SELECT_OBJETIVOS, new RowMapper<Objetivo>(){

            @Override
            public Objetivo mapRow(ResultSet rs, int rownumber) throws SQLException{

                Objetivo entregador = new Objetivo();
                entregador.setId(rs.getInt("id"));
                entregador.setObjetivo(rs.getString("objetivo"));
                
                return entregador;

            }
        });
    }
     
}
