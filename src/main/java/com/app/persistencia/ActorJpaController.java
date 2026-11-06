package com.app.persistencia;

import com.app.logica.Actor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ActorJpaController {
	
	// "Fábrica" de conexiones (EntityManagerFactory).
	// Sirve para crear EntityManager, que son los que realmente hablan con la BD.
	private EntityManagerFactory emf = null;

	// En el constructor decimos con qué unidad de persistencia vamos a trabajar.
	// Esa unidad está definida en persistence.xml con el nombre "ejemplojpa".
	public ActorJpaController() {
		emf = Persistence.createEntityManagerFactory("unidad");
	}

	// Método que crea y devuelve un nuevo "EntityManager".
	// Pensalo como: "dame un empleado que se conecte a la base para trabajar".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para guardar un Artista en la base de datos.
	public void crear(Actor act) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (pedimos un empleado).
			em = getEntityManager();
			
			// Iniciamos una transacción: empieza la "promesa".
			em.getTransaction().begin();
			
			// Guardamos el objeto en la base (pero todavía no está confirmado).
			em.persist(act);
			
			// Confirmamos la transacción: ahora sí el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión (el empleado ya no hace falta).
			if (em != null) {
				em.close();
			}
		}
		}
			// BUSCAR UNO: busca un artista por su id.
			// No hace falta transacción porque no modifica nada en la base.
			public Actor buscarUno(int id) {
			EntityManager em = getEntityManager();
			try {
			// find busca por clave primaria. Si no existe, devuelve null.
			return em.find(Actor.class, id);
			} finally {
			em.close();
			}
			}
			// BUSCAR TODOS: devuelve una lista con todos los artistas.
			public List<Actor> buscarTodos() {
			EntityManager em = getEntityManager();
			try {
			// Consulta JPQL
			return em.createQuery("SELECT a FROM Actor a", Actor.class)
			.getResultList();
			} finally {
			em.close();}
			}
			// MODIFICAR UN ACTOR
			public void editar(Actor actor) {
			    EntityManager em = getEntityManager();

			    try {
			        em.getTransaction().begin();
			        em.merge(actor);
			        em.getTransaction().commit();
			    } catch (RuntimeException e) {
			        if (em.getTransaction().isActive()) {
			            em.getTransaction().rollback();
			        }
			        throw e;
			    } finally {
			        em.close();
			    }
			}

			// ELIMINAR UN ACTOR
			public void eliminar(int id) {
			    EntityManager em = getEntityManager();

			    try {
			        em.getTransaction().begin();

			        Actor actor = em.find(Actor.class, id);

			        if (actor != null) {

			            Long cantidad = em.createQuery(
			                "SELECT COUNT(p) FROM Pelicula p JOIN p.listaActores a WHERE a.id = :id",
			                Long.class)
			                .setParameter("id", id)
			                .getSingleResult();

			            if (cantidad > 0) {
			                throw new RuntimeException(
			                    "No se puede eliminar el actor porque tiene películas asociadas.");
			            }
			        
			            em.remove(actor);
			        }

			        em.getTransaction().commit();

			    } catch (RuntimeException e) {
			        if (em.getTransaction().isActive()) {
			            em.getTransaction().rollback();
			        }
			        throw e;

			    } finally {
			        em.close();
			    }
			}}