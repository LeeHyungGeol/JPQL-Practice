package org.example.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import org.example.jpql.domain.Member;
import org.example.jpql.domain.Team;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    //code

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Team team = new Team();
      team.setName("memberTeam1");
      em.persist(team);

      Member member = new Member();
      member.setUsername("같은 이름 username");
      member.setAge(22);
      member.changeTeam(team);
      em.persist(member);

      em.flush();
      em.clear();

      String query = "select nullif(m.username, '같은 이름 username') from Member m";
      List<String> result = em.createQuery(query).getResultList();

      for (String s : result) {
        System.out.println("username = " + s);
      }

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }
}
