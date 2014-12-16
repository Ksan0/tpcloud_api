package api.files;


import java.util.ArrayList;


public class FileMetadata {
    private String storageName;
    private String name;
    private String storagePath;
    private boolean isDir;
    private long size;
    private String mimeType;

    private ArrayList<FileMetadata> containFiles = new ArrayList<FileMetadata>();

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStoragePath(String path) {
        storagePath = path;
        int pos = path.lastIndexOf('/');
        name = path.substring(pos+1);
    }

    public String getName() {
        return name;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setDir(boolean isDir) {
        this.isDir = isDir;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void addContainFile(FileMetadata file) {
        containFiles.add(file);
    }
}
