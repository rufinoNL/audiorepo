package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the person_role database table.
 * 
 */
@Embeddable
public class PersonRoleEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int roleid;

	@Column(insertable=false, updatable=false)
	private int personid;

	public PersonRoleEntityPK() {
	}
	public int getRoleid() {
		return this.roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getPersonid() {
		return this.personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonRoleEntityPK)) {
			return false;
		}
		PersonRoleEntityPK castOther = (PersonRoleEntityPK)other;
		return 
			(this.roleid == castOther.roleid)
			&& (this.personid == castOther.personid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleid;
		hash = hash * prime + this.personid;
		
		return hash;
	}
}