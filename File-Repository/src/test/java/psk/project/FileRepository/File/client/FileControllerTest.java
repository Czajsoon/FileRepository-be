package psk.project.FileRepository.File.client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import psk.project.FileRepository.File.services.FileFacade;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static psk.project.FileRepository.RandomUtilTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileFacade fileFacade;


    @Test
    void return_OK_200_http_status_when_get_file() throws Exception {
        mockMvc.perform(get("/fileInfo?fileId=" + randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void return_OK_200_http_status_when_get_all_files() throws Exception {
        mockMvc.perform(get("/filesInfo"))
                .andExpect(status().isOk());
    }

    @Test
    void return_OK_200_http_status_when_download_file() throws Exception {
        mockMvc.perform(get("/file/download/" + randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void return_OK_200_http_status_when_download_files() throws Exception {
        mockMvc.perform(get("/files/download/" + randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void return_OK_200_http_status_when_save_files() throws Exception {
        mockMvc.perform(multipart("/files/" + randomUUID())
                        .file("files", randomByteArray())
                        .param("description", randomString(8))
                        .param("path", randomString(5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void return_OK_200_http_status_when_save_file() throws Exception {
        mockMvc.perform(multipart("/file/" + randomUUID())
                        .file("file", randomByteArray())
                        .param("description", randomString(8))
                        .param("path", randomString(5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void mexico() throws Exception {
        mockMvc.perform(get("/mexicano"))
                .andExpect(status().isOk());
    }
}