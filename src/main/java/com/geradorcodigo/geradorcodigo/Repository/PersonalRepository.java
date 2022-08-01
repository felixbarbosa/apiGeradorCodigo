package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Personal;

public interface PersonalRepository {
    
    List<Personal> obterTodosPersonais();

    Personal salvarPersonal(Personal personal);

    Personal obterPersonalPorId(int id);

    Personal atualizarPersonal(Personal personal);
}
