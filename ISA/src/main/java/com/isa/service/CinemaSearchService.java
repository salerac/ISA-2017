package com.isa.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.isa.domain.Cinema;
@Primary
@Service
public class CinemaSearchService {
	
	@Autowired
	private final EntityManager centityManager;
	
	@Autowired
	public CinemaSearchService(EntityManager entityManager) {
		super();
		this.centityManager = entityManager;
	}
	
	public void initializeHibernateSearch() {
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Cinema> fuzzySearch(String searchTerm){
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Cinema.class).get();
		Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name").matching(searchTerm).createQuery();
		
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Cinema.class);
		
		
		List<Cinema> cinemas = null;
		cinemas = jpaQuery.getResultList();
		return cinemas;
				
		
	}

}
