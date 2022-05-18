package psk.project.FileRepository.utils;

public final class StringOperationUtils {

    private StringOperationUtils(){}

    public static String getFileExtention(String filename) {
        StringBuilder extention = new StringBuilder();
        boolean flag = false;
        for (int i = filename.length() - 1; i > 0; i--) {
            if (flag) break;
            if (filename.charAt(i) == '.') flag = true;
            extention.append(filename.charAt(i));
        }
        extention.reverse();
        return extention.toString();
    }

    public static String getDefaultPhotoLink(){
        return System.getProperty("user.dir") + "/photos/defaultPhoto.png";
    }
}
