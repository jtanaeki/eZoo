package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Main interface used to execute CRUD methods on FeedingSchedule class
 * @author jt
 *
 */
public interface FeedingScheduleDAO {

	/**
	 * Used to retrieve a list of all Schedules 
	 * @return
	 */
	List<FeedingSchedule> getAllSchedules();

	/**
	 * Used to persist the schedule to the datastore
	 * @param scheduleToSave
	 */
	void addSchedule(FeedingSchedule scheduleToSave) throws Exception;
	
	/**
	 * Used to remove the schedule from the datastore
	 * @param scheduleToDelete
	 */
	void removeSchedule(FeedingSchedule scheduleToDelete) throws Exception;

	/**
	 * Used to retrieve a schedule for a given animal 
	 * @return
	 */
	 FeedingSchedule getSchedule(Animal animal);
	 
	/**
	 * Used to assign a schedule to a given animal 
	 * @param animalSchedule
	 */
	 void assignSchedule(Animal animal, FeedingSchedule fs);

}
