package qajava;

import org.springframework.stereotype.Service;

@Service
public class MyService {
  public String getResult() {
    //DB calls, processing, etc...
    return "complex result";
  }
}
