package com.examples.ezoo.test;

import java.util.List;

import com.examples.ezoo.dao.AnimalDAO;
//import com.examples.ezoo.dao.AnimalDAOImpl;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDAOImpl;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class Animal_FeedingSchedule_DAOTest {
	
	public static void main(String[] args) throws Exception{
//		AnimalDAO a_dao = new AnimalDAO();
	    FeedingScheduleDAO fs_dao = new FeedingScheduleDAOImpl();
	    Animal anim = new Animal(4, 104, "Name4", "TK4", "TP4", "TC4", "TO4", "TF4",	"TG4", "TS4", 374.98, 765.84, "Apple", "Healthy4");
	    FeedingSchedule feedSched = new FeedingSchedule(4, "3:23", "half a day", "Leopard Food", "fourth schedule");
	    
	    List<FeedingSchedule> list = fs_dao.getAllSchedules();
	    for (int i = 0; i < list.size(); i++){
	      FeedingSchedule f = list.get(i);
	      System.out.println(f);
	    }
//	    a_dao.saveAnimal(anim);
//	    fs_dao.addSchedule(feedSched);
//	    fs_dao.removeSchedule(feedSched);
//	    dao.getSchedule(animalSchedule);
//	    dao.assignSchedule(animal, feedSched); 
	  }

}
