package com.yeahwell.dp.create.factorymothod2;

import com.yeahwell.dp.create.factorymothod1.Auto;
import com.yeahwell.dp.create.factorymothod1.Bus;

public class BusFactory extends Factory{

	@Override
	public Auto createAuto() {
		return new Bus();
	}

}
