package io.myfunstuff.stocks.service.rs;

import org.apache.wink.json4j.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(path = "/service")
public interface WebService {
//    @RequestMapping(path = "/getStock", method = RequestMethod.GET)
//    String getIndex(
//        @RequestParam(value = "symbol", defaultValue = "SPY") String symbol,
//        Model model
//    );

    @GetMapping("/hello")
    ModelAndView getHello(
        @RequestParam(name = "symbol", defaultValue = "SPY") String symbol,
        @RequestParam(name = "startDate", defaultValue = "1999-01-01") String startDate,
        @RequestParam(name = "endDate", defaultValue = "2100-01-01") String endDate
    ) throws IOException, JSONException;
}
