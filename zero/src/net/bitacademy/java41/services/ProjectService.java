package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.MemberProject;
import net.bitacademy.java41.vo.Project;

@Component
public class ProjectService {
	ProjectDao projectDao;
	DBConnectionPool dbPool;
	MemberDao memberDao;
	
	public ProjectService setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	public ProjectService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
		return this;
	}
	
	public List<Project> getProjectList() throws Exception {
		return projectDao.list();
	}
	
	public List<MemberProject> getMyProjects(String email) throws Exception {
		return projectDao.listByMember(email);
	}
	
	public Project getProject(int no) throws Exception {
		return projectDao.get(no);
	}
	
	public void change(Project project) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			projectDao.change(project, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}
	
	
	public void addProject(Project project) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			projectDao.add(project, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}
	public List<Member> getMember(int no) throws Exception {
		return projectDao.getMember(no);
	}
	
	public int getProjectDelete(int no) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int i = projectDao.remove(no, con);
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













