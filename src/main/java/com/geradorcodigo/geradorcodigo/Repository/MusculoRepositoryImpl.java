package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.geradorcodigo.geradorcodigo.Model.Musculo;

@Repository
public class MusculoRepositoryImpl implements MusculoRepository{

    private static String SELECT_MUSCULO_ID = "select * from mc_musculo where id = ?";

    private static String SELECT_MUSCULOS = "select * from mc_musculo";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Musculo> obterMusculos(){

        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());

        System.out.println("Data = " + dataSQL.toString());
        return jbdcTemplate.query(SELECT_MUSCULOS, new RowMapper<Musculo>(){

            @Override
            public Musculo mapRow(ResultSet rs, int rownumber) throws SQLException{

                Musculo musculo = new Musculo();
                musculo.setId(rs.getInt("id"));
                musculo.setDescricao(rs.getString("descricao"));
                musculo.setImagem(rs.getString("imagem"));
                
                return musculo;

            }
        });
    }

    public Musculo obterMusculoPorId(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_MUSCULO_ID, new Object[] {id}, new RowMapper<Musculo>() {
            @Override
            public Musculo mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Musculo musculo = new Musculo();

                musculo.setId(rs.getInt("id"));
                musculo.setDescricao(rs.getString("descricao"));
                musculo.setImagem(rs.getString("imagem"));

                return musculo;
            }
        });

    }
     
}
