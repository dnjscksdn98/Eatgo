package com.alexcode.eatgo.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.RegionService;
import com.alexcode.eatgo.domain.models.Region;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegionController.class)
class RegionControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RegionService regionService;

  @Test
  public void list() throws Exception {
    List<Region> regions = new ArrayList<>();
    regions.add(Region.builder().name("Seoul").build());

//    given(regionService.getRegions()).willReturn(regions);

    mvc.perform(get("/regions"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Seoul")));
  }

  @Test
  public void create() throws Exception {
    Region region = Region.builder().name("Seoul").build();
//    given(regionService.addRegion("Seoul")).willReturn(region);

    mvc.perform(post("/regions")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"Seoul\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().string("{}"));

//    verify(regionService).addRegion("Seoul");
  }

  @Test
  public void createWithExistedData() throws Exception {
//    given(regionService.addRegion("Seoul"))
//            .willThrow(new RegionDuplicationException("Seoul"));

    mvc.perform(post("/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Seoul\"}"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void createWithEmptyData() throws Exception {
    mvc.perform(post("/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"\"}"))
            .andExpect(status().isBadRequest());

//    verify(regionService, never()).addRegion(any());
  }

}