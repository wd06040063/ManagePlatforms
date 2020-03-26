package com.WangD.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.WangD.model.DormBuild;
import com.WangD.model.RepairRecord;
import com.WangD.util.StringUtil;

public class RepairRecordDao {
	public List<RepairRecord> recordList(Connection con, RepairRecord s_repairrecord)throws Exception {
		List<RepairRecord> recordList = new ArrayList<RepairRecord>();
		StringBuffer sb = new StringBuffer("select * from t_repairrecord t1");
		if(StringUtil.isNotEmpty(s_repairrecord.getStudentNumber())) {
			sb.append(" and t1.studentNumber like '%"+s_repairrecord.getStudentNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_repairrecord.getStudentName())) {
			sb.append(" and t1.studentName like '%"+s_repairrecord.getStudentName()+"%'");
		}
		if(s_repairrecord.getDormBuildId()!=0) {
			sb.append(" and t1.dormBuildId="+s_repairrecord.getDormBuildId());
		}
		if(StringUtil.isNotEmpty(s_repairrecord.getDate())) {
			sb.append(" and t1.date="+s_repairrecord.getDate());
		}
		if(StringUtil.isNotEmpty(s_repairrecord.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_repairrecord.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_repairrecord.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_repairrecord.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			RepairRecord record=new RepairRecord();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<RepairRecord> recordListWithBuild(Connection con, RepairRecord s_record, int buildId)throws Exception {
		List<RepairRecord> recordList = new ArrayList<RepairRecord>();
		StringBuffer sb = new StringBuffer("select * from t_repairrecord t1");
		if(StringUtil.isNotEmpty(s_record.getStudentNumber())) {
			sb.append(" and t1.studentNumber like '%"+s_record.getStudentNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_record.getStudentName())) {
			sb.append(" and t1.studentName like '%"+s_record.getStudentName()+"%'");
		}
		sb.append(" and t1.dormBuildId="+buildId);
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			RepairRecord record=new RepairRecord();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<RepairRecord> recordListWithNumber(Connection con, RepairRecord s_record, String studentNumber)throws Exception {
		List<RepairRecord> recordList = new ArrayList<RepairRecord>();
		StringBuffer sb = new StringBuffer("select * from t_repairrecord t1");
		if(StringUtil.isNotEmpty(studentNumber)) {
			sb.append(" and t1.studentNumber ="+studentNumber);
		} 
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			RepairRecord record=new RepairRecord();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<DormBuild> dormBuildList(Connection con)throws Exception {
		List<DormBuild> dormBuildList = new ArrayList<DormBuild>();
		String sql = "select * from t_dormBuild";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormBuild dormBuild=new DormBuild();
			dormBuild.setDormBuildId(rs.getInt("dormBuildId"));
			dormBuild.setDormBuildName(rs.getString("dormBuildName"));
			dormBuild.setDetail(rs.getString("dormBuildDetail"));
			dormBuildList.add(dormBuild);
		}
		return dormBuildList;
	}
//	
//	public int studentCount(Connection con, Student s_student)throws Exception {
//		StringBuffer sb = new StringBuffer("select count(*) as total from t_student t1");
//		if(StringUtil.isNotEmpty(s_student.getName())) {
//			sb.append(" and t1.name like '%"+s_student.getName()+"%'");
//		} else if(StringUtil.isNotEmpty(s_student.getStuNumber())) {
//			sb.append(" and t1.stuNum like '%"+s_student.getStuNumber()+"%'");
//		} else if(StringUtil.isNotEmpty(s_student.getDormName())) {
//			sb.append(" and t1.dormName like '%"+s_student.getDormName()+"%'");
//		}
//		if(s_student.getDormBuildId()!=0) {
//			sb.append(" and t1.dormBuildId="+s_student.getDormBuildId());
//		}
//		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//		ResultSet rs = pstmt.executeQuery();
//		if(rs.next()) {
//			return rs.getInt("total");
//		} else {
//			return 0;
//		}
//	}
	
	public RepairRecord recordShow(Connection con, String recordId)throws Exception {
		String sql = "select * from t_repairrecord t1 where t1.recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		ResultSet rs=pstmt.executeQuery();
		RepairRecord record = new RepairRecord();
		if(rs.next()) {
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
		}
		return record;
	}
	
	public int recordAdd(Connection con, RepairRecord record)throws Exception {
		String sql = "insert into t_repairrecord values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, record.getStudentNumber());
		pstmt.setString(2, record.getStudentName());
		pstmt.setInt(3, record.getDormBuildId());
		pstmt.setString(4, record.getDormName());
		pstmt.setString(5, record.getDate());
		pstmt.setString(6, record.getDetail());
		return pstmt.executeUpdate();
	}
	
	public int recordDelete(Connection con, String recordId)throws Exception {
		String sql = "delete from t_repairrecord where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		return pstmt.executeUpdate();
	}
	
	public int recordUpdate(Connection con,RepairRecord record)throws Exception {
		String sql = "update t_repairrecord set studentNumber=?,studentName=?,dormBuildId=?,dormName=?,detail=? where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, record.getStudentNumber());
		pstmt.setString(2, record.getStudentName());
		pstmt.setInt(3, record.getDormBuildId());
		pstmt.setString(4, record.getDormName());
		pstmt.setString(5, record.getDetail());
		pstmt.setInt(6, record.getRecordId());
		return pstmt.executeUpdate();
	}
	
	
}
