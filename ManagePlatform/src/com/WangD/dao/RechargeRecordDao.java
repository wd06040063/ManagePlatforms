package com.WangD.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.WangD.model.DormBuild;
import com.WangD.model.RechargeRecord;
import com.WangD.util.StringUtil;

public class RechargeRecordDao {
	public List<RechargeRecord> recordList(Connection con, RechargeRecord s_rechargerecord)throws Exception {
		List<RechargeRecord> recordList = new ArrayList<RechargeRecord>();
		StringBuffer sb = new StringBuffer("select * from t_rechargerecord t1");
		if(StringUtil.isNotEmpty(s_rechargerecord.getStudentNumber())) {
			sb.append(" and t1.studentNumber like '%"+s_rechargerecord.getStudentNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_rechargerecord.getStudentName())) {
			sb.append(" and t1.studentName like '%"+s_rechargerecord.getStudentName()+"%'");
		}
		if(s_rechargerecord.getDormBuildId()!=0) {
			sb.append(" and t1.dormBuildId="+s_rechargerecord.getDormBuildId());
		}
		if(StringUtil.isNotEmpty(s_rechargerecord.getDate())) {
			sb.append(" and t1.date="+s_rechargerecord.getDate());
		}
		if(StringUtil.isNotEmpty(s_rechargerecord.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_rechargerecord.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_rechargerecord.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_rechargerecord.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			RechargeRecord record=new RechargeRecord();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("data"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<RechargeRecord> recordListWithBuild(Connection con, RechargeRecord s_record, int buildId)throws Exception {
		List<RechargeRecord> recordList = new ArrayList<RechargeRecord>();
		StringBuffer sb = new StringBuffer("select * from t_rechargerecord t1");
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
			RechargeRecord record=new RechargeRecord();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("data"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<RechargeRecord> recordListWithNumber(Connection con, RechargeRecord s_record, String studentNumber)throws Exception {
		List<RechargeRecord> recordList = new ArrayList<RechargeRecord>();
		StringBuffer sb = new StringBuffer("select * from t_chargerecord t1");
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
			RechargeRecord record=new RechargeRecord();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("data"));
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

	
	public RechargeRecord recordShow(Connection con, String recordId)throws Exception {
		String sql = "select * from t_rechargerecord t1 where t1.recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		ResultSet rs=pstmt.executeQuery();
		RechargeRecord record = new RechargeRecord();
		if(rs.next()) {
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("data"));
			record.setDetail(rs.getString("detail"));
		}
		return record;
	}
	
	public int recordAdd(Connection con, RechargeRecord record)throws Exception {
		String sql = "insert into t_rechargerecord values(null,?,?,?,?,?,?)";
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
		String sql = "delete from t_rechargerecord where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		return pstmt.executeUpdate();
	}
	
	public int recordUpdate(Connection con,RechargeRecord record)throws Exception {
		String sql = "update t_rechargerecord set studentNumber=?,studentName=?,dormBuildId=?,dormName=?,detail=? where recordId=?";
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
