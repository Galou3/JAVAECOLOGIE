package org.forest.web.config;

import org.forest.port.driving.CO2AbsorptionUseCase;
import org.forest.port.driving.TreeUseCase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebTestConfig {
    @MockBean
    private TreeUseCase treeUseCase;

    @MockBean
    private CO2AbsorptionUseCase co2AbsorptionUseCase;
}
