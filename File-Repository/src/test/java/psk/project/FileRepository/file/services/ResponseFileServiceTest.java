package psk.project.FileRepository.file.services;

import psk.project.FileRepository.IntegrationAbstractTest;

class ResponseFileServiceTest extends IntegrationAbstractTest {
//
//    private final FileDAO fileDAO;
//    private final ResponseFileService sut;
//
//    ResponseFileServiceTest() {
//        fileDAO = mock(FileDAO.class);
//        sut = new ResponseFileService(
//                fileDAO
//        );
//    }
//
//    @Test
//    void invoke_get_in_file_DAO_and_check_correct_return() {
//        //given
//        when(fileDAO.get(anyString())).thenReturn(Optional.of(FILE));
//
//        //when
//        FileResponse response = sut.getFileInfoById(anyString());
//
//        //then
//        verify(fileDAO, times(1)).get(anyString());
//        assertThat(response)
//                .hasSamefilenameAs(FILE.getFileName())
//                .hasSameFileIdAs(FILE_ID.toString())
//                .hasSameOwnerIdAs(FILE.getDefaultUser().getDefaultUserID().toString())
//                .hasSameCommentAs(FILE_COMMENT)
//                .hasSameSizeAs(FILE.getSize())
//                .hasSameDescriptionAs(FILE.getDescription())
//                .hasSamePathAs(FILE.getPath());
//    }
//
//    @Test
//    void invoke_get_all_in_file_DAO() {
//        //when
//        sut.getAllFiles();
//
//        //then
//        verify(fileDAO, times(1)).getAll();
//    }
//
//    @Test
//    void throw_File_not_found_exception_when_file_with_id_not_found() {
//        //given
//        when(fileDAO.get(anyString())).thenReturn(Optional.empty());
//
//        //when & then
//        Assertions.assertThrows(
//                FileNotFoundException.class,
//                () -> sut.getFileInfoById(anyString()),
//                String.format("The file was not been found with id:'%s'. Skontaktuj się z administartorem!",FILE_ID));
//    }
}