package ar.antonella.forohub.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.antonella.forohub.models.Topico;
import ar.antonella.forohub.models.dto.TopicoDTO;
import ar.antonella.forohub.services.ITopicoService;
import ar.antonella.forohub.utils.SortType;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final ITopicoService service;


    @PostMapping
    public ResponseEntity<Topico> save(@Valid @RequestBody TopicoDTO topico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(topico));
    }

    @PutMapping("{id}")
    public ResponseEntity<Topico> update(@PathVariable Long id, @RequestBody TopicoDTO topico) {
        return ResponseEntity.ok().body(service.update(id,topico));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Topico>> findAll(@RequestParam Integer page,
                                                @RequestParam Integer size,
                                                @RequestParam(required = false) SortType sortType) {
        if (sortType == null) {
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok().body(service.findAll(page, size, sortType));
    }


    @GetMapping("{id}")
    public ResponseEntity<Topico> readById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
