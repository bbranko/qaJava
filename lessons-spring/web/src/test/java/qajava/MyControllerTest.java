package qajava;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(MyController.class)
//@WebMvcTest(value = {MyController.class, MyService.class})
class MyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MyService service;

  @Test
  public void testResult() throws Exception {
    when(service.getResult()).thenReturn("result");

    this.mockMvc.perform(get("/api/result"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("result")));
  }

  @Autowired
  MyController myController;
  @Test
  public void testResultDirect() {
    assert myController.getResult().equals("result");
  }
}
