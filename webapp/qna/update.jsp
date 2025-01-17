<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/layout/header.html" %>
</head>
<body>

<%@ include file="/layout/nav.jsp" %>

<div class="container" id="main">
   <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/question/update">
              <input type="hidden" name="questionId" value="${question.questionId}">
              <div class="form-group">
                  <label for="writer">글쓴이</label>
                  <input class="form-control" id="writer" name="writer" placeholder="글쓴이" value="${question.writer}" readonly />
              </div>
              <div class="form-group">
                  <label for="title">제목</label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="제목" maxlength="100" value="${question.title}" />
              </div>
              <div class="form-group">
                  <label for="contents">내용</label>
                  <textarea name="contents" id="contents" rows="5" class="form-control" maxlength="5000">${question.contents}</textarea>
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

<%@ include file="/layout/footer.html" %>
</body>
</html>