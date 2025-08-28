package com.uninter.api_tarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas") // Define o prefixo da URL para todos os endpoints
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    // CREATE - Criar uma nova tarefa (POST)
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // READ - Listar todas as tarefas (GET)
    @GetMapping
    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    // READ - Consultar uma tarefa específica pelo ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> consultarTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get()); // Retorna 200 OK com a tarefa
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    // UPDATE - Atualizar uma tarefa existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetalhes) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setNome(tarefaDetalhes.getNome());
            tarefa.setDataEntrega(tarefaDetalhes.getDataEntrega());
            tarefa.setResponsavel(tarefaDetalhes.getResponsavel());

            Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Remover uma tarefa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTarefa(@PathVariable Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.ok().build(); // Retorna 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}