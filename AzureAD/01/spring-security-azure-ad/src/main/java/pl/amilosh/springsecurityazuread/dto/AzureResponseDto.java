package pl.amilosh.springsecurityazuread.dto;

public class AzureResponseDto {

    private String code;
    private String state;
    private String session_state;

    public AzureResponseDto() {
    }

    public AzureResponseDto(String code, String state, String session_state) {
        this.code = code;
        this.state = state;
        this.session_state = session_state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSession_state() {
        return session_state;
    }

    public void setSession_state(String session_state) {
        this.session_state = session_state;
    }
}
