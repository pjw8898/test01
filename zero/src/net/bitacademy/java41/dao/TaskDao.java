package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.MemberProject;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.Task;

@Component
public class TaskDao {
DBConnectionPool conPool;

	public TaskDao setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}

	public TaskDao() {	}

	public TaskDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}

	public Task get(int tno) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					"SELECT * FROM SPMS_TASKS where TNO=?");
			stmt.setInt(1, tno);
			rs = stmt.executeQuery();
			
			
			if (rs.next()) {
				return new Task()
					.setTno(rs.getInt("TNO"))
					.setPno(rs.getInt("PNO"))
					.setTitle(rs.getString("TITLE"))
					.setUiProtoUrl(rs.getString("UIPROTOURL"))
					.setContent(rs.getString("CONTENT"))
					.setStartDate(rs.getDate("START_DATE"))
					.setEndDate(rs.getDate("END_DATE"))
					.setStatus(rs.getInt("STATUS"));
			} 
				return null;
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public List<Task> list(int pno) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Task> list = new ArrayList<Task>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM SPMS_TASKS where PNO=" + pno);

			Task t = null;
			while(rs.next()) {
				t = new Task();
				t.setTno(rs.getInt("TNO"));
				t.setPno(rs.getInt("PNO"));
				t.setTitle(rs.getString("TITLE"));
				t.setUiProtoUrl(rs.getString("UIPROTOURL"));
				t.setContent(rs.getString("CONTENT"));
				t.setStartDate(rs.getDate("START_DATE"));
				t.setEndDate(rs.getDate("END_DATE"));
				t.setStatus(rs.getInt("STATUS"));
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			throw e;

		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	public int add(Connection transactionConnection, Task task
			) throws Exception{

		Connection con = transactionConnection;
		PreparedStatement taskStmt = null;
		
		try {
			con = this.conPool.getConnection();
			
			taskStmt = con.prepareStatement(
					"INSERT INTO spms_tasks (pno, title, uiprotourl, content, start_date, end_date, status)"
					+ " VALUES (?,?,?,?,?,?,?)");
			
			taskStmt.setInt(1, task.getPno());
			taskStmt.setString(2, task.getTitle());
			taskStmt.setString(3, task.getUiProtoUrl());
			taskStmt.setString(4, task.getContent());
			taskStmt.setDate(5, task.getStartDate());
			taskStmt.setDate(6, task.getEndDate());
			taskStmt.setInt(7, task.getStatus());
			return taskStmt.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {taskStmt.close();} catch(Exception e) {}
			if (con != null && con.getAutoCommit()) {
				conPool.returnConnection(con);
			}
		}
		
	}
	
	public int change(Task task, Connection transactionConnection) throws Exception {
		Connection con = transactionConnection;
		PreparedStatement stmt = null;

		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_tasks set"
				+ " TITLE=?, UIPROTOURL=?, CONTENT=?,"
				+ " START_DATE=?, END_DATE=?,status=?"
				+ " where TNO=? and pno=?");
			
				stmt.setString(1, task.getTitle());
				stmt.setString(2, task.getUiProtoUrl());
				stmt.setString(3, task.getContent());
				stmt.setDate(4, task.getStartDate());
				stmt.setDate(5, task.getEndDate());
				stmt.setInt(6, task.getStatus());
				stmt.setInt(7, task.getTno());
				stmt.setInt(8, task.getPno());
				return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null && con.getAutoCommit()) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int remove(int tno, Connection transactionConnection) throws Exception {
		Connection con = transactionConnection;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				  " delete from SPMS_TASKS "
					+ " where TNO=?"	);
			stmt.setInt(1, tno);
			
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null && con.getAutoCommit()) {
				conPool.returnConnection(con);
			}
		}
	}

}


