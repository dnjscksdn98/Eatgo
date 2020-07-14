package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.jwt.JwtConfig;
import com.alexcode.eatgo.jwt.JwtSecretKey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReservationController.class)
@ContextConfiguration(classes = {JwtConfig.class, JwtSecretKey.class})
class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    @WithMockUser(authorities = "reservation:read")
    public void list() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOi" +
                "Jvd25lcjAxQGV4YW1wbGUuY29tIiwidXNlcklkIjo0LCJ" +
                "1c2VyTmFtZSI6Im93bmVyMDEiLCJhdXRob3JpdGllcyI6" +
                "W3siYXV0aG9yaXR5IjoiUk9MRV9PV05FUiJ9LHsiYXV0a" +
                "G9yaXR5IjoicmVzZXJ2YXRpb246cmVhZCJ9XSwicmVzdGF" +
                "1cmFudElkIjoxLCJpYXQiOjE1OTQ2MDQ4MjAsImV4cCI6M" +
                "TU5NTQzMDAwMH0.1nWO9iqmtd-NcH0CMzw4CfyPUov_6OxJ3jeQjFLFKoQ";

        mvc.perform(get("/reservations")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        verify(reservationService).list(eq(1L));
    }
}