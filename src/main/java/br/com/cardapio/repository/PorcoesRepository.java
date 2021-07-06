package br.com.cardapio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.entity.Porcoes;

@Repository
public interface PorcoesRepository extends CrudRepository<Porcoes, Long>{
	
	List<Porcoes> findByPorcaoContaining(String porcao);
}
