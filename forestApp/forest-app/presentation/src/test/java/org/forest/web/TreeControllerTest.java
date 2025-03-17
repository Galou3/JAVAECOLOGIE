package org.forest.web;


import org.forest.domain.model.Exposure;
import org.forest.domain.model.Species;
import org.forest.domain.model.Tree;
import org.forest.port.driving.TreeUseCase;
import org.forest.web.config.WebTestConfig;
import org.forest.web.controller.TreeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = TreeController.class)
@AutoConfigureMockMvc
@Import(WebTestConfig.class)
public class TreeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TreeUseCase treeUseCase;

    @Test
    void shouldGetATreeById() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(treeUseCase.get(uuid)).thenReturn(Optional.of(
                new Tree(uuid, LocalDate.now(), Species.OAK, Exposure.SHADOW, 40)
        ));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/tree/%s".formatted(uuid.toString()))
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.species").value("OAK"));

    }

}
