package net.planeteronde.bernard.ui.view.projet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueContext;
import com.vaadin.spring.annotation.SpringView;

import net.planeteronde.bernard.backend.data.entity.Projet;
import net.planeteronde.bernard.ui.util.DollarPriceConverter;
import net.planeteronde.bernard.ui.view.admin.AbstractCrudView;
import net.planeteronde.bernard.ui.view.projet.ProjetViewDesign;
import net.planeteronde.bernard.ui.view.signaletique.SignaletiqueView;
import net.planeteronde.bernard.ui.navigation.NavigationManager;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

@SpringView
public class ProjetView extends AbstractCrudView<Projet> {

	private final ProjetPresenter presenter;

	private final ProjetViewDesign projetViewDesign;

	
	
	

	private NavigationManager navigationManager;

	private static final String PRICE_PROPERTY = "montant";

	@Autowired
	public ProjetView(ProjetPresenter presenter, NavigationManager navigationManager) {
		this.presenter = presenter;
	
		projetViewDesign = new ProjetViewDesign();
		this.navigationManager = navigationManager;
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		// Show three columns: "nom", "filiale" and "montant".
		getGrid().setColumns("nom", "filiale","loB","subLoB","client","typeOuvrage");
		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
	
		projetViewDesign.list.addSelectionListener(e -> selectedProjet(e.getFirstSelectedItem().get()));
		projetViewDesign.add.addClickListener(e -> newProjet());
	
		
	}
	
	public void selectedProjet(Projet projet) {
		navigationManager.navigateTo(SignaletiqueView.class, projet.getId());
	}
	public void newProjet() {
		navigationManager.navigateTo(SignaletiqueView.class);
	}
	

	@Override
	public ProjetViewDesign getViewComponent() {
		return projetViewDesign;
	}

	@Override
	protected ProjetPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Projet> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<Projet> grid) {
		getViewComponent().list = grid;
	}

	@Override
	protected Component getForm() {
		return getViewComponent().form;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}

	@Override
	protected Button getDelete() {
		return getViewComponent().delete;
	}

	@Override
	protected Button getUpdate() {
		return getViewComponent().update;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().nom;
	}

	@Override
	public void bindFormFields(Binder<Projet> binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Projet> binder) {
		// TODO Auto-generated method stub
		
	}

}