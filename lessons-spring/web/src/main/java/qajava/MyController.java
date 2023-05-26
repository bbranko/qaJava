package qajava;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Builder;
import lombok.Data;

@Controller
@RequestMapping("api")
public class MyController {

  final MyService service;

  public MyController(MyService service) {
    this.service = service;
  }

  @GetMapping("result")
  @ResponseBody
  public String getResult() {
//    return "result";
    return service.getResult();
  }

//  @GetMapping("result")
  @GetMapping("resultJson")
  @ResponseBody
  public ResultDto getDtoResult() {
    return ResultDto.builder()
      .result("result")
      .build();
  }
}

@Data
@Builder
class ResultDto{
  String result;
}
