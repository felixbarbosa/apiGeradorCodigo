package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Carga;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;

@Repository
public class CargaRepositoryImpl implements CargaRepository{

    private static String SELECT_CARGA_ALUNO_EXERCICIO = "select * from mc_carga where aluno = ? " 
            + "and exercicio = ? order by data";
    private static String SELECT_CARGA_ALUNO_EXERCICIO_ULTIMO = "select id, aluno, exercicio, carga, " 
            + "data from mc_carga where aluno = ? and exercicio = ? "
            + "ORDER BY data DESC LIMIT 1";
    private static String SELECT_CARGA_ALUNO_EXERCICIO_PRIMEIRO = "select id, aluno, exercicio, carga, " 
            + "data from mc_carga where aluno = ? and exercicio = ? "
            + "ORDER BY data LIMIT 1";
    private static String SELECT_CARGA_ALUNO = "select * from mc_carga where aluno = ?";
    private static String INSERT = " insert into mc_carga (carga, aluno, exercicio, data) "
            + " values (?, ?, ?, ?) ";
    //private static String UPDATE = " update mc_carga set nome = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private ExercicioRepository exercicioRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Carga salvarCarga(Carga carga) {

        Date data;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(carga.getData());
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());
            System.out.println("Data = " + dataSQL);

            jbdcTemplate.update(INSERT, new Object[] {carga.getCarga(), carga.getAluno().getId(), 
                carga.getExercicio().getId(), dataSQL});

            return carga;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public List<Carga> obterCargasPorAlunoPorExercicio(int aluno, int exercicio){
        return jbdcTemplate.query(SELECT_CARGA_ALUNO_EXERCICIO, new RowMapper<Carga>(){

            @Override
            public Carga mapRow(ResultSet rs, int rownumber) throws SQLException{

                Carga carga = new Carga();
                Aluno aluno = new Aluno();
                Exercicio exercicio = new Exercicio();

                carga.setId(rs.getInt("id"));

                aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                carga.setAluno(aluno);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                carga.setExercicio(exercicio);

                carga.setCarga(rs.getString("carga"));
                carga.setData(rs.getDate("data").toString());

                return carga;

            }
        }, aluno, exercicio);
    }

    public Carga obterUltimaCargaPorAlunoPorExercicio(int aluno, int exercicio) {
       
        return jbdcTemplate.queryForObject(SELECT_CARGA_ALUNO_EXERCICIO_ULTIMO, new Object[] {aluno, exercicio}, new RowMapper<Carga>() {
            @Override
            public Carga mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Carga carga = new Carga();
                Aluno aluno = new Aluno();
                Exercicio exercicio = new Exercicio();

                carga.setId(rs.getInt("id"));

                aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                carga.setAluno(aluno);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                carga.setExercicio(exercicio);

                carga.setCarga(rs.getString("carga"));

                carga.setData(rs.getDate("data").toString());

                return carga;
            }
        });

    }

    public Carga obterPrimeiraCargaPorAlunoPorExercicio(int aluno, int exercicio) {
       
        return jbdcTemplate.queryForObject(SELECT_CARGA_ALUNO_EXERCICIO_PRIMEIRO, new Object[] {aluno, exercicio}, new RowMapper<Carga>() {
            @Override
            public Carga mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Carga carga = new Carga();
                Aluno aluno = new Aluno();
                Exercicio exercicio = new Exercicio();

                carga.setId(rs.getInt("id"));

                aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                carga.setAluno(aluno);

                exercicio = exercicioRepo.obterExercicioPorId(rs.getInt("exercicio"));
                carga.setExercicio(exercicio);

                carga.setCarga(rs.getString("carga"));
                carga.setData(rs.getDate("data").toString());

                return carga;
            }
        });

    }

    

    
}
