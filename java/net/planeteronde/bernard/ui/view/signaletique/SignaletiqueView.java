package net.planeteronde.bernard.ui.view.signaletique;

import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import net.planeteronde.bernard.backend.data.entity.Projet;
import net.planeteronde.bernard.backend.data.entity.Projet.Filiale;
import net.planeteronde.bernard.ui.components.ConfirmPopup;
import com.vaadin.ui.Label;

@SpringView(name = "signaletique")
public class SignaletiqueView extends SignaletiqueViewDesign implements View {
	
	public enum Mode {
		EDITION, CONSULTATION;
	}

	private final SignaletiquePresenter presenter;
	private Binder<Projet> binder = new Binder<>(Projet.class);
	private boolean hasChanges;
	private final BeanFactory beanFactory;
	private Projet projet;
	private Mode mode;

	@Autowired
	public SignaletiqueView(BeanFactory beanFactory, SignaletiquePresenter presenter) {
		this.beanFactory = beanFactory;
		this.presenter = presenter;
		
	}

	@PostConstruct
	public void init() {
		presenter.init(this);


		// Binder takes care of binding Vaadin fields defined as Java member fields in this class to properties in the Order bean
	
		// Almost all fields are required, so we don't want to display indicators
	
		// Bindings are done in the order the fields appear on the screen as we report validation errors for the first invalid field and it is most
		// intuitive for the user that we start from the top if there are multiple errors.
		binder.bindInstanceFields(this);
		// Track changes manually as we use setBean and nested binders
		binder.addValueChangeListener(e -> hasChanges = true);
		
		
		
		
    /*    binder.bind(filiale, Projet::getFiliale,Projet::setFiliale);
		binder.bind(client, Projet::getClient,Projet::setClient) ;
		binder.bind(lob, Projet::getLoB,Projet::setLoB) ;
		binder.bind(subLob, Projet::getSubLoB,Projet::setSubLoB) ;
		binder.bind(metier, Projet::getMetier,Projet::setMetier) ;
		binder.bind(typeOuvrage, Projet::getTypeOuvrage,Projet::setTypeOuvrage) ;
		*/
	
		filiale.setItems(Projet.Filiale.values());
		client.setItems(Projet.Client.values());
		metier.setItems(Projet.Metier.values());
		lob.setItems(Projet.LoB.values());
		subLob.setItems(Projet.SubLoB.values());
		typeOuvrage.setItems(Projet.TypeOuvrage.values());
		
		

		//actions 
	
		//éditer Retour ou ANnuler
	annuler.addClickListener(e -> presenter.backCancel());
		//sauvegarder ou supprimer
	enregistrer.addClickListener(e -> presenter.editSave());

	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		String projetId = event.getParameters();
		if ("".equals(projetId)) {
			presenter.enterView(null);
		} else {
			presenter.enterView(Long.valueOf(projetId));
		}
	}
	
	void setProjet(Projet projet) {
		this.projet = projet;
		binder.setBean(projet);
		hasChanges = false;
		
	}

	protected Projet getProjet() {
		return binder.getBean();
	}
	

	public void showNotFound() {
		removeAllComponents();
		addComponent(new Label("Projet not found"));
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
		binder.setReadOnly(mode != Mode.EDITION);
		if (mode == Mode.CONSULTATION) {
			annuler.setCaption("Retour");
			annuler.setIcon(VaadinIcons.CLOSE);
			enregistrer.setCaption("Editer");
			enregistrer.setVisible(true);
		} else if (mode == Mode.EDITION) {
			annuler.setCaption("annuler");
			annuler.setIcon(VaadinIcons.CLOSE);
			enregistrer.setCaption("Enregistrer");
			enregistrer.setVisible(true);
		} else {
			throw new IllegalArgumentException("Unknown mode " + mode);
		}
	}
	
	public Mode getMode() {
		return mode;
	}
	
	
	
	public Stream<HasValue<?>> validate() {
		Stream<HasValue<?>> errorFields = binder.validate().getFieldValidationErrors().stream()
				.map(BindingValidationStatus::getField);
		return errorFields;
	}
	
	
	

	
	
	
	@Override
	public void beforeLeave(ViewBeforeLeaveEvent event) {
		if (!containsUnsavedChanges()) {
			event.navigate();
		} else {
			ConfirmPopup confirmPopup = beanFactory.getBean(ConfirmPopup.class);
			confirmPopup.showLeaveViewConfirmDialog(this, event::navigate);
		}
	}
	public void onProductInfoChanged() {
		hasChanges = true;
	}
	public boolean containsUnsavedChanges() {
		return hasChanges;
	}
	
	
	
	
	

	
}
	
	