package br.insper.filmes.ator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public Ator criarAtor(Ator ator) {
        validarAtor(ator);
        ator.setId(UUID.randomUUID().toString());
        return atorRepository.save(ator);
    }

    public Optional<Ator> buscarAtorPorId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return atorRepository.findById(id);
    }

    public List<Ator> listarTodosAtores() {
        return atorRepository.findAll();
    }

    public Optional<Ator> atualizarAtor(String id, Ator ator) {
        validarAtor(ator);
        if (atorRepository.existsById(id)) {
            ator.setId(id);
            return Optional.of(atorRepository.save(ator));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletarAtor(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (atorRepository.existsById(id)) {
            atorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validarAtor(Ator ator) {
        if (!StringUtils.hasText(ator.getNome()) || ator.getNome().length() > 100) {
            throw new IllegalArgumentException("Nome inválido ou muito longo.");
        }
        if (!StringUtils.hasText(ator.getNacionalidade()) || ator.getNacionalidade().length() > 50) {
            throw new IllegalArgumentException("Nacionalidade inválida ou muito longa.");
        }
        if (ator.getFilmes() == null) {
            throw new IllegalArgumentException("A lista de filmes não pode ser nula.");
        }
    }
}
