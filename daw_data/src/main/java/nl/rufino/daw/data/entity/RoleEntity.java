package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="RoleEntity.findAll", query="SELECT r FROM RoleEntity r")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int roleid;

	private String role;

	//bi-directional many-to-many association to PersonEntity
	@ManyToMany(mappedBy="roles")
	private List<PersonEntity> persons;

	public RoleEntity() {
	}

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<PersonEntity> getPersons() {
		return this.persons;
	}

	public void setPersons(List<PersonEntity> persons) {
		this.persons = persons;
	}

}