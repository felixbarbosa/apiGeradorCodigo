package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Musculo;

public interface MusculoRepository {

    Musculo obterMusculoPorId(int id);
    
    List<Musculo> obterMusculos();
}
