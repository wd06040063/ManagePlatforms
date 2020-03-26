<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function dormBuildDelete(dormBuildId) {
		if(confirm("您确定要删除这个宿舍楼吗？")) {
			window.location="dormBuild?action=delete&dormBuildId="+dormBuildId;
		}
	}
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			<font color="white">宿舍楼管理</font>
		</div>
		<form name="myForm" class="form-search" method="post" action="dormBuild?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='dormBuild?action=preSave'">添加</button>
				<span class="data_search">
					<font color="white">名称:&nbsp;&nbsp;</font><input id="s_dormBuildName" name="s_dormBuildName" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_dormBuildName }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table  table-bordered  datatable">
				<thead>
					<tr>
						<th width="15%"><font color="white">编号</font></th>
						<th><font color="white">名称</font></th>
						<th><font color="white">简介</font></th>
						<th width="20%"><font color="white">操作</font></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach  varStatus="i" var="dormBuild" items="${dormBuildList }">
					<tr>
						<td><font color="white">${i.count+(page-1)*pageSize }</font></td>
						<td><font color="white">${dormBuild.dormBuildName }</font></td>
						<td><font color="white">${dormBuild.detail==null||dormBuild.detail==""?"无":dormBuild.detail }</font></td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild?action=manager&dormBuildId=${dormBuild.dormBuildId }'">管理员</button>&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild?action=preSave&dormBuildId=${dormBuild.dormBuildId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDelete(${dormBuild.dormBuildId})">删除</button></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div>
</div>