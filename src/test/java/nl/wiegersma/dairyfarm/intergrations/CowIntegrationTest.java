package nl.wiegersma.dairyfarm.intergrations;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CowIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CowRepository cowRepository;

    @Test
    public void shouldCreateACow() throws Exception {


        String requestJson = """ 
                        {
                        "cowNumber": 7500,
                        "aLife": true
                         }
                        """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/cows")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andReturn();

        Cow cow = cowRepository.findById(1L).orElseThrow(() -> new RecordNotFoundException("test msg"));

        assertTrue(cow.isALife());
        assertEquals(7500L, cow.getCowNumber());

    }
}
