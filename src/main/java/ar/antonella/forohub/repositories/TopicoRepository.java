package ar.antonella.forohub.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ar.antonella.forohub.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
