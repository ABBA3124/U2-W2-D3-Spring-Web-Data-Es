package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.controllers;

import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.Autore;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("autori")
public class AutoreController {


    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public List<Autore> getAllAuthors() {
        return autoreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autore> getAuthorById(@PathVariable UUID id) {
        Optional<Autore> author = autoreService.findById(id);
        return author.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Autore createAuthor(@RequestBody Autore autore) {
        autore.setAvatar("https://lui-avatars.com/api?name=" + autore.getNome() + "+" + autore.getCognome());
        return autoreService.save(autore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autore> updateAuthor(@PathVariable UUID id, @RequestBody Autore autore) {
        if (!autoreService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        autore.setId(id);
        return ResponseEntity.ok(autoreService.save(autore));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        if (!autoreService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        autoreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
