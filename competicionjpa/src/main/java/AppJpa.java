
import java.util.Scanner;

import javax.persistence.EntityManager;

import dao.AppJpaDao;


import model.JPAUtil;


public class AppJpa {

	public static void main(String[] args) {
		
		int opcion = 0;
		
		Scanner scanner = new Scanner(System.in);

		AppJpaDao dao = new AppJpaDao();
		
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		dao.menuRepetitivo(opcion, scanner, entity);
		
	}	

}
