<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var studentNumber = document.getElementById("studentNumber").value;
		var detail = document.getElementById("detail").value;
		if(studentNumber==""||detail==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(2)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${student.studentId!=null }">
				<font style="color:white">修改报修记录</font>
			</c:when>
			<c:otherwise>
				<font style="color:white">添加报修记录</font>
			</c:otherwise>
		</c:choose>
		</div>
		<form action="record?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="recordId" name="recordId" value="${record.recordId }"/>
					<table align="center">
						<tr style="color:white">
							<td><font color="red">*</font>学号：</td>
							<td><input type="text" id="studentNumber"  name="studentNumber" value="${record.studentNumber==null?studentNumber:record.studentNumber }"  style="margin-top:5px;height:30px;" ${studentNumber!=null?'readonly':'' }/></td>
						</tr>
						<tr style="color:white">
							<td><font color="red">*</font>日期：</td>
							<td><input type="text" id="date"  name="date" value="${record.date==null?date:record.date }"  style="margin-top:5px;height:30px;" readonly="readonly" /></td>
						</tr>
						<tr style="color:white">
							<td><font color="red">*</font>备注：</td>
							<td><input type="text" id="detail"  name="detail" value="${record.detail }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='repairManager'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>