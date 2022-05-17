package psk.project.FileRepository.file.services;

import org.junit.jupiter.api.Test;
import psk.project.FileRepository.file.dao.FileDAO;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.IntegrationAbstractTest;

import static org.mockito.Mockito.*;

class UploadFileServiceTest extends IntegrationAbstractTest {
//
//    private final FileDAO fileDAO;
//    private final UploadFileService sut;
//
//    UploadFileServiceTest() {
//        fileDAO = mock(FileDAO.class);
//        sut = new UploadFileService(
//                fileDAO
//        );
//    }
//
//    @Test
//    void invoke_save_and_check_correct_file_response() {
//        //given
//        FileDTO fileDTO = prepareFileDTO();
//        fileDTO.setAdditionalPath(null);
//        FileResponse fileResponse = prepareFileResponse();
//        when(fileDAO.save(any())).thenReturn(fileResponse);
//
//        //when
//        sut.uploadFile(fileDTO);
//
//        //then
//        verify(fileDAO, times(1)).save(fileDTO);
////        assertThat(response)
////                .hasSamefilenameAs(fileDTO.getFileName())
////                .hasSameFileIdAs(FILE_ID.toString())
////                .hasSameOwnerIdAs(fileDTO.getOwnerId())
////                .hasSameCommentAs(FILE_COMMENT)
////                .hasSameSizeAs(fileDTO.getSize())
////                .hasSameDescriptionAs(fileDTO.getDescription())
////                .hasSamePathAs(fileDTO.getPath());
//    }

}