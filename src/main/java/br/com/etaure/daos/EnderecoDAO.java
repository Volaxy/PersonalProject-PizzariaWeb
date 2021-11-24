package br.com.etaure.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.etaure.entities.Endereco;

public class EnderecoDAO {

	private static EntityManager em;

	private void createEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Pizzaria Web");
			em = factory.createEntityManager();
		}
	}

	// Insere uma novo Endere�o
	public void insert(Endereco endereco) {
		createEntityManager();

		em.getTransaction().begin();
		em.persist(endereco);
		em.getTransaction().commit();
	}

	public List<Endereco> findAll() {
		createEntityManager();

		String jpql = "SELECT e FROM Endereco e";
		return em.createQuery(jpql, Endereco.class).getResultList();
	}

	public Endereco findById(Integer id) {
		createEntityManager();

		return em.find(Endereco.class, id);
	}

	// Atualiza a Endereco
	public void updateEndereco(Integer id, Endereco newEndereco) {
		createEntityManager();

		Endereco oldEndereco = em.find(Endereco.class, id);

		em.getTransaction().begin();

		oldEndereco.setCidade(newEndereco.getCidade());
		oldEndereco.setBairro(newEndereco.getBairro());
		oldEndereco.setRua(newEndereco.getRua());
		oldEndereco.setNumero(newEndereco.getNumero());
		oldEndereco.setLogradouro(newEndereco.getLogradouro());

		em.getTransaction().commit();
	}

	// Deleta a Endereco
	public void deleteEndereco(Integer id) {
		createEntityManager();

		Endereco endereco = em.find(Endereco.class, id);

		em.getTransaction().begin();

		em.remove(endereco);

		em.getTransaction().commit();
	}

}
