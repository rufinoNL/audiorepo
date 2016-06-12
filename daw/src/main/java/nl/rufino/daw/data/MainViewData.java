package nl.rufino.daw.data;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.rufino.daw.data.entity.MusicEntity;

public class MainViewData {

	private EntityManager em;

	public MainViewData(EntityManager em) {
		this.em = em;
	}
	
	//Testing git repo via SoureTree
	public ObservableList<MusicEntity> getMusic(){
		ObservableList<MusicEntity> musicList = FXCollections.observableArrayList();
		Query queryFindAll = em.createNamedQuery("MusicEntity.findAll");
		Collection<MusicEntity> result  = queryFindAll.getResultList();
		
		musicList.addAll(result);
		return musicList;
	}
}