package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person_role database table.
 * 
 */
@Entity
@Table(name="person_role")
@NamedQuery(name="PersonRoleEntity.findAll", query="SELECT p FROM PersonRoleEntity p")
public class PersonRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonRoleEntityPK id;

	//bi-directional many-to-many association to CompanyEntity
	@ManyToMany
	@JoinTable(
		name="person_role_for_company"
		, joinColumns={
			@JoinColumn(name="personid", referencedColumnName="personid"),
			@JoinColumn(name="roleid", referencedColumnName="roleid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="companyid")
			}
		)
	private List<CompanyEntity> companies;

	//bi-directional many-to-many association to MusicEntity
	@ManyToMany
	@JoinTable(
		name="person_role_regarding_music"
		, joinColumns={
			@JoinColumn(name="personid", referencedColumnName="personid"),
			@JoinColumn(name="roleid", referencedColumnName="roleid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="musicid")
			}
		)
	private List<MusicEntity> musics;

	public PersonRoleEntity() {
	}

	public PersonRoleEntityPK getId() {
		return this.id;
	}

	public void setId(PersonRoleEntityPK id) {
		this.id = id;
	}

	public List<CompanyEntity> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<CompanyEntity> companies) {
		this.companies = companies;
	}

	public List<MusicEntity> getMusics() {
		return this.musics;
	}

	public void setMusics(List<MusicEntity> musics) {
		this.musics = musics;
	}

}