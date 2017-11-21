package net.planeteronde.bernard.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.planeteronde.bernard.backend.data.entity.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

	Page<Projet> findByNomLikeIgnoreCase(String nom, Pageable page);

	int countByNomLikeIgnoreCase(String nom);

}
