package com.yeahwell.dp.create.factorymothod2;

import com.yeahwell.dp.create.factorymothod1.Auto;
import com.yeahwell.dp.create.factorymothod1.Car;

public class CarFactory extends Factory{

	@Override
	public Auto createAuto() {
		return new Car();
	}

}
