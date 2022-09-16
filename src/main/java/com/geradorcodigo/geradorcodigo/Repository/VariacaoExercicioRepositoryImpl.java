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
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.VariacaoExercicio;

@Repository
public class VariacaoExercicioRepositoryImpl implements VariacaoExercicioRepository{

    private static String SELECT_VARIACAO_EXERCICIO_ID = "select * from mc_variacao_exercicio where id = ?";
    private static String SELECT_VARIACAO_EXERCICIO_EXERCICIO = "select * from mc_variacao_exercicio where exercicio = ? "
    + "and (professor = 5 or professor = ?)";
    private static String SELECT_VARIACAO_EXERCICIOS_GERAIS = "select * from mc_variacao_exercicio where professor = 5 or professor = ?";
    private static String INSERT_VARIACAO_EXERCICIO = " insert into mc_variacao_exercicio (id, descricao, musculo, professor, urlimage, urlvideo, instrucao, isvariacao, exercicio) values " +
        " (nextval('mc_exercicio_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static String UPDATE = " update mc_variacao_exercicio set descricao = ?, musculo = ?, urlimage = ?, urlvideo = ?, instrucao = ? where id = ?";
    private static String REMOVE = " delete from mc_variacao_exercicio where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired 
    private MusculoRepository musculoRepo;

    @Autowired 
    private PersonalRepository personalRepo;

    @Autowired 
    private ExercicioRepository exercicioRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public VariacaoExercicio salvarVariacaoExercicio(VariacaoExercicio variacaoExercicio) {

        //KeyHolder keyHolder = new GeneratedKeyHolder();

        System.out.println("Salvar dentro do repository");
        System.out.println("É variacao = " + variacaoExercicio.getIsVariacao());

        jbdcTemplate.update(INSERT_VARIACAO_EXERCICIO, new Object[] {
            variacaoExercicio.getDescricao(), 
            variacaoExercicio.getMusculo().getId(), 
            variacaoExercicio.getProfessor().getId(), 
            variacaoExercicio.getUrlImagem(), 
            variacaoExercicio.getUrlVideo(), 
            variacaoExercicio.getInstrucao(), 
            variacaoExercicio.getIsVariacao() == 1 ? true : false,
            variacaoExercicio.getExercicio().getId()
        });

        //System.out.println("Id gerado = " + keyHolder.getKey());
        
        return variacaoExercicio;
    }

    public VariacaoExercicio obterVariacaoExercicioPorId(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_VARIACAO_EXERCICIO_ID, new Object[] {id}, new RowMapper<VariacaoExercicio>() {
            @Override
            public VariacaoExercicio mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                VariacaoExercicio variacaoExercicio = new VariacaoExercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();
                Exercicio exercicio = new Exercicio();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                variacaoExercicio.setProfessor(professor);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                variacaoExercicio.setExercicio(exercicio);

                variacaoExercicio.setId(rs.getInt("id"));
                variacaoExercicio.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                variacaoExercicio.setMusculo(musculo);

                variacaoExercicio.setUrlImagem(rs.getString("urlimage"));
                variacaoExercicio.setUrlVideo(rs.getString("urlvideo"));
                variacaoExercicio.setInstrucao(rs.getString("instrucao"));
                variacaoExercicio.setIsVariacao(rs.getBoolean("isvariacao") == true ? 1 : 0);

                return variacaoExercicio;
            }
        });

    }

    public VariacaoExercicio atualizar(VariacaoExercicio variacaoExercicio) {
    
        jbdcTemplate.update(UPDATE, new Object[] { variacaoExercicio.getDescricao(), variacaoExercicio.getMusculo().getId(), variacaoExercicio.getUrlImagem(),
            variacaoExercicio.getUrlVideo(), variacaoExercicio.getInstrucao(), variacaoExercicio.getId()});

        return variacaoExercicio; 
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

    public List<VariacaoExercicio> obterVariacoesExercicios(int professorId){

        return jbdcTemplate.query(SELECT_VARIACAO_EXERCICIOS_GERAIS, new RowMapper<VariacaoExercicio>(){

            @Override
            public VariacaoExercicio mapRow(ResultSet rs, int rownumber) throws SQLException{

                VariacaoExercicio variacaoExercicio = new VariacaoExercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();
                Exercicio exercicio = new Exercicio();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                variacaoExercicio.setProfessor(professor);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                variacaoExercicio.setExercicio(exercicio);

                variacaoExercicio.setId(rs.getInt("id"));
                variacaoExercicio.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                variacaoExercicio.setMusculo(musculo);

                variacaoExercicio.setUrlImagem(rs.getString("urlimage"));
                variacaoExercicio.setUrlVideo(rs.getString("urlvideo"));
                variacaoExercicio.setInstrucao(rs.getString("instrucao"));
                variacaoExercicio.setIsVariacao(rs.getBoolean("isvariacao") == true ? 1 : 0);
                
                return variacaoExercicio;

            }
        }, professorId);
    }

    public List<VariacaoExercicio> obterVariaceosExerciciosPorExercicio(int exercicioId, int professorId){

        return jbdcTemplate.query(SELECT_VARIACAO_EXERCICIO_EXERCICIO, new RowMapper<VariacaoExercicio>(){

            @Override
            public VariacaoExercicio mapRow(ResultSet rs, int rownumber) throws SQLException{

                VariacaoExercicio variacaoExercicio = new VariacaoExercicio();
                Musculo musculo = new Musculo();
                Personal professor = new Personal();
                Exercicio exercicio = new Exercicio();

                professor = personalRepo.obterPersonalPorId(rs.getInt("professor"));
                variacaoExercicio.setProfessor(professor);

                exercicio = exercicioRepo.obterExercicioPorId(exercicioId);
                variacaoExercicio.setExercicio(exercicio);

                variacaoExercicio.setId(rs.getInt("id"));
                variacaoExercicio.setDescricao(rs.getString("descricao"));

                musculo = musculoRepo.obterMusculoPorId(rs.getInt("musculo"));
                variacaoExercicio.setMusculo(musculo);

                variacaoExercicio.setUrlImagem(rs.getString("urlimage"));
                variacaoExercicio.setUrlVideo(rs.getString("urlvideo"));
                variacaoExercicio.setInstrucao(rs.getString("instrucao"));
                variacaoExercicio.setIsVariacao(rs.getBoolean("isvariacao") == true ? 1 : 0);
                
                return variacaoExercicio;

            }
        }, exercicioId, professorId);
    }
     
}
