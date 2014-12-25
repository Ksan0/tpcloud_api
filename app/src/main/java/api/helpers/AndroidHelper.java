package api.helpers;



public class AndroidHelper {

    private static final String RESERVED_CHARS = "|\\?*<\":>+[]/'";

    public static String filePathToKey(String storageName, String filePath) {
        StringBuilder fileKey = new StringBuilder();
        String path = storageName + filePath;

        for (int i = 0; i < path.length(); i++) {

            if (path.charAt(i) == '%') {
                fileKey.append("%%");
            } else if (RESERVED_CHARS.indexOf( path.codePointAt(i) ) != -1) {
                fileKey.append('%');
                fileKey.append(path.codePointAt(i));
                fileKey.append('%');
            } else {
                fileKey.append(path.charAt(i));
            }
        }

        return fileKey.toString();
    }

}
