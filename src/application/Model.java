package application;

import javafx.beans.property.SimpleStringProperty;

import java.io.File;

public class Model {

    private SimpleStringProperty filePath;
    private SimpleStringProperty md5sum;
    private SimpleStringProperty sha1sum;
    private SimpleStringProperty sha256sum;
    private SimpleStringProperty sha512sum;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        setFilePath(file.getAbsolutePath());
    }

    public String getFilePath() {
        return filePath.get();
    }

    public SimpleStringProperty filePathProperty() {
        if (filePath == null) {
            filePath = new SimpleStringProperty("");
        }
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath.set(file.getAbsolutePath());
    }

    public String getSha1sum() {
        return sha1sum.get();
    }

    public SimpleStringProperty sha1sumProperty() {
        if (sha1sum == null) {
            sha1sum = new SimpleStringProperty("");
        }
        return sha1sum;
    }

    public void setSha1sum(String sha1sum) {
        this.sha1sum.set(sha1sum);
    }

    public String getSha256sum() {
        return sha256sum.get();
    }

    public SimpleStringProperty sha256sumProperty() {
        if (sha256sum == null) {
            sha256sum = new SimpleStringProperty("");
        }
        return sha256sum;
    }

    public void setSha256sum(String sha256sum) {
        this.sha256sum.set(sha256sum);
    }

    public String getSha512sum() {
        return sha512sum.get();
    }

    public SimpleStringProperty sha512sumProperty() {
        if (sha512sum == null) {
            sha512sum = new SimpleStringProperty("");
        }
        return sha512sum;
    }

    public void setSha512sum(String sha512sum) {
        this.sha512sum.set(sha512sum);
    }

    public String getMd5sum() {
        return md5sum.get();
    }

    public SimpleStringProperty md5sumProperty() {
        if (md5sum == null) {
            md5sum = new SimpleStringProperty("");
        }
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum.set(md5sum);
    }
}
