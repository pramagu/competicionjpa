package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Atletas;
import model.JPAUtil;
import model.Records;

public class AppJpaDao {

	public void menuRepetitivo(int opcion, Scanner scanner, EntityManager entity) {
		Atletas atleta;
		while (opcion != 6) {
			System.out.println("1. Crear atleta");
			System.out.println("2. Buscar atleta");
			System.out.println("3. Actualizar Atleta");
			System.out.println("4. Eliminar Atleta");
			System.out.println("5. Revisar Clasificación");
			System.out.println("6. Salir");
			System.out.println("7. Obtener nuevos Records");
			System.out.println("Elija una opción:");
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				atleta = new Atletas();
				System.out.println("¿Cual es tu nombre?");
				atleta.setFullName(scanner.nextLine());
				scanner.nextLine();
				System.out.println("Indique el Nombre del Atleta:");
				atleta.setFullName(scanner.nextLine());
				System.out.println("Indique el sexo del atleta:");
				atleta.setGenre(scanner.nextLine());
				System.out.println("Indique la edad del atleta:");
				atleta.setAge(Integer.parseInt(scanner.nextLine()));
				System.out.println("Indique el peso maxímo de:");
				System.out.println("Press de banca:");
				atleta.setBenchpress(Integer.parseInt(scanner.nextLine()));
				System.out.println("Peso muerto:");
				atleta.setDeadlift(Integer.parseInt(scanner.nextLine()));
				System.out.println("Sentadilla:");
				atleta.setSquat(Integer.parseInt(scanner.nextLine()));
				System.out.println("Total:");
				atleta.setTotal(Integer.parseInt(scanner.nextLine()));
				entity.getTransaction().begin();
				entity.persist(atleta);
				entity.getTransaction().commit();
				System.out.println("Atleta registrado..");
				System.out.println();

				break;
			case 2:
				System.out.println("Teclee el número de identificación del atleta:");
				atleta = new Atletas();
				atleta = entity.find(Atletas.class, scanner.nextInt());
				if (atleta != null) {
					System.out.println(atleta);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Atleta no encontrado... Lista de atletas completa");
					List<Atletas> listaAtletas = new ArrayList<>();
					Query query = entity.createQuery("SELECT a FROM Atleta a");
					listaAtletas = query.getResultList();
					for (Atletas a : listaAtletas) {
						System.out.println(a);
					}
					System.out.println();
				}
				break;
			case 3:
				System.out.println("Teclee el id del atleta a actualizar:");
				atleta = new Atletas();
				atleta = entity.find(Atletas.class, scanner.nextInt());
				if (atleta != null) {
					System.out.println(atleta);
					System.out.println("Digite el nombre del atleta:");
					scanner.nextLine();
					atleta.setFullName(scanner.nextLine());
					System.out.println("Indique el sexo del atleta:");
					scanner.nextLine();
					atleta.setGenre(scanner.nextLine());
					System.out.println("Indique la edad del atleta:");
					atleta.setAge(scanner.nextInt());
					System.out.println("Indique el peso maxímo de:");
					System.out.println("Press de banca:");
					atleta.setBenchpress(scanner.nextInt());
					System.out.println("Peso muerto:");
					atleta.setDeadlift(scanner.nextInt());
					System.out.println("Sentadilla:");
					atleta.setSquat(scanner.nextInt());
					System.out.println("Total:");
					atleta.setTotal(scanner.nextInt());
					entity.getTransaction().begin();
					entity.persist(atleta);
					entity.getTransaction().commit();
					System.out.println("Atleta actualizado..");
					System.out.println();
				} else {
					System.out.println("Atleta no encontrado....");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Digite el id del atleta a eliminar:");
				atleta = new Atletas();
				atleta = entity.find(Atletas.class, scanner.nextInt());
				if (atleta != null) {
					System.out.println(atleta);
					entity.getTransaction().begin();
					entity.remove(atleta);
					entity.getTransaction().commit();
					System.out.println("Atleta eliminado...");
				} else {
					System.out.println("Atleta no encontrado...");
				}
				break;

			case 5:
				Query query = entity.createQuery("SELECT a FROM Atletas a ORDER BY a.total DESC");
				List<Atletas> resultList = query.getResultList();
				int counter = 0;
				for (Atletas a : resultList) {
					counter++;
					System.out.println("Puesto nº" + counter + " " + a.toCompetiString());
				}
				break;

			case 6:
				entity.close();
				JPAUtil.shutdown();
				break;

			case 7:
				nuevoRecord2(entity);

				break;
			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
	}

	private static void nuevoRecord2(EntityManager entity) {

		Records record = new Records();
		record = entity.find(Records.class, 1);

		CriteriaBuilder builder = entity.getCriteriaBuilder();
		CriteriaQuery<Atletas> cq = builder.createQuery(Atletas.class);
		Root<Atletas> root = cq.from(Atletas.class);

		cq.multiselect(builder.max(root.<Integer>get("benchpress")), builder.max(root.<Integer>get("deadlift")),
				builder.max(root.<Integer>get("squat")), builder.max(root.<Integer>get("total")));

		List<Atletas> listaMaximos = entity.createQuery(cq).getResultList();

		record.setBenchpress(listaMaximos.get(0).getBenchpress());
		record.setDeadlift(listaMaximos.get(0).getDeadlift());
		record.setSquat(listaMaximos.get(0).getSquat());
		record.setTotal(listaMaximos.get(0).getTotal());

		entity.getTransaction().begin();
		entity.persist(record);
		entity.getTransaction().commit();

	}

}
