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
import com.geradorcodigo.geradorcodigo.Model.Treino;
import com.geradorcodigo.geradorcodigo.Model.VariacoesExercicios;

@Repository
public class TreinoRepositoryImpl implements TreinoRepository{

    private static String SELECT_TREINO_ALUNO = "select mc_treino.nome, mc_treino.\"diaSemana\" " 
    + "from mc_treino "
    + "inner join mc_musculo "
    + "on mc_musculo.id = mc_treino.musculo "
    + "where mc_treino.aluno = ? "
    + "group by mc_treino.nome, mc_treino.\"diaSemana\"";

    private static String SELECT_TREINO_ALUNO_NOME = "select mc_treino.nome, mc_treino.\"diaSemana\", mc_musculo.descricao, mc_musculo.id as musculoId " 
    + "from mc_treino "
    + "inner join mc_musculo "
    + "on mc_musculo.id = mc_treino.musculo "
    + "where mc_treino.aluno = ? "
    + "and mc_treino.nome = ? "
    + "group by mc_musculo.descricao, mc_treino.nome, mc_musculo.id, mc_treino.\"diaSemana\"";
    
    private static String SELECT_TREINO_POR_DIA_ALUNO = "select mc_musculo.descricao as musculoAlvo, mc_exercicio.id, " 
    + "mc_exercicio.descricao as exercicio, mc_treino.series, mc_exercicio.url as imagemexercicio, mc_treino.repeticoes, mc_treino.descanso, "
    + "mc_treino.velocidade, mc_treino.instrucao, mc_treino.variacaoexercicio from mc_treino "
    + "inner join mc_musculo on mc_treino.musculo = mc_musculo.id "
    + "inner join mc_aluno on mc_treino.aluno = mc_aluno.id "
    + "inner join mc_exercicio on mc_treino.exercicio = mc_exercicio.id "
    + "inner join mc_dia_semana on mc_treino.\"diaSemana\" = mc_dia_semana.id "
    + "where mc_dia_semana.id = ? and mc_aluno.id = ? order by musculoAlvo";

    private static String SELECT_TREINO_NOME_ALUNO = "select mc_treino.nome from mc_treino "
    + "inner join mc_musculo on mc_treino.musculo = mc_musculo.id "
    + "inner join mc_aluno on mc_treino.aluno = mc_aluno.id "
    + "inner join mc_exercicio on mc_treino.exercicio = mc_exercicio.id "
    + "inner join mc_dia_semana on mc_treino.\"diaSemana\" = mc_dia_semana.id "
    + "where mc_dia_semana.id = ? and mc_aluno.id = ? group by mc_treino.nome";

    private static String SELECT_TREINO_POR_MUSCULO = "select mc_musculo.descricao as musculoAlvo, " 
    + "mc_exercicio.descricao as exercicio, mc_exercicio.url as imagemexercicio, mc_treino.series, mc_treino.repeticoes, mc_treino.descanso, "
    + "mc_treino.velocidade, mc_treino.instrucao, mc_treino.variacaoexercicio from mc_treino "
    + "inner join mc_musculo on mc_treino.musculo = mc_musculo.id "
    + "inner join mc_aluno on mc_treino.aluno = mc_aluno.id "
    + "inner join mc_exercicio on mc_treino.exercicio = mc_exercicio.id "
    + "inner join mc_dia_semana on mc_treino.\"diaSemana\" = mc_dia_semana.id "
    + "where mc_musculo.id = ? and mc_aluno.id = ?";

    private static String INSERT = "insert into mc_treino (nome, exercicio, aluno, repeticoes, velocidade, "
            + "descanso, musculo, \"diaSemana\", series, instrucao, variacaoexercicio) "
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static String UPDATE = " update mc_treino set exercicio = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private DiaSemanaRepository diaSemanaRepo;

    @Autowired
    private VariacoesExerciciosRepository variacaoRepo;
    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Treino salvarTreino(Treino treino) {

        jbdcTemplate.update(INSERT, new Object[] {treino.getNome(), treino.getExercicio().getId(), 
        treino.getAluno().getId(), treino.getRepeticoes(), treino.getVelocidade(), treino.getDescanso(), 
        treino.getMusculoAlvo().getId(), treino.getDiaSemana().getId(), treino.getSeries(), treino.getInstrucao(),
        treino.getVariacaoExercicio().getId()});

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
                DiaSemana diaSemana = new DiaSemana();

                diaSemana = diaSemanaRepo.obterDiaSemanaPorId(rs.getInt("diaSemana"));
                treino.setDiaSemana(diaSemana);

                treino.setNome(rs.getString("nome"));
                
                return treino;

            }
        }, alunoId);
    }

    public List<Treino> obterTreinoAlunoNome(int alunoId, String nome){

        return jbdcTemplate.query(SELECT_TREINO_ALUNO_NOME, new RowMapper<Treino>(){

            @Override
            public Treino mapRow(ResultSet rs, int rownumber) throws SQLException{

                Treino treino = new Treino();
                DiaSemana diaSemana = new DiaSemana();
                Musculo musculo = new Musculo();

                diaSemana = diaSemanaRepo.obterDiaSemanaPorId(rs.getInt("diaSemana"));
                treino.setDiaSemana(diaSemana);

                musculo.setId(rs.getInt("musculoId"));
                musculo.setDescricao(rs.getString("descricao"));
                treino.setMusculoAlvo(musculo);

                treino.setNome(rs.getString("nome"));
                
                return treino;

            }
        }, alunoId, nome);
    }

    public List<Treino> obterTreinoAlunoPorDia(int alunoId, int diaSemanaId){
        
        return jbdcTemplate.query(SELECT_TREINO_POR_DIA_ALUNO, new RowMapper<Treino>(){

            @Override
            public Treino mapRow(ResultSet rs, int rownumber) throws SQLException{

                Treino treino = new Treino();
                Musculo musculo = new Musculo();
                Exercicio exercicio = new Exercicio();
                VariacoesExercicios variacao = new VariacoesExercicios();

                musculo.setDescricao(rs.getString("musculoAlvo"));
                treino.setMusculoAlvo(musculo);

                if(rs.getInt("variacaoexercicio") == 0){
                    treino.setVariacaoExercicio(null);
                }else{
                    variacao = variacaoRepo.obterVariacoesPorId(rs.getInt("variacaoexercicio"));
                    treino.setVariacaoExercicio(variacao);
                }
                
                exercicio.setDescricao(rs.getString("exercicio"));
                exercicio.setUrlImagem(rs.getString("imagemexercicio"));
                exercicio.setId(rs.getInt("id"));
                treino.setExercicio(exercicio);

                treino.setDescanso(rs.getString("descanso"));
                treino.setRepeticoes(rs.getString("repeticoes"));
                treino.setSeries(rs.getString("series"));
                treino.setVelocidade(rs.getString("velocidade"));
                treino.setInstrucao(rs.getString("instrucao"));
                
                return treino;

            }
        }, diaSemanaId, alunoId);
    }

    public List<Treino> obterTreinoNomeAluno(int alunoId, int diaSemanaId){
        
        return jbdcTemplate.query(SELECT_TREINO_NOME_ALUNO, new RowMapper<Treino>(){

            @Override
            public Treino mapRow(ResultSet rs, int rownumber) throws SQLException{

                Treino treino = new Treino();

                treino.setNome(rs.getString("nome"));
                
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
                VariacoesExercicios variacao = new VariacoesExercicios();

                musculo.setDescricao(rs.getString("musculoAlvo"));
                treino.setMusculoAlvo(musculo);

                if(rs.getInt("variacaoexercicio") == 0){
                    treino.setVariacaoExercicio(null);
                }else{
                    variacao = variacaoRepo.obterVariacoesPorId(rs.getInt("variacaoexercicio"));
                    treino.setVariacaoExercicio(variacao);
                }
                
                exercicio.setDescricao(rs.getString("exercicio"));
                exercicio.setUrlImagem(rs.getString("imagemexercicio"));
                treino.setExercicio(exercicio);

                treino.setDescanso(rs.getString("descanso"));
                treino.setRepeticoes(rs.getString("repeticoes"));
                treino.setSeries(rs.getString("series"));
                treino.setVelocidade(rs.getString("velocidade"));
                treino.setInstrucao(rs.getString("instrucao"));
                
                return treino;

            }
        }, musculoId, alunoId);
    }
     
}
