package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name="company")
@NamedQuery(name="CompanyEntity.findAll", query="SELECT c FROM CompanyEntity c")
public class CompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false)
	private int companyid;

	@Column(name="company_name")
	private String companyName;

	//bi-directional many-to-many association to PersonRoleEntity
	@ManyToMany(mappedBy="companies")
	private List<PersonRoleEntity> personRoles;

	public CompanyEntity() {
	}

	public int getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<PersonRoleEntity> getPersonRoles() {
		return this.personRoles;
	}

	public void setPersonRoles(List<PersonRoleEntity> personRoles) {
		this.personRoles = personRoles;
	}

}