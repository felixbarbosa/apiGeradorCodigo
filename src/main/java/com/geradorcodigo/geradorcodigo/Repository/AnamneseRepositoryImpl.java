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

import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Anamnese;

@Repository
public class AnamneseRepositoryImpl implements AnamneseRepository{

    private static String SELECT_anamnese_ALUNO = "select * from mc_anamnese where aluno = " +
    "?";
    
    private static String INSERT = " insert into mc_anamnese (objetivo, atividadeFisica, refeicoes, dieta, suplementacao," +
    " sono, fumante, bebidaalcoolica, colesterol, alteracaocardiaca, diabetes, hipertenso," +
    " pulmonar, medicamento, cirurgia, dores, problemaortopedico, observacoes, aluno) "
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    
    private static String REMOVE = "delete from mc_anamnese where aluno = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private AlunoRepository alunoRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Anamnese salvarAnamnese(Anamnese anamnese) {

        jbdcTemplate.update(INSERT, new Object[] {
            anamnese.getObjetivo(), anamnese.getAtividadeFisica(), anamnese.getRefeicoes(), anamnese.getDieta(),
            anamnese.getSuplementacao(), anamnese.getSono(), anamnese.getFumante(), anamnese.getBebidaAlcoolica(),
            anamnese.getColesterol(), anamnese.getAlteracaoCardiaca(), anamnese.getDiabetes(), anamnese.getHipertenso(),
            anamnese.getPulmonar(), anamnese.getMedicamento(), anamnese.getCirurgia(), anamnese.getDores(),
            anamnese.getProblemaOrtopedico(), anamnese.getObservacoes(), anamnese.getAluno().getId()
        });

        return anamnese;
    }

    public Boolean deletarAnamnesePorAluno(int alunoId) {

        Boolean ret = false;
        
        Integer rows = jbdcTemplate.update(REMOVE, new Object[] {alunoId});


        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    public List<Anamnese> obterAnamnesePorAluno(int alunoId){

        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());

        System.out.println("Data = " + dataSQL.toString());

        return jbdcTemplate.query(SELECT_anamnese_ALUNO, new RowMapper<Anamnese>(){

            @Override
            public Anamnese mapRow(ResultSet rs, int rownumber) throws SQLException{

                Anamnese anamnese = new Anamnese();

                Aluno aluno = new Aluno();

                anamnese.setId(rs.getInt("id"));
                anamnese.setObjetivo(rs.getString("objetivo"));
                anamnese.setAtividadeFisica(rs.getString("atividadefisica"));
                anamnese.setRefeicoes(rs.getString("refeicoes"));
                anamnese.setDieta(rs.getString("dieta"));
                anamnese.setSuplementacao(rs.getString("suplementacao"));
                anamnese.setSono(rs.getString("sono"));
                anamnese.setFumante(rs.getString("fumante"));
                anamnese.setBebidaAlcoolica(rs.getString("bebidaalcoolica"));
                anamnese.setColesterol(rs.getString("colesterol"));
                anamnese.setAlteracaoCardiaca(rs.getString("alteracaocardiaca"));
                anamnese.setDiabetes(rs.getString("diabetes"));
                anamnese.setHipertenso(rs.getString("hipertenso"));
                anamnese.setPulmonar(rs.getString("pulmonar"));
                anamnese.setMedicamento(rs.getString("medicamento"));
                anamnese.setCirurgia(rs.getString("cirurgia"));
                anamnese.setDores(rs.getString("dores"));
                anamnese.setProblemaOrtopedico(rs.getString("problemaortopedico"));
                anamnese.setObservacoes(rs.getString("observacoes"));

                aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                anamnese.setAluno(aluno);
                
                return anamnese;

            }
        }, alunoId);
    }
     
}
