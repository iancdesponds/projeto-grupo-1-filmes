package br.insper.filmes.diretor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @PostMapping
    public ResponseEntity<Diretor> criarDiretor(@Validated @RequestBody Diretor diretor) {
        Diretor novoDiretor = diretorService.criarDiretor(diretor);
        return ResponseEntity.ok(novoDiretor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarDiretorPorId(@PathVariable("id") Integer id) {
        Optional<Diretor> diretor = diretorService.buscarDiretorPorId(id);
        return diretor.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Diretor>> listarTodos() {
        return ResponseEntity.ok(diretorService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable("id") Integer id, @Validated @RequestBody Diretor diretor) {
        Optional<Diretor> diretorAtualizado = diretorService.atualizarDiretor(id, diretor);
        return diretorAtualizado.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable("id") Integer id) {
        if (diretorService.deletarDiretor(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
