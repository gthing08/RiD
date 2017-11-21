package net.planeteronde.bernard.backend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.planeteronde.bernard.backend.ProjetRepository;
import net.planeteronde.bernard.backend.data.entity.Projet;
import net.planeteronde.bernard.backend.data.entity.User;

@Service
public class ProjetService extends CrudService<Projet> {

	private final ProjetRepository projetRepository;
	

	@Autowired
	public ProjetService(ProjetRepository projetRepository) {
		this.projetRepository = projetRepository;
		
	}
	
	public Projet findProjet(Long id) {
		return projetRepository.findOne(id);
	}
	
	
//requete vers la BDD 
	@Override
	public Page<Projet> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNomLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNomLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	@Override
	protected ProjetRepository getRepository() {
		return projetRepository;
	}
	
	//commande de sauvegarde
		@Transactional(rollbackOn = Exception.class)
		public Projet saveProjet(Projet projet, User user) {
			//changement d'état et implémentation de l'historique de la commande
			return projetRepository.save(projet);
		}
		
		
	

}
