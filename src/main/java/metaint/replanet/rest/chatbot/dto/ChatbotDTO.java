package metaint.replanet.rest.chatbot.dto;

public class ChatbotDTO {

    private int questionCode;
    private String questionContent;
    private String answerContent;

    public ChatbotDTO() {
    }

    public ChatbotDTO(int questionCode, String questionContent, String answerContent) {
        this.questionCode = questionCode;
        this.questionContent = questionContent;
        this.answerContent = answerContent;
    }

    public int getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(int questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    @Override
    public String toString() {
        return "ChatbotDTO{" +
                "questionCode=" + questionCode +
                ", questionContent='" + questionContent + '\'' +
                ", answerContent='" + answerContent + '\'' +
                '}';
    }
}
