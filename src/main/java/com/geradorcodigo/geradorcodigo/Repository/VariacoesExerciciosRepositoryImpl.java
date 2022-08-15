package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Model.VariacoesExercicios;

@Repository
public class VariacoesExerciciosRepositoryImpl implements VariacoesExerciciosRepository{

    private static String SELECT_VARIACOES_EXERCICIOS = "select * from mc_variacoes_exercicios where exercicio = " +
    "?";

    private static String SELECT_VARIACAO_EXERCICIO = "select * from mc_variacoes_exercicios where id = " +
    "?";
    private static String SELECT_VARIACOES_EXERCICIOS_MUSCULO = "select * from mc_variacoes_exercicios where musculo = " +
    "?";
    private static String INSERT = " insert into mc_variacoes_exercicios (descricao, musculo, exercicio) "
            + " values (?, ?, ?) ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private MusculoRepository musculoRepo;

    @Autowired
    private ExercicioRepository exercicioRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public VariacoesExercicios cadastrarVariacao(VariacoesExercicios variacoes) {

        jbdcTemplate.update(INSERT, new Object[] { variacoes.getDescricao(), variacoes.getMusculo().getId(), 
        variacoes.getExercicio().getId()});

        return variacoes;
    }

    public VariacoesExercicios obterVariacoesPorId(int variacaoId){

        return jbdcTemplate.queryForObject(SELECT_VARIACAO_EXERCICIO, new Object[] {variacaoId}, new RowMapper<VariacoesExercicios>(){

            @Override
            public VariacoesExercicios mapRow(ResultSet rs, int rownumber) throws SQLException{

                VariacoesExercicios variacoes = new VariacoesExercicios();
                Musculo musculo = new Musculo();
                Exercicio exercicio = new Exercicio();

                variacoes.setId(rs.getInt("id"));
                variacoes.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                variacoes.setMusculo(musculo);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                variacoes.setExercicio(exercicio);
                
                return variacoes;

            }
        });
    }

    public List<VariacoesExercicios> obterVariacoes(int exercicioId){

        return jbdcTemplate.query(SELECT_VARIACOES_EXERCICIOS, new RowMapper<VariacoesExercicios>(){

            @Override
            public VariacoesExercicios mapRow(ResultSet rs, int rownumber) throws SQLException{

                VariacoesExercicios variacoes = new VariacoesExercicios();
                Musculo musculo = new Musculo();
                Exercicio exercicio = new Exercicio();

                variacoes.setId(rs.getInt("id"));
                variacoes.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                variacoes.setMusculo(musculo);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                variacoes.setExercicio(exercicio);
                
                return variacoes;

            }
        }, exercicioId);
    }

    public List<VariacoesExercicios> obterVariacoesPorMusculo(int musculoId){

        return jbdcTemplate.query(SELECT_VARIACOES_EXERCICIOS_MUSCULO, new RowMapper<VariacoesExercicios>(){

            @Override
            public VariacoesExercicios mapRow(ResultSet rs, int rownumber) throws SQLException{

                VariacoesExercicios variacoes = new VariacoesExercicios();
                Musculo musculo = new Musculo();
                Exercicio exercicio = new Exercicio();

                variacoes.setId(rs.getInt("id"));
                variacoes.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                variacoes.setMusculo(musculo);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                variacoes.setExercicio(exercicio);
                
                return variacoes;

            }
        }, musculoId);
    }
     
}
