package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.Stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyStuffServicImpl implements MyStuffService {

	@Autowired
	public MyStuffServicImpl() {
	}

	@Override
	public Stuff test(String project, String service) {
		return new Stuff(project, service);
	}

}
