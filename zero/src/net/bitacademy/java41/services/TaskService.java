package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.TaskDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.MemberProject;
import net.bitacademy.java41.vo.ProjectTask;

public class TaskService {
	TaskDao taskDao;
	ProjectDao projectDao;
	DBConnectionPool dbPool;
	MemberDao memberDao;

	public TaskService setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
		return this;
	}

	public TaskService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
		return this;
	}
	
	public List<ProjectTask> list(int pno) throws Exception {
		return taskDao.list(pno);
	}

}
//	public int addProjectTask(ProjectTask projectTask, MemberProject memberProject) {
//		Connection con = dbPool.getConnection();
//		con.setAutoCommit(false);
//		try {
//			TaskDao.add(projectTask, memberProject);
//			con.commit();
//		} catch (Exception e) {
//			con.rollback();
//			throw e;
//			
//		} finally {
//			con.setAutoCommit(true);
//			dbPool.returnConnection(con);
//		}
//	}
//
//}