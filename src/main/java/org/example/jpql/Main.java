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
      Team teamA = new Team();
      teamA.setName("teamA");
      em.persist(teamA);

      Team teamB = new Team();
      teamB.setName("teamB");
      em.persist(teamB);

      Team teamC = new Team();
      teamC.setName("teamB");
      em.persist(teamC);

      Member member1 = new Member();
      member1.setUsername("member1");
      member1.setAge(22);
      member1.changeTeam(teamA);
      em.persist(member1);

      Member member2 = new Member();
      member2.setUsername("member2");
      member2.setAge(22);
      member2.changeTeam(teamA);
      em.persist(member2);

      Member member3 = new Member();
      member3.setUsername("member3");
      member3.setAge(22);
      member3.changeTeam(teamB);
      em.persist(member3);

      Member member4 = new Member();
      member4.setUsername("member4");
      member4.setAge(22);
      em.persist(member4);

      em.flush();
      em.clear();

      String query = "select t from Team t join t.members m";
      List<Team> result = em.createQuery(query, Team.class).getResultList();

      System.out.println("result.size() = " + result.size());

      for (Team t : result) {
        System.out.println("team = " + t.getName());
        for (Member m : t.getMembers()) {
          System.out.println("-> member = " + m.getUsername());
        }
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
