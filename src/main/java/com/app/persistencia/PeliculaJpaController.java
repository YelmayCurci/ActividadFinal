package com.app.persistencia;

import java.util.List;

import com.app.logica.Actor;
import com.app.logica.Pelicula;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PeliculaJpaController {
	
	// "Fábrica" de conexiones con la base de datos.
	// Nos permite crear EntityManager, que son los que realmente hacen el trabajo.
	private EntityManagerFactory emf = null;

	// En el constructor indicamos qué unidad de persistencia usar.
	// El nombre "ejemplojpa" debe coincidir con el que está en persistence.xml.
	public PeliculaJpaController() {
		emf = Persistence.createEntityManagerFactory("unidad");
	}

	// Método que devuelve un nuevo EntityManager (una conexión lista para trabajar).
	// Podés pensarlo como: "dame un empleado que se conecte a la BD".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para crear (persistir) una Canción en la base de datos.
	public void crear(Pelicula peli) {
		
		EntityManager em = null;
		try {
			// Abrimos la conexión (traemos al empleado).
			em = getEntityManager();
			
			// Iniciamos la transacción (arranca la promesa de que se hará todo o nada).
			em.getTransaction().begin();
			
			// Guardamos el objeto Cancion en la base (todavía no confirmado).
			em.persist(peli);
			
			// Confirmamos la transacción: ahora sí, el dato queda guardado en la BD.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión si estaba abierta (el empleado termina su turno).
			if (em != null) {
				em.close();
			}
		}}
	
	// act 15
			// BUSCAR UNO: busca un artista por su id.
			// No hace falta transacción porque no modifica nada en la base.
			public Pelicula buscarUno(int id) {
			EntityManager em = getEntityManager();
			try {
			// find busca por clave primaria. Si no existe, devuelve null.
			return em.find(Pelicula.class, id);
			} finally {
			em.close();
			}
			}
			// BUSCAR TODOS: devuelve todas las películas junto con sus actores.
			public List<Pelicula> buscarTodos() {
			    EntityManager em = getEntityManager();

			    try {
			        return em.createQuery(
			                "SELECT DISTINCT p FROM Pelicula p LEFT JOIN FETCH p.listaActores",
			                Pelicula.class)
			                .getResultList();

			    } finally {
			        em.close();
			    }
			}

			// ELIMINAR UNA CANCION
			public void eliminar(int id) {
			    EntityManager em = getEntityManager();

			    try {
			        em.getTransaction().begin();

			        Pelicula pelicula = em.find(Pelicula.class, id);

			        if (pelicula != null) {
			            em.remove(pelicula);
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
			
		}
			// AYUDA 2: trae los actores de las películas cuyo título contiene el texto
			public List<Actor> buscarActoresPorPelicula(String texto) {
				EntityManager em = getEntityManager();
				try {
					return em.createQuery(
							"SELECT a FROM Actor a WHERE LOWER(a.pelicula.titulo) LIKE :texto", Actor.class)
							.setParameter("texto", "%" + texto.toLowerCase() + "%")
							.getResultList();
				} finally {
					em.close();
				}
			}

			// Busca actores por nombre o apellido
			public List<Actor> buscarActoresPorNombre(String texto) {
				EntityManager em = getEntityManager();
				try {
					return em.createQuery(
							"SELECT a FROM Actor a WHERE LOWER(a.nombre) LIKE :texto "
							+ "OR LOWER(a.apellido) LIKE :texto", Actor.class)
							.setParameter("texto", "%" + texto.toLowerCase() + "%")
							.getResultList();
				} finally {
					em.close();
				}
			}

			public void editar(Pelicula pelicula) {
				// TODO Auto-generated method stub
				
			}// BUSCAR PELÍCULAS POR TÍTULO
				public List<Pelicula> buscarPorTitulo(String texto) {
				    EntityManager em = getEntityManager();

				    try {
				        return em.createQuery(
				                "SELECT DISTINCT p FROM Pelicula p "
				                + "LEFT JOIN FETCH p.listaActores "
				                + "WHERE LOWER(p.titulo) LIKE LOWER(:texto)",
				                Pelicula.class)
				                .setParameter("texto", "%" + texto + "%")
				                .getResultList();

				    } finally {
				        em.close();
				    }
				}
}

