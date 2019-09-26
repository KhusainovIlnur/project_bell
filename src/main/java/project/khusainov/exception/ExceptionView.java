package project.khusainov.exception;

public class ExceptionView {
    private String error;

    public ExceptionView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
