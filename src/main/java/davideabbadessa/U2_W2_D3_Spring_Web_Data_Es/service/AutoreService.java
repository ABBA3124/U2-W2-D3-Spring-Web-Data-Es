package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.service;

import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.Autore;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.repositories.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public List<Autore> findAll() {
        return autoreRepository.findAll();
    }

    public Optional<Autore> findById(UUID id) {
        return autoreRepository.findById(id);
    }

    public Autore save(Autore autore) {
        return autoreRepository.save(autore);
    }

    public void deleteById(UUID id) {
        autoreRepository.deleteById(id);
    }
}
