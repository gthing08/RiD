package net.planeteronde.bernard.ui.view.projet;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import net.planeteronde.bernard.backend.data.entity.Projet;
import net.planeteronde.bernard.backend.service.ProjetService;
import net.planeteronde.bernard.ui.navigation.NavigationManager;
import net.planeteronde.bernard.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class ProjetPresenter extends AbstractCrudPresenter<Projet, ProjetService, ProjetView> {

	@Autowired
	public ProjetPresenter(ProjetDataProvider dataProvider, NavigationManager navigationManager,
			ProjetService service, BeanFactory beanFactory) {
		super(navigationManager, service, Projet.class, dataProvider, beanFactory);
	}
}
