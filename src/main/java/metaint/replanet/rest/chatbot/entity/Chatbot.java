package metaint.replanet.rest.chatbot.entity;

import javax.persistence.*;

@Entity(name = "tblChatBot")
@Table(name = "tbl_chat_bot")
public class Chatbot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_code")
    private int questionCode;
    @Column(name = "question_content")
    private String questionContent;
    @Column(name = "answer_content")
    private String answerContent;

    protected Chatbot() {}

    public int getQuestionCode() {
        return questionCode;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    @Override
    public String toString() {
        return "Chatbot{" +
                "questionCode=" + questionCode +
                ", questionContent='" + questionContent + '\'' +
                ", answerContent='" + answerContent + '\'' +
                '}';
    }
}
