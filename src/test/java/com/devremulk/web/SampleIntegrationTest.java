package com.devremulk.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devremulk.web.entity.SampleEntity;
import com.devremulk.web.repository.SampleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SampleIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        sampleRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void shouldCreateSampleAndPopulateAuditFields() throws Exception {
        SampleRequest request = new SampleRequest("Deneme");

        mockMvc.perform(post("/api/v1/samples")
                        .header("X-User", "tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        List<SampleEntity> samples = sampleRepository.findAll();
        assertThat(samples).hasSize(1);
        SampleEntity saved = samples.getFirst();
        assertThat(saved.getCreatedUser()).isEqualTo("tester");
        assertThat(saved.getUpdateUser()).isEqualTo("tester");
        assertThat(saved.getCreatedDatetime()).isNotNull();
        assertThat(saved.getUpdateDatetime()).isNotNull();
        assertThat(saved.getUuid()).isNotBlank();
        assertThat(saved.getDeleted()).isFalse();
    }

    @Test
    @WithMockUser
    void shouldReturnLocalizedGreetings() throws Exception {
        mockMvc.perform(get("/api/v1/samples/greeting")
                        .header("Accept-Language", "en"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, sample service!"));

        mockMvc.perform(get("/api/v1/samples/greeting")
                        .header("Accept-Language", "tr"))
                .andExpect(status().isOk())
                .andExpect(content().string("Merhaba, örnek servis!"));
    }

    private record SampleRequest(String name) {
    }

}
