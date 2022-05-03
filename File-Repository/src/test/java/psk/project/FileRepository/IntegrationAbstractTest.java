package psk.project.FileRepository;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.File.DAO.FakeFileBuilder;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.defaultUser.FakeDefaultUserBuilder;

import java.util.UUID;

import static psk.project.FileRepository.RandomUtilTest.*;

public abstract class IntegrationAbstractTest {

    protected final UUID FILE_ID = randomUUID();
    protected final UUID USER_ID = randomUUID();
    protected final String FILE_DESCRIPTION = randomString(6);
    protected final String FILE_COMMENT = randomString(6);
    protected final Long FILE_SIZE = randomLong();
    protected final String FILE_PATH = "/add/path/";
    protected final File FILE = prepareFileData();
    protected final DefaultUser USER = prepareUser();

    protected final String FILE_NAME = "file.txt";

    protected FileDTO prepareFileDTO(){
        MultipartFile multipartFile = new MockMultipartFile(FILE_NAME,randomByteArray());
        return FileDTO.builder()
                .file(multipartFile)
                .comment(FILE_COMMENT)
                .fileName(FILE_NAME)
                .description(FILE_DESCRIPTION)
                .size(FILE_SIZE)
                .ownerId(USER_ID)
                .description(FILE_DESCRIPTION)
                .path(FILE_PATH)
                .additionalPath("")
                .build();
    }

    protected File prepareFileData() {
        return new FakeFileBuilder()
                .withId(FILE_ID)
                .withUser(prepareUser())
                .withComment(FILE_COMMENT)
                .withSize(FILE_SIZE)
                .withDescription(FILE_DESCRIPTION)
                .withFilename(FILE_NAME);
    }

    protected DefaultUser prepareUser() {
        return new FakeDefaultUserBuilder()
                .withId(USER_ID);
    }

    protected FileResponse prepareFileResponse(){
        return FileResponse.of(FILE);
    }
}
