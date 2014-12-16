package api.storages;


public class StorageYandex extends Storage {
    
    private static final String APP_KEY = "6d949608745a427ea749b1570a674b1d";
    private static final String AUTH_REDIRECT_URL= "tpyacloud://localhost";
    private static final String AUTH_URL = String.format(
            "https://oauth.yandex.com/authorize?" +
            "response_type=token&" +
            "client_id=%s&",

            APP_KEY
    );
    private static final String METADATA_PATH = "https://cloud-api.yandex.net/v1/disk/resources?" +
                                                "path=%s&" +
                                                "access_token=%s";
    
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
        return String.format(METADATA_PATH, path, accessToken);
    }
}
