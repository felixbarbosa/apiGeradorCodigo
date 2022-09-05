package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository{

    private static String SELECT_PESSOA_ID = "select * from mc_pessoa where id = " +
    "?";
    private static String INSERT = " insert into mc_pessoa (id, nome, email, cref, cpf, aluno, personal) "
            + " values (nextval('mc_pessoa_id_seq'), ?, ?, ?, ?, ?, ?) ";
    //private static String UPDATE = " update mc_aluno set nome = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private PersonalRepository personalRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    Integer alunoId = 0;
    Integer professorId = 0;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int idGerado = 0;

        if(pessoa.getAluno() == null || pessoa.getAluno().getId() == 0){
            System.out.println("Aluno é nulo");
            alunoId = null;
            professorId = pessoa.getPersonal().getId();
        }else if(pessoa.getPersonal() == null || pessoa.getPersonal().getId() == 0){
            System.out.println("Professor é nulo");
            professorId = null;
            alunoId = pessoa.getAluno().getId();
        }else{
            alunoId = pessoa.getAluno().getId();
            professorId = pessoa.getPersonal().getId();
        }

        /*jbdcTemplate.update(INSERT, new Object[] {pessoa.getNome(), pessoa.getEmail(), pessoa.getCref(), 
            pessoa.getCpf(), alunoId, professorId});*/

        jbdcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
                ps.setString(1, pessoa.getNome());
                ps.setString(2, pessoa.getEmail());
                ps.setString(3, pessoa.getCref());
                ps.setString(4, pessoa.getCpf());
                ps.setObject(5, alunoId);
                //ps.setInt(6, professorId);
                ps.setObject(6, professorId);
                return ps;
            }, keyHolder);

        idGerado = keyHolder.getKey().intValue();

        pessoa.setId(idGerado);

        return pessoa;
    }

    public Pessoa obterPessoa(int id) {
       
        return jbdcTemplate.queryForObject(SELECT_PESSOA_ID, new Object[] {id}, new RowMapper<Pessoa>() {
            @Override
            public Pessoa mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Pessoa pessoa = new Pessoa();
                Aluno aluno = new Aluno();
                Personal personal = new Personal();

                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCref(rs.getString("cref"));
                pessoa.setCpf(rs.getString("cpf"));

                if(rs.getInt("personal") == 0){
                    pessoa.setPersonal(null);
                }else{
                    personal = personalRepo.obterPersonalPorId(rs.getInt("personal"));
                    pessoa.setPersonal(personal);
                }
                
                if(rs.getInt("aluno") == 0){
                    pessoa.setAluno(null);
                }else{
                    aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                    pessoa.setAluno(aluno);
                }

                

                return pessoa;
            }
        });

    }

    
}
