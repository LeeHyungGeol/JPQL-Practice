package org.example.jpql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(
  name = "Member.findByUsername",
  query = "select m from Members m where m.username = :username"
)
public class Member {
  @Id @GeneratedValue
  private Long id;
  private String username;
  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID")
  private Team team;

  private MemberType type;

  public void changeTeam(Team team) {
    setTeam(team);
    team.getMembers().add(this);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Team getTeam() {
    return team;
  }

  private void setTeam(Team team) {
    this.team = team;
  }

  public MemberType getType() {
    return type;
  }

  public void setType(MemberType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Member{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", age=" + age +
      ", type=" + type +
      '}';
  }
}
