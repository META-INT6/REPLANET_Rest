package metaint.replanet.rest.common;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ResponseMessageDTO {

    private int httpStatusCode; // 상태코드
    private String message; // 간단한 응답 메시지
    private Map<String, Object> results; // 응답 결과 출력

    public ResponseMessageDTO() {
    }

    public ResponseMessageDTO(HttpStatus httpStatusCode, String message, Map<String, Object> results) {
        this.httpStatusCode = httpStatusCode.value();
        this.message = message;
        this.results = results;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseMessageDTO{" +
                "httpStatusCode=" + httpStatusCode +
                ", message='" + message + '\'' +
                ", results=" + results +
                '}';
    }
}
