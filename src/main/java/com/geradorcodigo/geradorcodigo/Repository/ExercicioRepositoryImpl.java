package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.VariacoesExercicios;

@Repository
public class ExercicioRepositoryImpl implements ExercicioRepository{

    private static String SELECT_EXERCICIO_ID = "select * from mc_exercicio where id = ?";
    private static String SELECT_EXERCICIO_MUSCULO = "select * from mc_exercicio where musculo = ? "
    + "and professor = 5 or professor = ?";
    private static String SELECT_EXERCICIOS_GERAIS = "select * from mc_exercicio where professor = 5 or professor = ?";
    private static String SELECT_EXERCICIOS_VARIACOES = "select mc_exercicio.id as exercicioid, mc_exercicio.descricao as exercicio, " 
    + "mc_variacoes_exercicios.id as variacaoid, mc_exercicio.professor, mc_exercicio.musculo, "
    + "mc_exercicio.urlimage, mc_exercicio.urlvideo, mc_exercicio.instrucao from mc_exercicio left join mc_variacoes_exercicios "
    + "on mc_variacoes_exercicios.exercicio = mc_exercicio.id "
    + "where mc_exercicio.professor = 5 or mc_exercicio.professor = ?";
    private static String INSERT_EXERCICIO = " insert into mc_exercicio (id, descricao, musculo, professor, urlimage, urlvideo, instrucao) values " +
        " (nextval('mc_exercicio_id_seq'), ?, ?, ?, ?, ?, ?) ";
    private static String UPDATE = " update mc_exercicio set descricao = ?, musculo = ?, urlimage = ?, urlvideo = ?, instrucao = ? where id = ?";
    private static String REMOVE = " delete from mc_exercicio where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired 
    private MusculoRepository musculoRepo;

    @Autowired 
    private PersonalRepository personalRepo;

    @Autowired 
    private VariacoesExerciciosRepository variacoesRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Exercicio salvarExercicio(Exercicio exercicio) {

        //KeyHolder keyHolder = new GeneratedKeyHolder();

        jbdcTemplate.update(INSERT_EXERCICIO, new Object[] {exercicio.getDescricao(), 
        exercicio.getMusculo().getId(), exercicio.getProfessor().getId(), exercicio.getUrlImagem(), exercicio.getUrlVideo(), exercicio.getInstrucao()});

        //System.out.println("Id gerado = " + keyHolder.getKey());
        
        return exercicio;
    }

    public Exercicio obterExercicioPorId(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_EXERCICIO_ID, new Object[] {id}, new RowMapper<Exercicio>() {
            @Override
            public Exercicio mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Exercicio exercicio = new Exercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                exercicio.setProfessor(professor);

                exercicio.setId(rs.getInt("id"));
                exercicio.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                exercicio.setMusculo(musculo);

                exercicio.setUrlImagem(rs.getString("urlimage"));
                exercicio.setUrlVideo(rs.getString("urlvideo"));
                exercicio.setInstrucao(rs.getString("instrucao"));

                return exercicio;
            }
        });

    }

    public Exercicio atualizar(Exercicio exercicio) {
    
        jbdcTemplate.update(UPDATE, new Object[] { exercicio.getDescricao(), exercicio.getMusculo().getId(), exercicio.getUrlImagem(),
            exercicio.getUrlVideo(), exercicio.getInstrucao(), exercicio.getId()});

        return exercicio; 
    }

    public Boolean deletar(int id) {

        boolean removeu = true;

        try {
            jbdcTemplate.update(REMOVE, new Object[] {id});
        } catch (Exception e) {
            removeu = false;
        }

        return removeu; 
    }

    /*public List<Exercicio> obterEntregasPendentes(){

        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());

        System.out.println("Data = " + dataSQL.toString());
        return jbdcTemplate.query(SELECT_ENTREGAS_PENDENTES, new RowMapper<Exercicio>(){

            @Override
            public Exercicio mapRow(ResultSet rs, int rownumber) throws SQLException{

                Exercicio exercicio = new Exercicio();
                exercicio.setId(rs.getInt("id"));
                exercicio.setData(rs.getString("data"));
                exercicio.setCodigo(rs.getString("codigo"));
                exercicio.setHora(rs.getString("hora"));
                exercicio.setLocal(rs.getString("local"));
                exercicio.setTurno(rs.getString("turno"));
                exercicio.setVisto(rs.getInt("visto"));
                
                return exercicio;

            }
        }, dataSQL.toString());
    }*/

    public List<Exercicio> obterExercicios(int professorId){

        return jbdcTemplate.query(SELECT_EXERCICIOS_GERAIS, new RowMapper<Exercicio>(){

            @Override
            public Exercicio mapRow(ResultSet rs, int rownumber) throws SQLException{

                Exercicio exercicio = new Exercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                exercicio.setProfessor(professor);

                exercicio.setId(rs.getInt("id"));
                exercicio.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                exercicio.setMusculo(musculo);

                exercicio.setUrlImagem(rs.getString("urlimage"));
                exercicio.setUrlVideo(rs.getString("urlvideo"));
                exercicio.setInstrucao(rs.getString("instrucao"));
                
                return exercicio;

            }
        }, professorId);
    }

    public List<Exercicio> obterExerciciosPorMusculo(int musculoId, int professorId){

        return jbdcTemplate.query(SELECT_EXERCICIO_MUSCULO, new RowMapper<Exercicio>(){

            @Override
            public Exercicio mapRow(ResultSet rs, int rownumber) throws SQLException{

                Exercicio exercicio = new Exercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                exercicio.setProfessor(professor);

                exercicio.setId(rs.getInt("id"));
                exercicio.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(musculoId);
                exercicio.setMusculo(musculo);

                exercicio.setUrlImagem(rs.getString("urlimage"));
                exercicio.setUrlVideo(rs.getString("urlvideo"));
                exercicio.setInstrucao(rs.getString("instrucao"));
                
                return exercicio;

            }
        }, musculoId, professorId);
    }

    public List<Exercicio> obterExerciciosVariacoes(int professorId){

        return jbdcTemplate.query(SELECT_EXERCICIOS_VARIACOES, new RowMapper<Exercicio>(){

            @Override
            public Exercicio mapRow(ResultSet rs, int rownumber) throws SQLException{

                Exercicio exercicio = new Exercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();
                VariacoesExercicios variacoes = new VariacoesExercicios();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                exercicio.setProfessor(professor);

                exercicio.setId(rs.getInt("exercicioid"));
                exercicio.setDescricao(rs.getString("exercicio"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                exercicio.setMusculo(musculo);

                exercicio.setUrlImagem(rs.getString("urlimage"));
                exercicio.setUrlVideo(rs.getString("urlvideo"));
                exercicio.setInstrucao(rs.getString("instrucao"));

                System.out.println("Variacao Id = " + rs.getInt("variacaoid"));

                if(rs.getInt("variacaoid") != 0){
                    variacoes = variacoesRepo.obterVariacoesPorId(rs.getInt("variacaoid"));
                    exercicio.setVariacoes(variacoes);
                }

                
                
                return exercicio;

            }
        }, professorId);
    }
     
}
