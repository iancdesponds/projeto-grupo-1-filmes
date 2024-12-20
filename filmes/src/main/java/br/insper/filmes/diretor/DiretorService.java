package br.insper.filmes.diretor;

import br.insper.filmes.ator.Ator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    public Diretor criarDiretor(Diretor diretor) {
        validarDiretor(diretor);
        diretor.setId(UUID.randomUUID().toString());
        return diretorRepository.save(diretor);
    }

    public Optional<Diretor> buscarDiretorPorId(String id) {
        if (id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id invalido");
        }
        return diretorRepository.findById(id);
    }

    public List<Diretor> listarTodos() {
        return diretorRepository.findAll();
    }

    public Optional<Diretor> atualizarDiretor(String id, Diretor diretor) {
        validarDiretor(diretor);
        if (diretorRepository.existsById(id)) {
            diretor.setId(id);
            return Optional.of(diretorRepository.save(diretor));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletarDiretor(String id) {
        if (id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id invalido");
        }
        if (diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validarDiretor(Diretor diretor) {
        if (!StringUtils.hasText(diretor.getNome()) || diretor.getNome().length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome inválido ou muito longo.");
        }
        if (!StringUtils.hasText(diretor.getNacionalidade()) || diretor.getNacionalidade().length() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nacionalidade inválida ou muito longa.");
        }

    }
}
