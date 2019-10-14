package io.myfunstuff.stocks.service.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import io.myfunstuff.stocks.model.Stuff;

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
