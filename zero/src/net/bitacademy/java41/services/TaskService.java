package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.TaskDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Task;

public class TaskService {
	TaskDao taskDao;
	ProjectDao projectDao;
	DBConnectionPool dbPool;
	MemberDao memberDao;
	Task task;

	public TaskService setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
		return this;
	}

	public TaskService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
		return this;
	}
	
	public Task getTask(int tno) throws Exception{
		return taskDao.get(tno);
	}
	
	public List<Task> list(int pno) throws Exception {
		return taskDao.list(pno);
	}

	public void addProjectTask(Task task) 
	
	throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			taskDao.add(con, task);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}

	public void change(Task task) throws Exception {
		
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			taskDao.change(task, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}
	
	public int getTaskDelete(int tno) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int i = taskDao.remove(tno, con);
			con.commit();
			return i;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}
	
}

