package ar.antonella.forohub.services;

import org.springframework.data.domain.Page;
import ar.antonella.forohub.models.Topico;
import ar.antonella.forohub.models.dto.TopicoDTO;
import ar.antonella.forohub.utils.SortType;

public interface ITopicoService {

    String FIELD_BY_SORT = "fechaCreacion";

    Page<Topico> findAll(Integer page, Integer size, SortType sortType);

    Topico findById(Long id);


    Topico save(TopicoDTO topico);

    Topico update(Long id, TopicoDTO topico);


    void deleteById(Long id);

}
