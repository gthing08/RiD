package net.planeteronde.bernard.ui.view.signaletique;

import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.vaadin.spring.events.EventBus.ViewEventBus;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import net.planeteronde.bernard.app.HasLogger;
import net.planeteronde.bernard.app.security.SecurityUtils;
import net.planeteronde.bernard.backend.data.entity.Projet;
import net.planeteronde.bernard.backend.service.ProjetService;
import net.planeteronde.bernard.backend.service.UserService;
import net.planeteronde.bernard.ui.navigation.NavigationManager;

import net.planeteronde.bernard.ui.view.projet.ProjetView;
import net.planeteronde.bernard.ui.view.signaletique.SignaletiqueView.Mode;
import net.planeteronde.bernard.ui.view.signaletique.SignaletiqueView;


@SpringComponent
@ViewScope
public class SignaletiquePresenter implements Serializable, HasLogger {
	
	private SignaletiqueView view;
	private final ProjetService projetService;
	private final NavigationManager navigationManager;
	private final UserService userService;


	@Autowired
	public SignaletiquePresenter(ViewEventBus viewEventBus, ProjetService projetService, NavigationManager navigationManager, UserService userService) 
	{
		
		this.navigationManager = navigationManager;
		this.projetService = projetService;
		this.userService = userService;
		
	}
	

	
	void init(SignaletiqueView view) {
		this.view = view;
	}
	
	
	
	//si commande existante appelle valeur sinon crée une commande
	public void enterView(Long id) {
		Projet projet;
		if (id == null) {
			// New
			projet = new Projet();
		
		} else {
			projet = projetService.findProjet(id);
			if (projet == null) {
				view.showNotFound();
				return;
			}
		}	
		refreshView(projet);
	}
	

	

	
	public void backCancel() {
		
		if (view.getMode() == Mode.EDITION) {
			view.setMode(Mode.CONSULTATION);
		} else if (view.getMode() == Mode.CONSULTATION) {
				navigationManager.navigateTo(ProjetView.class);
			} else {
				Long id = view.getProjet().getId();
				enterView(id);
			}
		}
	
	
	
	
	
	public void editSave() {
		
		 if (view.getMode() == Mode.CONSULTATION) {
			view.setMode(Mode.EDITION); 
		}
		
	else if (view.getMode() == Mode.EDITION) {
			Projet projet = saveProjet();
			if (projet != null) {
			// Navigate to edit view so URL is updated correctly
			navigationManager.updateViewParameter("" + projet.getId());
			enterView(projet.getId());
		}
		}
	}
		

	
	private Projet saveProjet() {
		try {
			
			Projet projet = view.getProjet();
			//ligne de sauvegarde
			return projetService.saveProjet(projet, SecurityUtils.getCurrentUser(userService));
		} catch (ValidationException e) {
			// Should not get here if validation is setup properly
			Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
			getLogger().error("Validation error during order save", e);
			return null;
		} catch (OptimisticLockingFailureException e) {
			// Somebody else probably edited the data at the same time
			Notification.show("Somebody else might have updated the data. Please refresh and try again.",
					Type.ERROR_MESSAGE);
			getLogger().debug("Optimistic locking error while saving order", e);
			return null;
		} catch (Exception e) {
			// Something went wrong, no idea what
			Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
					Type.ERROR_MESSAGE);
			getLogger().error("Unable to save projet", e);
			return null;
		}
	}
	
	
private void refresh(Long id) {
		Projet projet = projetService.findProjet(id);
		if (projet == null) {
			view.showNotFound();
			return;
		}
		refreshView(projet);
	}
	
	
	
	
	
	private void refreshView(Projet projet) {
		view.setProjet(projet);
		if (projet.getId() == null) {
			view.setMode(Mode.EDITION);
		} else {
			view.setMode(Mode.CONSULTATION);
		}
	}   

}




