package com.uninter.api_tarefas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // O Spring Data JPA criará automaticamente os métodos básicos de CRUD (save, findAll, findById, deleteById)
    // Não precisamos escrever nenhum código aqui!
}