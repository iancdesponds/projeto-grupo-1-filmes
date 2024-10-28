package br.insper.filmes.diretor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    public Diretor criarDiretor(Diretor diretor) {
        validarDiretor(diretor);
        return diretorRepository.save(diretor);
    }

    public Optional<Diretor> buscarDiretorPorId(Integer id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return diretorRepository.findById(id);
    }

    public List<Diretor> listarTodos() {
        return diretorRepository.findAll();
    }

    public Optional<Diretor> atualizarDiretor(Integer id, Diretor diretor) {
        validarDiretor(diretor);
        if (diretorRepository.existsById(id)) {
            diretor.setId(id);
            return Optional.of(diretorRepository.save(diretor));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletarDiretor(Integer id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validarDiretor(Diretor diretor) {
        if (!StringUtils.hasText(diretor.getNome()) || diretor.getNome().length() > 100) {
            throw new IllegalArgumentException("Nome inválido ou muito longo.");
        }
        if (!StringUtils.hasText(diretor.getNacionalidade()) || diretor.getNacionalidade().length() > 50) {
            throw new IllegalArgumentException("Nacionalidade inválida ou muito longa.");
        }
        if (diretor.getFilmes() == null) {
            throw new IllegalArgumentException("A lista de filmes não pode ser nula.");
        }
    }
}
