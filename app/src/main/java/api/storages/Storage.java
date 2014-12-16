package api.storages;


public abstract class Storage {

    public static final String STORAGE_DROPBOX = "STORAGE_DROPBOX";
    public static final String STORAGE_YANDEX = "STORAGE_YANDEX";

    public static Storage create(String storageName) {
        if (storageName.equals(STORAGE_DROPBOX))
            return new StorageDropbox();
        if (storageName.equals(STORAGE_YANDEX))
            return new StorageYandex();
        return null;
    }


    public abstract String getAuthUrl();
    public abstract String getAuthRedirectUrl();
    public abstract String getMetadataUrl(String accessToken, String path);

}
