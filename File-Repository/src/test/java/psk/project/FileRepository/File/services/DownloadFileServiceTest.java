package psk.project.FileRepository.File.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import psk.project.FileRepository.File.DAO.FileDAO;
import psk.project.FileRepository.File.exceptions.FileNotFoundException;
import psk.project.FileRepository.IntegrationAbstractTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

class DownloadFileServiceTest extends IntegrationAbstractTest {

    private final FileDAO fileDAO;
    private final DownloadFileService sut;

    private static final String ROOT_PATH = System.getProperty("user.dir");

    DownloadFileServiceTest() {
        fileDAO = mock(FileDAO.class);
        sut = new DownloadFileService(
                fileDAO
        );
    }

    @Test
    void invoke_get_total_path_file_from_file_DAO() {
        //given
        when(fileDAO.getTotalPathFile(any())).thenReturn(ROOT_PATH + "/src/test/java/psk/project/FileRepository/File/testFile/file.txt");

        //when
        sut.downloadFile(any());

        //then
        verify(fileDAO, times(1)).getTotalPathFile(any());
    }

    @Test
    void throw_File_not_found_exception_when_download_file_not_found() {
        //given
        when(fileDAO.getTotalPathFile(FILE_ID.toString())).thenThrow(NoSuchElementException.class);

        //when & then

        Assertions.assertThrows(
                FileNotFoundException.class,
                () -> sut.downloadFile(FILE_ID.toString()),
                String.format("The file was not been found with id:'%s'. Skontaktuj się z administartorem!", FILE_ID));
    }

    @Test
    void invoke_get_all_and_get_total_path_file() {
        //given
        List<String> list = List.of(FILE_ID.toString());
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(fileDAO.getTotalPathFile(anyString())).thenReturn(ROOT_PATH + "/src/test/java/psk/project/FileRepository/File/testFile/file.txt");
        when(fileDAO.getAll()).thenReturn(List.of(prepareFileResponse()));

        //when
        sut.downloadFiles(list, response);

        //then
        verify(fileDAO,times(1)).getTotalPathFile(FILE_ID.toString());

    }


    @Test
    void invoke_mexicano(){
        //given & when
        sut.mexicanoFilm();

        //then
        verify(fileDAO,times(1)).mexicano();
    }
}