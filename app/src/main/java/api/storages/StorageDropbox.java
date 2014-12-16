package api.storages;


public class StorageDropbox extends Storage {
    private static final String APP_KEY = "601z07rjcs1mydq";
    private static final String AUTH_REDIRECT_URL = "tpdbcloud://localhost";
    private static final String AUTH_URL = String.format(
            "https://www.dropbox.com/1/oauth2/authorize?" +
            "response_type=token&" +
            "client_id=%s&" +
            "redirect_uri=%s",

            APP_KEY,
            AUTH_REDIRECT_URL
    );
    private static final String METADATA_URL = "https://api.dropbox.com/1/metadata/auto/%s?access_token=%s";


    @Override
    public String getAuthUrl() {
        return AUTH_URL;
    }

    @Override
    public String getAuthRedirectUrl() {
        return AUTH_REDIRECT_URL;
    }

    @Override
    public String getMetadataUrl(String accessToken, String path) {
        return String.format(METADATA_URL, path, accessToken);
    }
}