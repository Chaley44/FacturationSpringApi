package com.codeinfini.facturation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.codeinfini.facturation.model.Produit;
import com.codeinfini.facturation.repository.ProduitRepository;

@Service
public class ProduitServiceImp implements ProduitService {

	@Autowired
	ProduitRepository repo;
	
	
//	public Page<Produit> findPaginated(PageRequest pageable){
//		int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<Produit> listProduit;
//        String key = "";
//		
//        if (listProduit(key).size() < startItem) {
//        	listProduit = Collections.emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, listProduit(key).size());
//            listProduit = listProduit(key).subList(startItem, toIndex);
//        }
//
//        Page<Produit> bookPage
//          = new PageImpl<Produit>(listProduit, PageRequest.of(currentPage, pageSize),
//        		  listProduit(key).size());
//
//        return bookPage;
//
//		
//	}
	@Override
	public Page<Produit> findPaginated(String keyword, int pageNumber, int pageSize)
	{

		PageRequest pageable = PageRequest.of(pageNumber-1, pageSize);
		
		if(keyword != null) {
			Page<Produit> produits = new PageImpl<>(listProduit(keyword));
			return produits;
		}
		return repo.findAll(pageable);
	}
	@Override
	public List<Produit> listProduit(String key) {
		if(key != null) {
			return repo.findByKeyword(key);
		}
		return repo.findAll();
	}

	@Override
	public void ajouter(Produit produit) {
		repo.save(produit);

	}

	@Override
	public Optional<Produit> getById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void supprimer(Produit produit) {
		repo.delete(produit);

	}

	@Override
	public List<Produit> produits() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	

}
