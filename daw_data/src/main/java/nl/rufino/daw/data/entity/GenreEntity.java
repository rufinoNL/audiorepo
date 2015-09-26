package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@Table(name="genre")
@NamedQuery(name="GenreEntity.findAll", query="SELECT g FROM GenreEntity g")
public class GenreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false)
	private int genreid;

	private String genre;

	//bi-directional many-to-many association to MusicEntity
	@ManyToMany(mappedBy="genres")
	private List<MusicEntity> musics;

	public GenreEntity() {
	}

	public int getGenreid() {
		return this.genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<MusicEntity> getMusics() {
		return this.musics;
	}

	public void setMusics(List<MusicEntity> musics) {
		this.musics = musics;
	}

}