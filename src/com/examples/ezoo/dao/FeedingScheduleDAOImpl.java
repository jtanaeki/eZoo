package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;


public class FeedingScheduleDAOImpl implements FeedingScheduleDAO{
	
	Connection conn = null;
	PreparedStatement pStmt = null;
	int success = 0;

	@Override
	public List<FeedingSchedule> getAllSchedules() {
		List<FeedingSchedule> schedule = new ArrayList<>();

		try {
			conn = DAOUtilities.getConnection();

			String sql = "SELECT * FROM FEEDING_SCHEDULES";
			
			pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				FeedingSchedule fs = new FeedingSchedule();

				fs.setScheduleID(rs.getLong("schedule_ID"));
				fs.setFeedingTime(rs.getString("feeding_time"));

				fs.setReccurence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
				
				schedule.add(fs);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}

		return schedule;
	}

	@Override
	public void addSchedule(FeedingSchedule scheduleToSave) throws Exception {
		try {
			conn = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES VALUES (?, ?, ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setLong(1, scheduleToSave.getScheduleID());
			pStmt.setString(2, scheduleToSave.getFeedingTime());
			pStmt.setString(3, scheduleToSave.getReccurence());
			pStmt.setString(4, scheduleToSave.getFood());
			pStmt.setString(5, scheduleToSave.getNotes());
			
			success = pStmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		
		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Insert schedule failed: " + scheduleToSave);
		}
		else {
			System.out.println("\'" + scheduleToSave + "\'" + " added to the database.");
		}
	}

	@Override
	public void removeSchedule(FeedingSchedule scheduleToDelete) throws Exception {
		try {
			conn = DAOUtilities.getConnection();
			String sql = "DELETE FROM FEEDING_SCHEDULES WHERE schedule_ID = ?";
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setLong(1, scheduleToDelete.getScheduleID());
			
			success = pStmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		
		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Removing schedule failed: " + scheduleToDelete);
		}
		else {
			System.out.println("\'" + scheduleToDelete + "\'" + " removed from the database.");
		}
	}

	@Override
	public FeedingSchedule getSchedule(Animal animal) {
		FeedingSchedule fs = new FeedingSchedule();
		
		try {
			conn = DAOUtilities.getConnection();

			String sql = "SELECT fs.schedule_ID, feeding_time, recurrence" + 
					"FROM ANIMALS RIGHT JOIN FEEDING_SCHEDULES	 fs" + 
					"ON ? = fs.schedule_ID" + 
					"ORDER BY fs.schedule_ID";
			
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setLong(1, animal.getScheduleID());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				fs.setScheduleID(rs.getLong("fs.schedule_ID"));
				fs.setFeedingTime(rs.getString("feeding_time"));

				fs.setReccurence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		
		return fs;
	}

	@Override
	public void assignSchedule(Animal animal, FeedingSchedule fs) {		
		try {
			conn = DAOUtilities.getConnection();

			String sql = "UPDATE ANIMALS SET ? = ? WHERE animal_id = ?";
			
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setLong(1, animal.getScheduleID());
			pStmt.setLong(2, fs.getScheduleID());
			pStmt.setLong(3, animal.getAnimalID());
			
			
			pStmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		
	}

	private void closeResources() {
		try {
			if(pStmt != null) {
				pStmt.close();
			}
		}
		catch(SQLException e) {
			System.out.println("Could not close prepared statement!");
			e.printStackTrace();
		}
		
		try {
			if(conn != null) {
				conn.close();
			}
		}
		catch(SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
}
