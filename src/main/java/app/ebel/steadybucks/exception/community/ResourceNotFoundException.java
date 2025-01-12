package app.ebel.steadybucks.exception.community;

public class ResourceNotFoundException extends CommunityException{

    private static final String DEFAULT_MESSAGE = "리소스를 찾을 수 없습니다.";
    private static final String ERROR_CODE = "RESOURCE_NOT_FOUND";
    public ResourceNotFoundException() {
        super(DEFAULT_MESSAGE, ERROR_CODE);
    }

    public ResourceNotFoundException(String resourceName) {
//        DEFAULT_MESSAGE + resourceName
        super(resourceName + DEFAULT_MESSAGE, ERROR_CODE);
    }
}
