package psk.project.FileRepository.file.dao;

import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.File;

import java.util.UUID;

import static psk.project.FileRepository.RandomUtilTest.*;

public class FakeFileBuilder extends File {

    public static final String rootPath = System.getProperty("user.dir") + "/server/";

    public FakeFileBuilder() {
        setFileID(randomUUID());
        setPath("/add/path/");
        setFileName(randomString(5));
        setDescription(randomString(10));
        setFileFormat(randomString(3).toUpperCase());
        setSize(randomLong());
        setComment(randomString(8));
        setTotalPath(rootPath + getFileName());
        setDefaultUser(null);
    }

    public FakeFileBuilder withId(UUID id) {
        setFileID(id);
        return this;
    }

    public FakeFileBuilder withPath(String path) {
        setPath(path);
        return this;
    }

    public FakeFileBuilder withFilename(String filename) {
        setFileName(filename);
        setTotalPath(rootPath + getFileName());
        return this;
    }

    public FakeFileBuilder withDescription(String description) {
        setDescription(description);
        return this;
    }

    public FakeFileBuilder withFileFormat(String fileFormat) {
        setFileFormat(fileFormat);
        return this;
    }

    public FakeFileBuilder withSize(Long size) {
        setSize(size);
        return this;
    }

    public FakeFileBuilder withComment(String comment) {
        setComment(comment);
        return this;
    }

    public FakeFileBuilder withUser(DefaultUser user){
        setDefaultUser(user);
        return this;
    }

}
