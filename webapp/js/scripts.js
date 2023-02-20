
$(document).ready(function(){/* jQuery toggle layout */
  $('#btnToggle').click(function(){
    if ($(this).hasClass('on')) {
      $('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
      $(this).removeClass('on');
    }
    else {
      $('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
      $(this).addClass('on');
    }
  });

  $(".answerWrite input[type=submit]").click(addAnswer);
  $(".qna-comment").on('click', '.form-delete', deleteAnswer);
});

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
      ? args[number]
      : match
      ;
  });
};

function addAnswer(e) {
  e.preventDefault();

  const queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error : onError,
    success : onSuccess
  });
}

function onSuccess(json, status) {
  const { answer } = json
  const answerTemplate = $("#answerTemplate").html();
  const template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
  alert("error");
}

function deleteAnswer(e) {
  e.preventDefault();

  const queryString = $(this).closest(".form-delete").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : queryString,
    dataType : 'json',
    error : onError,
    success : (json, status) => {
      const { result } = json
      if (result.status) {
        $(this).closest(".article").remove();
      } else {
        alert(result.message);
      }
    }
  });
}
