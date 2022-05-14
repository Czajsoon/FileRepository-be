package psk.project.FileRepository.File.services;

import org.junit.jupiter.api.Test;
import psk.project.FileRepository.File.DAO.FileDAO;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.IntegrationAbstractTest;

import static org.mockito.Mockito.*;
import static psk.project.FileRepository.File.services.FileResponseAssertion.*;

class UploadFileServiceTest extends IntegrationAbstractTest {

    private final FileDAO fileDAO;
    private final UploadFileService sut;

    UploadFileServiceTest() {
        fileDAO = mock(FileDAO.class);
        sut = new UploadFileService(
                fileDAO
        );
    }

    @Test
    void invoke_save_and_check_correct_file_response() {
        //given
        FileDTO fileDTO = prepareFileDTO();
        fileDTO.setAdditionalPath(null);
        FileResponse fileResponse = prepareFileResponse();
        when(fileDAO.save(any())).thenReturn(fileResponse);

        //when
        sut.uploadFile(fileDTO);

        //then
        verify(fileDAO, times(1)).save(fileDTO);
//        assertThat(response)
//                .hasSamefilenameAs(fileDTO.getFileName())
//                .hasSameFileIdAs(FILE_ID.toString())
//                .hasSameOwnerIdAs(fileDTO.getOwnerId())
//                .hasSameCommentAs(FILE_COMMENT)
//                .hasSameSizeAs(fileDTO.getSize())
//                .hasSameDescriptionAs(fileDTO.getDescription())
//                .hasSamePathAs(fileDTO.getPath());
    }

}