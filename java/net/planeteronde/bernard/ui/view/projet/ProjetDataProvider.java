package net.planeteronde.bernard.ui.view.projet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import net.planeteronde.bernard.backend.data.entity.Projet;
import net.planeteronde.bernard.backend.service.ProjetService;

@SpringComponent
@PrototypeScope
public class ProjetDataProvider extends FilterablePageableDataProvider<Projet, Object> {

	private final ProjetService projetService;

	@Autowired
	public ProjetDataProvider(ProjetService productService) {
		this.projetService = productService;
	}

	@Override
	protected Page<Projet> fetchFromBackEnd(Query<Projet, Object> query, Pageable pageable) {
		return projetService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Projet, Object> query) {
		return (int) projetService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("nom", SortDirection.ASCENDING));
		return sortOrders;
	}

}