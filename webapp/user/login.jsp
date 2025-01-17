<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="kr">
<head>
  <%@ include file="/layout/header.html" %>
</head>
<body>

<%@ include file="/layout/nav.jsp" %>

<div class="container" id="main">
  <div class="col-md-6 col-md-offset-3">
    <div class="panel panel-default content-main">
      <form name="question" method="post" action="/user/login">
        <div class="form-group">
          <label for="userId">사용자 아이디</label>
          <input class="form-control" id="userId" name="userId" placeholder="User ID">
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-success clearfix pull-right">로그인</button>
        <div class="clearfix"/>
      </form>
    </div>
  </div>
</div>

<%@ include file="/layout/footer.html" %>
</body>
</html>