package com.geradorcodigo.geradorcodigo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.geradorcodigo.geradorcodigo.Model.Entregador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EntregadorRepositoryImpl implements EntregadorRepository{

    private static String SELECT_ENTREGAS_PENDENTES = " select * from entregador where visto = 0 and data = " +
    "?";
    private static String SELECT_ENTREGAS_REALIZADAS = " select * from entregador where visto = 1 ";
    private static String INSERT = " insert into entregador (data, hora, turno, local, codigo, visto) "
            + " values (?, ?, ?, ?, ?, ?) ";
    private static String UPDATE = " update entregador set visto = ? where id = ?";  

    @Autowired
    private JdbcTemplate jbdcTemplate;

    
    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Entregador salvar(Entregador entregador) {

        jbdcTemplate.update(INSERT, new Object[] { entregador.getData(), entregador.getHora() != null ? entregador.getHora() : null, entregador.getTurno() != null ? entregador.getTurno() : null,
        entregador.getLocal(), entregador.getCodigo(), 0});

        return entregador;
    }

    public Entregador atualizar(Entregador entregador) {
    
        jbdcTemplate.update(UPDATE, new Object[] { entregador.getVisto(), entregador.getId()});

        return entregador; 
    }

    public List<Entregador> obterEntregasPendentes(){

        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());

        System.out.println("Data = " + dataSQL.toString());
        return jbdcTemplate.query(SELECT_ENTREGAS_PENDENTES, new RowMapper<Entregador>(){

            @Override
            public Entregador mapRow(ResultSet rs, int rownumber) throws SQLException{

                Entregador entregador = new Entregador();
                entregador.setId(rs.getInt("id"));
                entregador.setData(rs.getString("data"));
                entregador.setCodigo(rs.getString("codigo"));
                entregador.setHora(rs.getString("hora"));
                entregador.setLocal(rs.getString("local"));
                entregador.setTurno(rs.getString("turno"));
                entregador.setVisto(rs.getInt("visto"));
                
                return entregador;

            }
        }, dataSQL.toString());
    }

    public List<Entregador> obterEntregasRealizadas(){

        return jbdcTemplate.query(SELECT_ENTREGAS_REALIZADAS, new RowMapper<Entregador>(){

            @Override
            public Entregador mapRow(ResultSet rs, int rownumber) throws SQLException{

                Entregador entregador = new Entregador();
                entregador.setId(rs.getInt("id"));
                entregador.setData(rs.getString("data"));
                entregador.setCodigo(rs.getString("codigo"));
                entregador.setHora(rs.getString("hora"));
                entregador.setLocal(rs.getString("local"));
                entregador.setTurno(rs.getString("turno"));
                entregador.setVisto(rs.getInt("visto"));
                
                return entregador;

            }
        });
    }
     
}
