package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    private static String SELECT_CARGA_ALUNO_EXERCICIO = "select * from mc_carga where aluno = ? and exercicio = ?";
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

        jbdcTemplate.update(INSERT, new Object[] {carga.getCarga(), carga.getAluno().getId(), 
            carga.getExercicio().getId(), carga.getData()});

        return carga;
    }

    public List<Carga> obterCargasPorAluno(int aluno){
        return jbdcTemplate.query(SELECT_CARGA_ALUNO, new RowMapper<Carga>(){

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
                carga.setData(rs.getString("data"));

                return carga;

            }
        }, aluno);
    }

    public Carga obterCargaPorAlunoPorExercicio(int carga, int exercicio) {
       
        return jbdcTemplate.queryForObject(SELECT_CARGA_ALUNO_EXERCICIO, new Object[] {carga, exercicio}, new RowMapper<Carga>() {
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
                carga.setData(rs.getString("data"));

                return carga;
            }
        });

    }

    
}
