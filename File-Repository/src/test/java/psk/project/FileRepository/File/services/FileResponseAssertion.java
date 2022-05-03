package psk.project.FileRepository.File.services;

import org.assertj.core.api.AbstractAssert;
import psk.project.FileRepository.File.models.FileResponse;

public class FileResponseAssertion extends AbstractAssert<FileResponseAssertion, FileResponse> {

    private FileResponseAssertion(FileResponse fileResponse) {
        super(fileResponse, FileResponseAssertion.class);
    }

    public static FileResponseAssertion assertThat(FileResponse fileResponse){
        return new FileResponseAssertion(fileResponse);
    }

    public FileResponseAssertion hasSameOwnerIdAs(String ownerId){
        isNotNull();
        if(!actual.getOwnerId().equals(ownerId)){
            failWithMessage("Expected file response to have owner id '%s' but was '%s'",
                    ownerId, actual.getOwnerId());
        }
        return this;
    }

    public FileResponseAssertion hasSameFileIdAs(String fileId){
        isNotNull();
        if(!actual.getFileId().equals(fileId)){
            failWithMessage("Expected file response to have file id '%s' but was '%s'",
                    fileId, actual.getFileId());
        }
        return this;
    }

    public FileResponseAssertion hasSameSizeAs(Long size){
        isNotNull();
        if(!actual.getSize().equals(size)){
            failWithMessage("Expected file response to have size '%s' but was '%s'",
                    size, actual.getSize());
        }
        return this;
    }

    public FileResponseAssertion hasSamePathAs(String path){
        isNotNull();
        if(!actual.getPath().equals(path)){
            failWithMessage("Expected file response to have path '%s' but was '%s'",
                    path, actual.getPath());
        }
        return this;
    }

    public FileResponseAssertion hasSamefilenameAs(String filename){
        isNotNull();
        if(!actual.getFileName().equals(filename)){
            failWithMessage("Expected file response to have filename '%s' but was '%s'",
                    filename, actual.getFileName());
        }
        return this;
    }

    public FileResponseAssertion hasSameDescriptionAs(String description){
        isNotNull();
        if(!actual.getDescription().equals(description)){
            failWithMessage("Expected file response to have description '%s' but was '%s'",
                    description, actual.getDescription());
        }
        return this;
    }

    public FileResponseAssertion hasSameCommentAs(String comment){
        isNotNull();
        if(!actual.getComment().equals(comment)){
            failWithMessage("Expected file response to have comment '%s' but was '%s'",
                    comment, actual.getComment());
        }
        return this;
    }
}
