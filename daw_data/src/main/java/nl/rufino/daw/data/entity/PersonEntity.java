package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name="person")
@NamedQuery(name="PersonEntity.findAll", query="SELECT p FROM PersonEntity p")
public class PersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int personid;

	private String address;

	private String alias;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-many association to RoleEntity
	@ManyToMany
	@JoinTable(
		name="person_role"
		, joinColumns={
			@JoinColumn(name="personid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="roleid")
			}
		)
	private List<RoleEntity> roles;

	public PersonEntity() {
	}

	public int getPersonid() {
		return this.personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<RoleEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

}