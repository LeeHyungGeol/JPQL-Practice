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

      Member member2 = new Member();
      member2.setUsername("같은 이름 username");
      member2.setAge(22);
      member2.changeTeam(team);
      em.persist(member2);

      em.flush();
      em.clear();

      String query = "select m.username from Team t join t.members m";
      List<String> result = em.createQuery(query).getResultList();


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
