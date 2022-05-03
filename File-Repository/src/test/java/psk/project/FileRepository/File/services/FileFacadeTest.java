package psk.project.FileRepository.File.services;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.IntegrationAbstractTest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.mockito.Mockito.*;

class FileFacadeTest extends IntegrationAbstractTest {
    private final UploadFileService uploadFileService;
    private final ResponseFileService responseFileService;
    private final DownloadFileService downloadFileService;
    private final FileFacade sut;

    FileFacadeTest() {
        uploadFileService = mock(UploadFileService.class);
        responseFileService = mock(ResponseFileService.class);
        downloadFileService = mock(DownloadFileService.class);
        sut = new FileFacade(
                uploadFileService,
                responseFileService,
                downloadFileService
        );
    }


    @Test
    void invoke_upload_file() {
        //given
        FileDTO fileDTO = prepareFileDTO();

        //when
        sut.saveFile(fileDTO);

        //then
        verify(uploadFileService, times(1)).uploadFile(fileDTO);
    }

    @Test
    void invoke_get_file_info_by_id() {
        //given
        String id = FILE_ID.toString();

        //when
        sut.getFileInfoById(id);

        //then
        verify(responseFileService, times(1)).getFileInfoById(id);
    }

    @Test
    void invoke_get_all_files() {
        //given & then
        sut.getAllFiles();

        //then
        verify(responseFileService, times(1)).getAllFiles();
    }

    @Test
    void invoke_download_file() {
        //given
        String id = FILE_ID.toString();

        //when
        sut.downloadFile(id);

        //then
        verify(downloadFileService, times(1)).downloadFile(id);
    }

    @Test
    void invoke_download_files() {
        //given
        List<String> ids = List.of(FILE_ID.toString());
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();

        //when
        sut.downloadFiles(httpServletResponse, ids);

        //then
        verify(downloadFileService, times(1)).downloadFiles(ids, httpServletResponse);
    }


    @Test
    void invoke_mexico(){
        //when
        sut.mexicanoFile();

        //then
        verify(downloadFileService,times(1)).mexicanoFilm();
    }

}