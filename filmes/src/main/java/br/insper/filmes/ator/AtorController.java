package br.insper.filmes.ator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    public ResponseEntity<Ator> criarAtor(@Validated @RequestBody Ator ator) {
        Ator novoAtor = atorService.criarAtor(ator);
        return ResponseEntity.ok(novoAtor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarAtorPorId(@PathVariable("id") String id) {
        Optional<Ator> ator = atorService.buscarAtorPorId(id);
        return ator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Ator>> listarTodosAtores() {
        return ResponseEntity.ok(atorService.listarTodosAtores());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable("id") String id, @Validated @RequestBody Ator ator) {
        Optional<Ator> atorAtualizado = atorService.atualizarAtor(id, ator);
        return atorAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable("id") String id) {
        if (atorService.deletarAtor(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}