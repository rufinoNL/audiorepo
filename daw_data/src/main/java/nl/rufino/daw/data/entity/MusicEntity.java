package nl.rufino.daw.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the music database table.
 * 
 */
@Entity
@Table(name="music")
@NamedQueries({
	@NamedQuery(name="MusicEntity.findByTrackName", query="SELECT m FROM MusicEntity m WHERE m.trackName = :trackName"),
	@NamedQuery(name="MusicEntity.findAll", query="SELECT m FROM MusicEntity m")})
public class MusicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false)
	private int musicid;

	@Temporal(TemporalType.DATE)
	@Column(name="created_on")
	private Date createdOn;

	@Column(name="original_folder")
	private String originalFolder;

	@Column(name="track_name")
	private String trackName;

	@Column(name="track_name2")
	private String trackName2;

	//bi-directional many-to-many association to GenreEntity
	@ManyToMany
	@JoinTable(
		name="music_genre"
		, joinColumns={
			@JoinColumn(name="musicid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="genreid")
			}
		)
	private List<GenreEntity> genres;

	public MusicEntity() {
	}

	public int getMusicid() {
		return this.musicid;
	}

	public void setMusicid(int musicid) {
		this.musicid = musicid;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getOriginalFolder() {
		return this.originalFolder;
	}

	public void setOriginalFolder(String originalFolder) {
		this.originalFolder = originalFolder;
	}

	public String getTrackName() {
		return this.trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackName2() {
		return this.trackName2;
	}

	public void setTrackName2(String trackName2) {
		this.trackName2 = trackName2;
	}

	public List<GenreEntity> getGenres() {
		return this.genres;
	}

	public void setGenres(List<GenreEntity> genres) {
		this.genres = genres;
	}

}