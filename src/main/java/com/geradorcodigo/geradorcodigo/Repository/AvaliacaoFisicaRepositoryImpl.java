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
import com.geradorcodigo.geradorcodigo.Model.AvaliacaoFisica;

@Repository
public class AvaliacaoFisicaRepositoryImpl implements AvaliacaoFisicaRepository{

    private static String SELECT_AVALIACAO_ALUNO = "select * from mc_avaliacao_fisica where aluno = " +
    "?";
    
    private static String INSERT = " insert into mc_avaliacao_fisica (aluno, objetivo, idade, data, altura," +
    " peso, peitoral, biceps, antebraco, cintura, abdome, quadril, coxa, foto_frente, foto_lado, foto_costas) "
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    //private static String UPDATE = " update mc_aluno set nome = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private AlunoRepository alunoRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public AvaliacaoFisica salvarAvaliacao(AvaliacaoFisica avaliacao) {

        jbdcTemplate.update(INSERT, new Object[] {
            avaliacao.getAluno().getId(), avaliacao.getObjetivo(), avaliacao.getIdate(), avaliacao.getData(),
            avaliacao.getAltura(), avaliacao.getPeso(), avaliacao.getPeitoral(), avaliacao.getBiceps(),
            avaliacao.getAnteBraco(), avaliacao.getCintura(), avaliacao.getAbdome(), avaliacao.getQuadril(),
            avaliacao.getCoxa(), avaliacao.getFotoFrente(), avaliacao.getFotoLado(), avaliacao.getFotoCostas()
        });

        return avaliacao;
    }

    public List<AvaliacaoFisica> obterAvaliacaoPorAluno(int alunoId){

        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());

        System.out.println("Data = " + dataSQL.toString());

        return jbdcTemplate.query(SELECT_AVALIACAO_ALUNO, new RowMapper<AvaliacaoFisica>(){

            @Override
            public AvaliacaoFisica mapRow(ResultSet rs, int rownumber) throws SQLException{

                AvaliacaoFisica avaliacao = new AvaliacaoFisica();

                Aluno aluno = new Aluno();

                aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                avaliacao.setAluno(aluno);

                avaliacao.setAbdome(rs.getString("abdome"));
                avaliacao.setAltura(rs.getString("altura"));
                avaliacao.setAnteBraco(rs.getString("antebraco"));
                avaliacao.setBiceps(rs.getString("biceps"));
                avaliacao.setCintura(rs.getString("cintura"));
                avaliacao.setCoxa(rs.getString("coxa"));
                avaliacao.setData(rs.getString("data"));
                avaliacao.setFotoCostas(rs.getString("foto_costas"));
                avaliacao.setFotoFrente(rs.getString("foto_frente"));
                avaliacao.setFotoLado(rs.getString("foto_lado"));
                avaliacao.setId(rs.getInt("id"));
                avaliacao.setIdate(rs.getInt("idade"));
                avaliacao.setObjetivo(rs.getString("objetivo"));
                avaliacao.setPeitoral(rs.getString("peitoral"));
                avaliacao.setPeso(rs.getDouble("peso"));
                avaliacao.setQuadril(rs.getString("quadril"));
                
                return avaliacao;

            }
        }, alunoId);
    }
     
}
