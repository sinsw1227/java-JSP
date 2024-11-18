<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<script>
	alert("<%= (String)request.getAttribute("error") %>\n 이전 화면으로 돌아갑니다");
	history.back();
</script>