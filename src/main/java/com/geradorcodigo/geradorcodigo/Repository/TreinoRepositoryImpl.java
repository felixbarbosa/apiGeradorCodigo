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
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Model.Objetivo;
import com.geradorcodigo.geradorcodigo.Model.Treino;

@Repository
public class TreinoRepositoryImpl implements TreinoRepository{

    private static String SELECT_TREINO_ALUNO = "select mc_treino.objetivo, mc_treino.nome, mc_treino.\"diaSemana\", mc_musculo.descricao, mc_musculo.id as musculoId " 
    + "from mc_treino "
    + "inner join mc_musculo "
    + "on mc_musculo.id = mc_treino.musculo "
    + "where mc_treino.aluno = ? "
    + "group by mc_musculo.descricao, mc_treino.nome, mc_musculo.id, mc_treino.\"diaSemana\", mc_treino.objetivo";

    private static String SELECT_TREINO_POR_DIA = "select mc_musculo.descricao as musculoAlvo, " 
    + "mc_exercicio.descricao as exercicio, mc_treino.series, mc_treino.repeticoes, mc_treino.descanso, "
    + "mc_treino.velocidade from mc_treino "
    + "inner join mc_musculo on mc_treino.musculo = mc_musculo.id "
    + "inner join mc_aluno on mc_treino.aluno = mc_aluno.id "
    + "inner join mc_exercicio on mc_treino.exercicio = mc_exercicio.id "
    + "inner join mc_dia_semana on mc_treino.\"diaSemana\" = mc_dia_semana.id "
    + "where mc_dia_semana.id = ? and mc_aluno.id = ?";

    private static String SELECT_TREINO_POR_MUSCULO = "select mc_musculo.descricao as musculoAlvo, " 
    + "mc_exercicio.descricao as exercicio, mc_treino.series, mc_treino.repeticoes, mc_treino.descanso, "
    + "mc_treino.velocidade from mc_treino "
    + "inner join mc_musculo on mc_treino.musculo = mc_musculo.id "
    + "inner join mc_aluno on mc_treino.aluno = mc_aluno.id "
    + "inner join mc_exercicio on mc_treino.exercicio = mc_exercicio.id "
    + "inner join mc_dia_semana on mc_treino.\"diaSemana\" = mc_dia_semana.id "
    + "where mc_musculo.id = ? and mc_aluno.id = ?";

    private static String INSERT = "insert into mc_treino (nome, exercicio, aluno, repeticoes, velocidade, "
            + "descanso, musculo, \"diaSemana\", series, objetivo) "
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static String UPDATE = " update mc_treino set exercicio = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private DiaSemanaRepository diaSemanaRepo;
    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Treino inserirTreinoAluno(Treino treino) {

        jbdcTemplate.update(INSERT, new Object[] {treino.getNome(), treino.getExercicio().getId(), 
        treino.getAluno().getId(), treino.getRepeticoes(), treino.getVelocidade(), treino.getDescanso(), 
        treino.getMusculoAlvo().getId(), treino.getDiaSemana().getId(), treino.getSeries(), treino.getObjetivo().getId()});

        return treino;
    }

    public Treino atualizar(Treino treino) {
    
        jbdcTemplate.update(UPDATE, new Object[] { treino.getExercicio().getId(), treino.getId()});

        return treino; 
    }

    public List<Treino> obterTreinoAluno(int alunoId){

        return jbdcTemplate.query(SELECT_TREINO_ALUNO, new RowMapper<Treino>(){

            @Override
            public Treino mapRow(ResultSet rs, int rownumber) throws SQLException{

                Treino treino = new Treino();
                Musculo musculo = new Musculo();
                DiaSemana diaSemana = new DiaSemana();
                Objetivo objetivo = new Objetivo();
                
                //treino.setId(rs.getInt("id"));

                //aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                //treino.setAluno(aluno);

                diaSemana = diaSemanaRepo.obterDiaSemanaPorId(rs.getInt("diaSemana"));
                treino.setDiaSemana(diaSemana);

                //musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                musculo.setId(rs.getInt("musculoId"));
                musculo.setDescricao(rs.getString("descricao"));
                treino.setMusculoAlvo(musculo);

                //exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                //treino.setExercicio(exercicio);

                //treino.setDescanso(rs.getString("descanso"));
                treino.setNome(rs.getString("nome"));

                objetivo.setId(rs.getInt("objetivo"));
                treino.setObjetivo(objetivo);
                
                //treino.setRepeticoes(rs.getString("repeticoes"));
                //treino.setSeries(rs.getString("series"));
                //treino.setVelocidade(rs.getString("velocidade"));
                
                return treino;

            }
        }, alunoId);
    }

    public List<Treino> obterTreinoAlunoPorDia(int alunoId, int diaSemanaId){
        
        return jbdcTemplate.query(SELECT_TREINO_POR_DIA, new RowMapper<Treino>(){

            @Override
            public Treino mapRow(ResultSet rs, int rownumber) throws SQLException{

                Treino treino = new Treino();
                Musculo musculo = new Musculo();
                Exercicio exercicio = new Exercicio();

                musculo.setDescricao(rs.getString("musculoAlvo"));
                treino.setMusculoAlvo(musculo);

                exercicio.setDescricao(rs.getString("exercicio"));
                treino.setExercicio(exercicio);

                treino.setDescanso(rs.getString("descanso"));
                treino.setRepeticoes(rs.getString("repeticoes"));
                treino.setSeries(rs.getString("series"));
                treino.setVelocidade(rs.getString("velocidade"));
                
                return treino;

            }
        }, diaSemanaId, alunoId);
    }

    public List<Treino> obterTreinoAlunoPorMusculo(int alunoId, int musculoId){
        
        return jbdcTemplate.query(SELECT_TREINO_POR_MUSCULO, new RowMapper<Treino>(){

            @Override
            public Treino mapRow(ResultSet rs, int rownumber) throws SQLException{

                Treino treino = new Treino();
                Musculo musculo = new Musculo();
                Exercicio exercicio = new Exercicio();

                musculo.setDescricao(rs.getString("musculoAlvo"));
                treino.setMusculoAlvo(musculo);

                exercicio.setDescricao(rs.getString("exercicio"));
                treino.setExercicio(exercicio);

                treino.setDescanso(rs.getString("descanso"));
                treino.setRepeticoes(rs.getString("repeticoes"));
                treino.setSeries(rs.getString("series"));
                treino.setVelocidade(rs.getString("velocidade"));
                
                return treino;

            }
        }, musculoId, alunoId);
    }
     
}
