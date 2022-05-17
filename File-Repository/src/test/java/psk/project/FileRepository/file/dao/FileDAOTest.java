package psk.project.FileRepository.file.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import psk.project.FileRepository.defaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.IntegrationAbstractTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class FileDAOTest extends IntegrationAbstractTest {
//    private final FileRepository fileRepository;
//    private final DefaultUserRepository userRepository;
//    private final FileDAO sut;
//
//    FileDAOTest() {
//        this.fileRepository = mock(FileRepository.class);
//        this.userRepository = mock(DefaultUserRepository.class);
//        sut = new FileDAO(
//                fileRepository,
//                userRepository
//        );
//    }
//
//    @Test
//    void invoke_find_by_id_in_file_repository() {
//        //given & when
//        sut.get(FILE_ID.toString());
//
//        //then
//        verify(fileRepository, times(1)).findById(FILE_ID);
//    }
//
//    @Test
//    void invoke_find_all_by_id_by_uuids_in_file_repository() {
//        //given
//        List<UUID> uuids = List.of(FILE_ID);
//
//        //when
//        sut.getAll(uuids.stream().map(UUID::toString).toList());
//
//        //then
//        verify(fileRepository, times(1)).findAllById(uuids);
//    }
//
//    @Test
//    void invoke_get_all_in_file_repository() {
//        //given & when
//        sut.getAll();
//
//        //then
//        verify(fileRepository, times(1)).findAll();
//    }
//
//    @Test
//    void invoke_get_total_path_file_by_id_and_path_is_correct() {
//        //given
//        when(fileRepository.findById(FILE_ID)).thenReturn(Optional.of(FILE));
//
//        //when
//        String totalPathFile = sut.getTotalPathFile(FILE_ID.toString());
//
//        //then
//        verify(fileRepository, times(1)).findById(FILE_ID);
//        Assertions.assertEquals(totalPathFile, FILE.getTotalPath());
//    }
//
//    @Test
//    void invoke_save_in_repository() {
//        //given
//        FileDTO fileDTO = prepareFileDTO();
//        when(userRepository.findById(any())).thenReturn(Optional.of(USER));
//        when(fileRepository.save(any())).thenReturn(FILE);
//
//        //when
//        sut.save(fileDTO);
//
//        //then
//        verify(fileRepository, times(1)).save(File.of(fileDTO, any()));
//    }
}