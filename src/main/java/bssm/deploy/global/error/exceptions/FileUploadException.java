package bssm.deploy.global.error.exceptions;

public class FileUploadException extends InternalServerException {
    public FileUploadException() {
        super("파일 업로드에 실패했습니다.");
    }
}
