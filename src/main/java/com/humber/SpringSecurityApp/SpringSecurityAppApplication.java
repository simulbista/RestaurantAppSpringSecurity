package com.humber.SpringSecurityApp;

import com.humber.SpringSecurityApp.models.Dish;
import com.humber.SpringSecurityApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	private final DishService dishService;

	@Autowired
	SpringSecurityAppApplication(DishService dishService){
		this.dishService = dishService;
	}

	@Override
	public void run(String... args) throws Exception {

		dishService.saveDish(new Dish(1,"Pizza","Vegan",12));
		dishService.saveDish(new Dish(2,"Burger","Non-veg",12));
		dishService.saveDish(new Dish(3,"Shwarma","Veg",13));
		dishService.saveDish(new Dish(4,"Ham and Cheese","Veg",12));
		dishService.saveDish(new Dish(5,"Biryani","Non-veg",9));
		dishService.saveDish(new Dish(6,"Fries","Vegan",12));
		dishService.saveDish(new Dish(7,"Salad","Non-veg",13));
		dishService.saveDish(new Dish(8,"Fried Rice","Non-veg",12));
		dishService.saveDish(new Dish(9,"Momo","Vegan",17));
		dishService.saveDish(new Dish(10,"Hot dog","Non-veg",9));
		dishService.saveDish(new Dish(11,"Poutine","Non-veg",12));
		dishService.saveDish(new Dish(12,"Vegies","Veg",17));
	}
}
