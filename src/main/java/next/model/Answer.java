package next.model;

import java.time.LocalDateTime;

public class Answer {
    private long answerId;
    private String writer;
    private String contents;
    private LocalDateTime createdDate;
    private long questionId;

    public Answer(long answerId, String writer, String contents, LocalDateTime createdDate, long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(String writer, String contents, long questionId) {
        this(0, writer, contents, LocalDateTime.now(), questionId);
    }

    public long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public long getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return answerId == answer.answerId;
    }

    @Override
    public int hashCode() {
        return (int) (answerId ^ (answerId >>> 32));
    }
}
