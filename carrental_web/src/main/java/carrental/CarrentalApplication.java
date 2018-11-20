package carrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import carrental.model.Article;
import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.model.Type.FuelType;
import carrental.model.Type.GearType;
import carrental.repository.ArticleRepository;
import carrental.repository.CarRepository;

@SpringBootApplication
public class CarrentalApplication implements CommandLineRunner {

	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarrentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i=1; i<10; i++)
			carRepository.save(new Car(i, "Ferrari", new Type(2, GearType.manual, FuelType.gasoline), new Category()));
		
		String[] names = {"Egyed Tamás Barnabás","Kurdi Gergely Botond","Somogyi Balázs","Soós Sarolta","Szabó Tamás"};
		String[] articles = new String[5];
		
		for (int i=1; i<=5; i++) {
			articles[i-1] = "This is a new Article, the " + i + ". from the 5! I would like to introduce our team's first CarRental webaplication.";
			for (int j=1; j<=10; j++)
				articles[i-1]+=(" We need a lot of word, and text to see the homepage's style in the real life.");
		}
		
		for (int i=1; i<=5; i++)
			articleRepository.save(new Article(i, articles[i-1], names[i-1]));
	}

}