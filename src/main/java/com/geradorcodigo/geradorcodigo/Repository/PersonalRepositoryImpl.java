package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.geradorcodigo.geradorcodigo.Model.Personal;

@Repository
public class PersonalRepositoryImpl implements PersonalRepository{

    private static String SELECT_PERSONAIS = "select * from mc_personal";
    private static String SELECT_PERSONAL_ID = "select * from mc_personal where id = ?";
    private static String INSERT = " insert into mc_personal (nome, cpf, login, senha, sexo, idade, email, cref) "
            + " values (?, ?, ?, ?, ?, ?, ?, ?) ";
    private static String UPDATE = " update mc_personal set nome = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Personal salvarPersonal(Personal personal) {

        jbdcTemplate.update(INSERT, new Object[] {personal.getNome(), personal.getCpf(), personal.getLogin(), 
        personal.getSenha(), personal.getSexo(), personal.getIdade(), personal.getEmail(), personal.getCref()});

        return personal;
    }

    public Personal atualizarPersonal(Personal personal) {
    
        jbdcTemplate.update(UPDATE, new Object[] { personal.getNome(), personal.getId()});

        return personal; 
    }

    public Personal obterPersonalPorId(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_PERSONAL_ID, new Object[] {id}, new RowMapper<Personal>() {
            @Override
            public Personal mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Personal personal = new Personal();

                personal.setId(rs.getInt("id"));
                personal.setCpf(rs.getString("cpf"));
                personal.setEmail(rs.getString("email"));
                personal.setIdade(rs.getInt("idade"));
                personal.setLogin(rs.getString("login"));
                personal.setNome(rs.getString("nome"));
                personal.setSenha(rs.getString("senha"));
                personal.setSexo(rs.getString("sexo"));
                personal.setCref(rs.getString("cref"));

                return personal;
            }
        });

    }

    public List<Personal> obterTodosPersonais(){

        return jbdcTemplate.query(SELECT_PERSONAIS, new RowMapper<Personal>(){

            @Override
            public Personal mapRow(ResultSet rs, int rownumber) throws SQLException{

                Personal personal = new Personal();

                personal.setId(rs.getInt("id"));
                personal.setCpf(rs.getString("cpf"));
                personal.setEmail(rs.getString("email"));
                personal.setIdade(rs.getInt("idade"));
                personal.setLogin(rs.getString("login"));
                personal.setNome(rs.getString("nome"));
                personal.setSenha(rs.getString("senha"));
                personal.setSexo(rs.getString("sexo"));
                personal.setCref(rs.getString("cref"));
                
                return personal;

            }
        });
    }
     
}
