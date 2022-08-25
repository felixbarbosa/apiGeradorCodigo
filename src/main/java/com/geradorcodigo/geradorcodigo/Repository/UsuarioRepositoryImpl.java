package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.geradorcodigo.geradorcodigo.Model.Objetivo;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;
import com.geradorcodigo.geradorcodigo.Model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

    private static String SELECT_ALUNO_ID = "select * from mc_usuario where login = ? and senha = ?" +
    "?";
    private static String INSERT = " insert into mc_usuario (login, senha, pessoa) "
            + " values (?, ?, ?) ";
    //private static String UPDATE = " update mc_aluno set nome = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private PessoaRepository pessoaRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Usuario salvarUsuario(Usuario usuario) {

        jbdcTemplate.update(INSERT, new Object[] {usuario.getLogin(), usuario.getSenha(), usuario.getPessoa().getId()});

        return usuario;
    }

    public Usuario obterUsuario(String login, String senha) {
       
        return jbdcTemplate.queryForObject(SELECT_ALUNO_ID, new Object[] {login, senha}, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Usuario usuario = new Usuario();
                Pessoa pessoa = new Pessoa();
                Objetivo objetivo = new Objetivo();

                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                pessoa = pessoaRepo.obterPessoa(rs.getInt("pessoa"));
                usuario.setPessoa(pessoa);

                return usuario;
            }
        });

    }

    
}
