<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
  <form id="queryForm">
  <input type="hidden" name="pageNum" value="1">
  </form>
  <form action="/collect/collectList" method="post">
<input type="hidden" name="pageNum">
</form>
  	<table class="table">
  <thead>
    <tr>
      <th scope="col"><input type="checkbox" value="" id="chkALL" name="chkALL"></th>
      <th scope="col">$</th>
      <th scope="col">标题</th>
      <th scope="col">url</th>
      <th scope="col">收藏时间</th>
      <th scope="col">用户id</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach items="${pageInfo.list }" var="item">
       <tr>
	      <th><input type="checkbox" value="${item.id }"  name="chk_list"></th>
	      <th scope="row">${item.id }</th>
	      <td title="${item.text }">${fn:substring(item.text,0,10) }</td>
	      
	      <td>${item.url}</td>
	      <td><fmt:formatDate value="${item.created }" pattern="yyyy-MM-dd HH:mm"/></td>
	      <td>${item.user_id}</td>
	      
	      <td>
	      		<button type="button" class="btn btn-primary" onclick="deleteCollect('${item.id}')">删除</button>
	      		<button type="button" class="btn btn-primary" onclick="view('${item.id}')">查看</button>
	      </td>
	    </tr>
   	</c:forEach>
   	
  </tbody>
</table>
			
			

<div class="row">
	<div class="col-2">
		<button type="button" class="btn btn-danger" onclick="delAlert();">删除</button>
	</div>
	<div class="col-10">
		<jsp:include page="../common/page.jsp"></jsp:include>
	</div>
</div>


<div class="modal" tabindex="-1" role="dialog" id="delModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">确认框</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	你确认删除选择的数据吗？
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="batchDel();">确认删除</button>
      </div>
    </div>
  </div>
</div>

<script src="/public/js/checkbox.js?v1.00"></script>
<script>
	
	
function fenye(currentPage) {
	$("[name=currentPage]").val(currentPage);
	$("form").submit();
}
	
	function gotoPage(pageNo){
		$("[name=pageNum]").val(pageNo);
		query();
	}
	
	function view(id){
		window.open("/article/"+id+".html");
	}
	

	
	function deleteCollect(id) {
		$.post("/collect/deleColl",{id:id},function(res){
			if(res.result){
				alert("删除成功");
				localhost.href="/collect/collectList";
			}else{
				alert("删除失败");
			}
		});
	}
	
</script>