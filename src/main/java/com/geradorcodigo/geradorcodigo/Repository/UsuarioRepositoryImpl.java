package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;
import com.geradorcodigo.geradorcodigo.Model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

    private static String SELECT_USUARIO = "select * from mc_usuario where login = ? and senha = ?";
    private static String INSERT = " insert into mc_usuario (id, login, senha, pessoa, foto) "
            + " values (nextval('mc_usuario_id_seq'), ?, ?, ?, ?) ";
    private static String UPDATE = " update mc_usuario set foto = ? where pessoa = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private PessoaRepository pessoaRepo;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Usuario salvarUsuario(Usuario usuario) {

        jbdcTemplate.update(INSERT, new Object[] {usuario.getLogin(), usuario.getSenha(), usuario.getPessoa().getId(), usuario.getFoto()});

        return usuario;
    }

    public Usuario atualizarUsuario(Usuario usuario) {

        jbdcTemplate.update(UPDATE, new Object[] {usuario.getFoto(), usuario.getPessoa().getId()});

        return usuario;
    }

    public List<Usuario> obterUsuario(String login, String senha){
        return jbdcTemplate.query(SELECT_USUARIO, new RowMapper<Usuario>(){

            @Override
            public Usuario mapRow(ResultSet rs, int rownumber) throws SQLException{

                Usuario usuario = new Usuario();
                Pessoa pessoa = new Pessoa();

                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFoto(rs.getString("foto"));

                if(rs.getInt("pessoa") == 0){
                    usuario.setPessoa(null);
                }else{
                    pessoa = pessoaRepo.obterPessoaPorId(rs.getInt("pessoa"));
                    usuario.setPessoa(pessoa);
                }
                
                return usuario;

            }
        }, login, senha);
    }

    
}
