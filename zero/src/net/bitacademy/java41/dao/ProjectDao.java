package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.MemberProject;
import net.bitacademy.java41.vo.Project;

public class ProjectDao {
	DBConnectionPool conPool;
	
	public ProjectDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public List<Project> list() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Project> list = new ArrayList<Project>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select PNO,TITLE,START_DATE,END_DATE"
					+ " from SPMS_PRJS"
					+ " order by PNO desc");
			
			while(rs.next()) {
				list.add(new Project()
							.setNo(rs.getInt("PNO"))
							.setTitle(rs.getString("TITLE"))
							.setStartDate(rs.getDate("START_DATE"))
							.setEndDate(rs.getDate("END_DATE")));
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
	
	public Project get(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					"select PNO,TITLE,CONTENT,START_DATE,END_DATE,TAG"
							+ " from SPMS_PRJS"
							+ " where PNO=?");
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Project()
							.setNo(rs.getInt("PNO"))
							.setTitle(rs.getString("TITLE"))
							.setContent(rs.getString("CONTENT"))
							.setStartDate(rs.getDate("START_DATE"))
							.setEndDate(rs.getDate("END_DATE"))
							.setTag(rs.getString("TAG"));
			} else {
				return null;
			}
			
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
	
	public List<MemberProject> listByMember(String email) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberProject> list = new ArrayList<MemberProject>();

		try {
			con = conPool.getConnection();
			
			String sql = "select t1.PNO,t1.TITLE,t2.LEVEL ";
			sql += " from SPMS_PRJS t1, SPMS_PRJMEMB t2 ";
			sql += " where t1.PNO=t2.PNO ";
			sql += " and t2.EMAIL=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new MemberProject()
							.setNo(rs.getInt("PNO"))
							.setTitle(rs.getString("TITLE"))
							.setLevel(rs.getInt("LEVEL")));
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
	
	public int add(Project project, Connection transactionConnection) throws Exception {
		Connection con = transactionConnection;
		PreparedStatement projectStmt = null;
		PreparedStatement projectMemberStmt = null;
		ResultSet rs = null;
		
		try {
			projectStmt = con.prepareStatement(
				"insert into SPMS_PRJS("
				+ " TITLE,CONTENT,START_DATE,END_DATE,TAG)"
				+ " values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			projectStmt.setString(1, project.getTitle());
			projectStmt.setString(2, project.getContent());
			projectStmt.setDate(3, project.getStartDate());
			projectStmt.setDate(4, project.getEndDate());
			projectStmt.setString(5, project.getTag());
			projectStmt.executeUpdate();
			
			rs = projectStmt.getGeneratedKeys();
			if (rs.next()) {
				project.setNo( rs.getInt(1) );
			}
			
			projectMemberStmt = con.prepareStatement(
					"insert into SPMS_PRJMEMB("
					+ " EMAIL,PNO,LEVEL)"
					+ " values(?,?,0)");
			projectMemberStmt.setString(1, project.getLeader());
			projectMemberStmt.setInt(2, project.getNo());
			projectMemberStmt.executeUpdate();
			
			return project.getNo();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch(Exception e) {}
			try {projectStmt.close();} catch(Exception e) {}
			try {projectMemberStmt.close();} catch(Exception e) {}
		}
	}
	
	public int change(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_PRJS set"
				+ " TITLE=?,CONTENT=?,"
				+ " START_DATE=?,END_DATE=?,TAG=?"
				+ " where PNO=?");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, project.getStartDate());
			stmt.setDate(4, project.getEndDate());
			stmt.setString(5, project.getTag());
			stmt.setInt(6, project.getNo());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public List<Member> getMember(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select a.EMAIL,a.MNAME,b.LEVEL,a.TEL,a.BLOG from SPMS_MEMBS a, SPMS_PRJMEMB b "
				+ " where a.EMAIL=b.EMAIL and PNO=?"); // ? -> in-parameter
			stmt.setInt(1, no);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Member()
				          .setEmail(rs.getString("EMAIL"))
				          .setName(rs.getString("MNAME"))
				          .setLevel(Integer.parseInt(rs.getString("LEVEL")))
				          .setTel(rs.getString("TEL"))
				          .setBlog(rs.getString("BLOG")));
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
	public int remove(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				  " delete from SPMS_PRJS "
					+ " where PNO=?"	);
			stmt.setInt(1, no);
			
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
	public int remove2(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					"delete from SPMS_PRJS where PNO=? "
					);
			stmt.setInt(1, no);
		
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
}
