package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.MemberProject;
import net.bitacademy.java41.vo.ProjectTask;

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

	public List<ProjectTask> list(int pno) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<ProjectTask> list = new ArrayList<ProjectTask>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM SPMS_TASKS where PNO=" + pno);

			ProjectTask t = null;
			while(rs.next()) {
				t = new ProjectTask();
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

//	public int add(ProjectTask projectTask, MemberProject memberProject) throws Exception{
//		Connection con = null;
//		PreparedStatement taskStmt = null;
//		PreparedStatement projectTaskStmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = this.conPool.getConnection();
//			
//			taskStmt = con.prepareStatement(
//					"INSERT INTO spms_tasks (pno, title, uiprotourl, content, start_date, end_date)"
//					" VALUES ((SELECT PNO FROM spms_prjs WHERE pno="'+ +'), "
//					" ?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
//			taskStmt.setString(1, projectTask.getTitle());
//			taskStmt.setString(2, projectTask.getUiProtoUrl());
//			taskStmt.setString(3, projectTask.getContent());
//			taskStmt.setDate(4, projectTask.getStartDate());
//			taskStmt.setDate(5, projectTask.getEndDate());
//			taskStmt.setInt(6, projectTask.getStatus());
//			taskStmt.executeUpdate();
//			
//			// * 자동 생성된 PK 값 알아내기
//			rs = taskStmt.getGeneratedKeys();
//			if (rs.next()) {
//				projectTask.setTno( rs.getInt(1) );
//			}
//			
//			// 2. 프로젝트의 PL을 등록한다.
//			projectTaskStmt = con.prepareStatement(
//					"insert into SPMS_MEMBTASK("
//					+ " EMAIL,PNO,TNO)"
//					+ " values(?,?,?)");
//			projectTaskStmt.setString(1, );
//			projectTaskStmt.setInt(2, MemberProject.getPno());
//			projectTaskStmt.setInt(3, ProjectTask.);
//			projectTaskStmt.executeUpdate();
//			
//			return projectTask.getTno();
//			
//		} catch (Exception e) {
//			throw e;
//			
//		} finally {
//			try {rs.close();} catch(Exception e) {}
//			try {TaskStmt.close();} catch(Exception e) {}
//			try {projectTaskStmt.close();} catch(Exception e) {}
//			if (con != null && con.getAutoCommit()) {
//				conPool.returnConnection(con);
//			}
//		}
//	}

}


