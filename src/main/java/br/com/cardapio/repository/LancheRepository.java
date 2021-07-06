package br.com.cardapio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.entity.Lanche;

@Repository
public interface LancheRepository extends CrudRepository<Lanche, Long> {
	
	List<Lanche> findByLancheContaining(String lanche);
}
