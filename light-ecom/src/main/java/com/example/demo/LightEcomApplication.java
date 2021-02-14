package com.example.demo;

import com.example.demo.dao.*;
import com.example.demo.entities.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.Random;

@EnableJpaRepositories
@SpringBootApplication
public class LightEcomApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(LightEcomApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Category.class,Product.class);
        categoryRepository.save( new Category(null,"Computers",null, null ,null));
		categoryRepository.save( new Category(null,"Printers", null,null ,null));
		categoryRepository.save( new Category(null,"Smart phone",null, null ,null));

		Random rnd = new Random();
		categoryRepository.findAll().forEach(c->{
			for (int i = 0; i < 10; i++) {
				Product p = new Product();

				p.setName(RandomString.make(18));
				p.setCurrentPrice(100+ rnd.nextDouble());
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setPhotoName("unknown.png");
				p.setCategory(c);
				productRepository.save(p);
			}

		});
	}
}
