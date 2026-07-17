package nl.wiegersma.dairyfarm.intergrations;


import nl.wiegersma.dairyfarm.enums.Time;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.models.Medication;
import nl.wiegersma.dairyfarm.repositories.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class MedicationIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MedicationRepository medicationRepository;

    @Test
    public void shouldGetAMedication() throws Exception {

        Medication medication = new Medication();
        medication.setName("avuloxil");
        medication.setDescription("medicatie tegen uier ontsteking");
        medication.setMeatWithdrawalPeriod(3L);
        medication.setMilkWithholdingPeriod(3L);
        medication.setTime(Time.DAYS);

        Medication saved = medicationRepository.save(medication);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/medications/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Medication getMedication = medicationRepository.findById(saved.getId()).orElseThrow(() -> new RecordNotFoundException("test failure"));
        assertEquals(getMedication.getName(), "avuloxil");
        assertEquals(getMedication.getDescription(), "medicatie tegen uier ontsteking");
        assertEquals(getMedication.getMeatWithdrawalPeriod(), 3L);
        assertEquals(getMedication.getMilkWithholdingPeriod(), 3L);
        assertEquals(getMedication.getTime(), Time.DAYS);

    }


}
