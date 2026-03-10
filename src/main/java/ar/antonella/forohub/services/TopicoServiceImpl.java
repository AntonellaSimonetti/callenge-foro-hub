package ar.antonella.forohub.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ar.antonella.forohub.models.Topico;
import ar.antonella.forohub.models.dto.TopicoDTO;
import ar.antonella.forohub.repositories.TopicoRepository;
import ar.antonella.forohub.utils.SortType;
import ar.antonella.forohub.utils.exceptions.TopicoNotFoundException;


@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements ITopicoService {

    private final TopicoRepository repository;


    @Override
    @Transactional
    public Page<Topico> findAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
        }
        return repository.findAll(pageRequest);
    }

    @Override
    public Topico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("No existe este topico con id " + id));
    }

    @Override
    @Transactional
    public Topico save(TopicoDTO topico) {
        var topicoNuevo = new Topico(null
                ,topico.titulo()
                ,topico.mensaje()
                ,topico.fechaCreacion()
                ,topico.autor()
                ,topico.curso());

        return repository.save(topicoNuevo);
    }

    @Override
    @Transactional
    public Topico update(Long id, TopicoDTO topico) {

        var topicoUpdate = repository.findById(id).
                orElseThrow(() -> new TopicoNotFoundException("No existe este topico con id " + id));

        topicoUpdate.setAutor(topico.autor());
        topicoUpdate.setTitulo(topico.titulo());
        topicoUpdate.setCurso(topico.curso());
        topicoUpdate.setMensaje(topico.mensaje());
        return repository.save(topicoUpdate);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new TopicoNotFoundException("No existe este topico " + id);
        }
        repository.deleteById(id);
    }
}
