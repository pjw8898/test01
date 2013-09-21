package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;

@Component
public class MemberService {
	DBConnectionPool dbConnectionPool;
	MemberDao memberDao;
	
	public MemberService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbConnectionPool = dbPool;
		return this;
	}
	
	public MemberService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public void signUp(Member member) throws Exception {
		Connection con = dbConnectionPool.getConnection();
		con.setAutoCommit(false);
		try {
			memberDao.add(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				for (String path : photos) {
					memberDao.addPhoto(member.getEmail(), path);
				}
			}
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbConnectionPool.returnConnection(con);
		}
	}
	
	public List<Member> getMemberList() throws Exception {
		return memberDao.list();
	}
	
	public Member getMember(String email) throws Exception {
		return memberDao.get(email);
	}
	
	public boolean changePassword(
			String email, String oldPassword, String newPassword) throws Exception {
		if (memberDao.changePassword(email, oldPassword, newPassword) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void myInfochange(Member member) throws Exception {
		Connection con = dbConnectionPool.getConnection();
		con.setAutoCommit(false);
		try {
			memberDao.myInfochange(member, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbConnectionPool.returnConnection(con);
		}
	}

	public void adminChange(Member member) throws Exception {
		Connection con = dbConnectionPool.getConnection();
		con.setAutoCommit(false);
		try {
			memberDao.adminChange(member, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbConnectionPool.returnConnection(con);
		}
	}
	
	public int getMemberDelete(String email) throws Exception {
		
		Connection con = dbConnectionPool.getConnection();
		con.setAutoCommit(false);
		try {
			int i = memberDao.remove(email, con);
			con.commit();
			return i;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbConnectionPool.returnConnection(con);
		}
		
	}
	
	public int getMemberDelete2(String email) throws Exception {
		Connection con = dbConnectionPool.getConnection();
		con.setAutoCommit(false);
		try {
			int j = memberDao.remove(email, con);
			con.commit();
			return j;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbConnectionPool.returnConnection(con);
		}
	}
}