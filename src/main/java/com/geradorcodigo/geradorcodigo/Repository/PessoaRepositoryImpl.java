package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository{

    private static String SELECT_PESSOA_ID = "select * from mc_pessoa where id = " +
    "?";
    private static String INSERT = " insert into mc_pessoa (nome, email, cref, cpf, aluno, personal) "
            + " values (?, ?, ?, ?, ?, ?) ";
    //private static String UPDATE = " update mc_aluno set nome = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private PersonalRepository personalRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {

        jbdcTemplate.update(INSERT, new Object[] {pessoa.getNome(), pessoa.getEmail(), pessoa.getCref(), 
            pessoa.getCpf(), pessoa.getAluno().getId(), pessoa.getPersonal().getId()});

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

                personal = personalRepo.obterPersonalPorId(rs.getInt("personal"));
                pessoa.setPersonal(personal);

                aluno = alunoRepo.obterAlunoPorId(rs.getInt("aluno"));
                pessoa.setAluno(aluno);

                return pessoa;
            }
        });

    }

    
}
