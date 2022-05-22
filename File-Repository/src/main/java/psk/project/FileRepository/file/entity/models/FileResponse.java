package psk.project.FileRepository.file.entity.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.File;

import java.math.BigInteger;
import java.util.List;

@Builder
@Getter
@Setter
public class FileResponse {
    private String ownerId;
    private String fileId;
    private BigInteger size;
    private String path;
    private String ownerName;
    private String ownerSurname;
    private String fileName;
    private String fileFormat;
    private String description;
    private String comment;
    private String fileTotalPath;
    private List<FileAccessUser> accessUsers;
    private Boolean editable;
    private Boolean shared;

    @Builder
    @Getter
    @Setter
    static class FileAccessUser{
        private String id;
        private String name;
        private String surname;
        private String shareLink;

        public static FileAccessUser of(DefaultUser user){
            return FileAccessUser.builder()
                    .id(user.getDefaultUserID().toString())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .shareLink(user.getShareLink())
                    .build();
        }
    }

    public static FileResponse of(File file,String userId) {
        String description = file.getDescription();
        if (description == null)
            description = "Brak";

        String path = "Folder główny";
        if (!file.getPath().equals("Folder główny"))
            path = file.getPath();

        List<FileAccessUser> accessUserList = file.getDefaultUsers()
                .stream()
                .map(FileAccessUser::of)
                .toList();

        return FileResponse.builder()
                .description(description)
                .size(file.getSize())
                .fileFormat(file.getFileFormat())
                .shared(!file.getDefaultUsers().isEmpty())
                .editable(file.getDefaultUser()
                        .getDefaultUserID()
                        .toString()
                        .equals(userId))
                .ownerName(file.getDefaultUser().getName())
                .ownerSurname(file.getDefaultUser().getSurname())
                .ownerId(file.getDefaultUser()
                        .getDefaultUserID()
                        .toString())
                .accessUsers(accessUserList)
                .fileTotalPath(System.getProperty("user.dir") + "/server/" + file.getTotalPath())
                .path(path)
                .fileName(file.getPureFileName())
                .comment(file.getComment())
                .fileId(file.getFileId().toString())
                .build();
    }

    public static FileResponse of(File file) {
        String description = file.getDescription();
        if (description == null)
            description = "Brak";

        String path = "Folder główny";
        if (!file.getPath().equals("Folder główny"))
            path = file.getPath();

        List<FileAccessUser> accessUserList = file.getDefaultUsers()
                .stream()
                .map(FileAccessUser::of)
                .toList();

        return FileResponse.builder()
                .description(description)
                .size(file.getSize())
                .fileFormat(file.getFileFormat())
                .shared(!file.getDefaultUsers().isEmpty())
                .editable(false)
                .ownerName(file.getDefaultUser().getName())
                .ownerSurname(file.getDefaultUser().getSurname())
                .ownerId(file.getDefaultUser()
                        .getDefaultUserID()
                        .toString())
                .accessUsers(accessUserList)
                .fileTotalPath(System.getProperty("user.dir") + "/server/" + file.getTotalPath())
                .path(path)
                .fileName(file.getPureFileName())
                .comment(file.getComment())
                .fileId(file.getFileId().toString())
                .build();
    }
}
