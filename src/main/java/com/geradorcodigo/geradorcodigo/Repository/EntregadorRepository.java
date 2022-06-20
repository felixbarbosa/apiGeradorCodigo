package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Entregador;

public interface EntregadorRepository {
    
    List<Entregador> obterTodos();

    Entregador salvar(Entregador entregador);

    Entregador atualizar(Entregador entregador);
}
