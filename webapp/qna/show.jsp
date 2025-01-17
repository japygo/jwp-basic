<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="kr">
<head>
  <%@ include file="/layout/header.html" %>
</head>
<body>

<%@ include file="/layout/nav.jsp" %>

<div class="container" id="main">
  <div class="col-md-12 col-sm-12 col-lg-12">
    <div class="panel panel-default">
      <header class="qna-header">
        <h2 class="qna-title">${question.title}</h2>
      </header>
      <div class="content-main">
        <article class="article">
          <div class="article-header">
            <div class="article-header-thumb">
              <img src="https://graph.facebook.com/v2.3/100000059371774/picture" class="article-author-thumb" alt="">
            </div>
            <div class="article-header-text">
              <a href="/users/${question.questionId}/${question.writer}" class="article-author-name">${question.writer}</a>
              <a href="/questions/${question.questionId}" class="article-header-time" title="퍼머링크">
                <fmt:parseDate value="${question.createdDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parsedDate}" />
                <i class="icon-link"></i>
              </a>
            </div>
          </div>
          <div class="article-doc">
            ${question.contents}
          </div>
          <div class="article-util">
            <ul class="article-util-list">
              <li>
                <a class="link-modify-article" href="/question/updateForm?questionId=${question.questionId}">수정</a>
              </li>
              <li>
                <form class="form-delete" action="/question/delete" method="POST">
                  <input type="hidden" name="questionId" value="${question.questionId}">
                  <button class="link-delete-article" type="submit">삭제</button>
                </form>
              </li>
              <li>
                <a class="link-modify-article" href="/">목록</a>
              </li>
            </ul>
          </div>
        </article>

        <div class="qna-comment">
          <div class="qna-comment-slipp">
            <p class="qna-comment-count"><strong>${question.countOfAnswer}</strong>개의 의견</p>
            <div class="qna-comment-slipp-articles">
            <c:forEach items="${answers}" var="answer" varStatus="status">
              <article class="article">
                <div class="article-header">
                  <div class="article-header-thumb">
                    <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
                  </div>
                  <div class="article-header-text">
                    <a href="#" class="article-author-name">${answer.writer}</a>
                    <a href="#" class="article-header-time" title="퍼머링크">
                      <fmt:parseDate value="${answer.createdDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
                      <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parsedDate}" />
                    </a>
                  </div>
                </div>
                <div class="article-doc comment-doc">
                  <p>${answer.contents}</p>
                </div>
                <div class="article-util">
                  <ul class="article-util-list">
                    <li>
                      <a class="link-modify-article" href="/api/qna/updateAnswer?answerId=${answer.answerId}">수정</a>
                    </li>
                    <li>
                      <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                        <input type="hidden" name="answerId" value="${answer.answerId}">
                        <button type="submit" class="link-delete-article">삭제</button>
                      </form>
                    </li>
                  </ul>
                </div>
              </article>
            </c:forEach>
              <div class="answerWrite">
                <form class="submit-write" method="post" name="answer" action="/answer/create">
                  <input type="hidden" name="questionId" value="${question.questionId}">
                  <div class="form-group col-lg-4" style="padding-top:10px;">
                    <input class="form-control" id="writer" name="writer" placeholder="이름">
                  </div>
                  <div class="form-group" style="padding:14px;">
                    <textarea name="contents" id="contents" class="form-control" placeholder="Update your status"></textarea>
                  </div>
                  <input class="btn btn-success pull-right" type="submit" value="답변하기" />
                  <div class="clearfix" />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/template" id="answerTemplate">
  <article class="article">
    <div class="article-header">
      <div class="article-header-thumb">
        <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
      </div>
      <div class="article-header-text">
        {0}
        <div class="article-header-time">{1}</div>
      </div>
    </div>
    <div class="article-doc comment-doc">
      {2}
    </div>
    <div class="article-util">
      <ul class="article-util-list">
        <li>
          <a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
        </li>
        <li>
          <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
            <input type="hidden" name="answerId" value="{3}" />
            <button type="submit" class="link-delete-article">삭제</button>
          </form>
        </li>
      </ul>
    </div>
  </article>
</script>
<%@ include file="/layout/footer.html" %>
</body>
</html>