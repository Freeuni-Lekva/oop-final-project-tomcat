package ge.edu.freeuni.responses;

public class ServiceActionResponse {
    private final boolean success;
    private final String errorMessage;

    public ServiceActionResponse(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ServiceActionResponse{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
