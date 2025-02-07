package io.github.ailearner.utils;

import java.io.File;

public enum DirectoryReference {
    RESOURCE("/src/main/resources"),
    TARGET("/target/"),
    SRC_MAIN("/src/main");

    private final String filePath;

    DirectoryReference(final String filePath) {
        this.filePath = System.getProperty("user.dir") + "/" + filePath;
    }

    public File getFile(String fileName) {
        return new File(this.filePath + "/" + fileName);
    }

    public String getAbsolutePath(final String fileName) {
        return getFile(fileName).getAbsolutePath();
    }
}
