package com.advancia.spring;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.advancia.spring.auth.db.pojo.Role;
import com.advancia.spring.auth.db.pojo.User;
import com.advancia.spring.auth.db.pojo.UserImage;
import com.advancia.spring.auth.db.service.UserImageService;
import com.advancia.spring.auth.db.service.UserService;
import com.advancia.spring.db.configuration.pojo.Campi;
import com.advancia.spring.db.configuration.pojo.Listino;
import com.advancia.spring.db.configuration.pojo.Prodotto;
import com.advancia.spring.db.configuration.pojo.VincoliProdotti;
import com.advancia.spring.db.configuration.service.CampiService;
import com.advancia.spring.db.configuration.service.ListinoService;
import com.advancia.spring.db.configuration.service.ProdottoService;
import com.advancia.spring.db.configuration.service.VincoliProdottiService;

@SpringBootApplication
public class AppMutuiServerApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private UserImageService userImageService;

	@Autowired
	private ProdottoService prodottoService;

	@Autowired
	private VincoliProdottiService vincoliProdottiService;

	@Autowired
	private CampiService campiService;

	@Autowired
	private ListinoService listinoService;

	public static void main(String[] args) {
		AppMutuiServerApplication app = SpringApplication.run(AppMutuiServerApplication.class, args)
				.getBean(AppMutuiServerApplication.class);

		// app.createUser();
		app.createProdotti();
		app.createVincoliProdotti();
		app.createCampi();
		app.createListino();
	}

	public void createUser() {
		String userImageNome = "immagine_profilo.jpg";
		String userImageType = "image/jpeg";
		byte[] userImagePicByte = { (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0x90, (byte) 0xAB,
				(byte) 0xCD, (byte) 0xEF };

		UserImage userImage = new UserImage(userImageNome, userImageType, userImagePicByte);
		userImageService.save(userImage);

		String userNome = "marco";
		String userCognome = "busacca";
		String userEmail = "marcobusacca@gmail.com";
		String userPassword = "marcobusacca";

		LocalDate localDate = LocalDate.of(2003, 12, 30);
		Date userDataDiNascita = Date.valueOf(localDate);

		User user = new User(userNome, userCognome, userEmail, passwordEncoder.encode(userPassword),
				userDataDiNascita,
				Role.USER, userImage);
		userService.save(user);
	}

	public void createProdotti() {

		prodottoService.save(new Prodotto("0001", "Fisso"));
		prodottoService.save(new Prodotto("0002", "Variabile"));
		prodottoService.save(new Prodotto("0003", "Ristrutturazione"));
		prodottoService.save(new Prodotto("0004", "Misto"));
		prodottoService.save(new Prodotto("0005", "Surroga"));
		prodottoService.save(new Prodotto("0006", "Giovani"));
		prodottoService.save(new Prodotto("0007", "Green"));
	}

	public void createVincoliProdotti() {

		vincoliProdottiService.save(new VincoliProdotti("0006", 35, true));
		vincoliProdottiService.save(new VincoliProdotti("0001", 80, false));
		vincoliProdottiService.save(new VincoliProdotti("0002", 70, false));
		vincoliProdottiService.save(new VincoliProdotti("0007", 50, true));
	}

	public void createCampi() {

		// FISSO
		campiService.save(new Campi("0001", "Importo"));
		campiService.save(new Campi("0001", "Richiesto"));
		campiService.save(new Campi("0001", "Durata"));
		campiService.save(new Campi("0001", "Reddito"));

		// VARIABILE
		campiService.save(new Campi("0002", "Importo"));
		campiService.save(new Campi("0002", "Richiesto"));
		campiService.save(new Campi("0002", "Durata"));
		campiService.save(new Campi("0002", "Reddito"));

		// RISTRUTTURAZIONE
		campiService.save(new Campi("0003", "Importo"));
		campiService.save(new Campi("0003", "Richiesto"));
		campiService.save(new Campi("0003", "Durata"));
		campiService.save(new Campi("0003", "Reddito"));
		campiService.save(new Campi("0003", "Costo ristrutturazione"));

		// MISTO
		campiService.save(new Campi("0004", "Importo"));
		campiService.save(new Campi("0004", "Richiesto"));
		campiService.save(new Campi("0004", "Durata"));
		campiService.save(new Campi("0004", "Reddito"));

		// SURROGA
		campiService.save(new Campi("0005", "Importo"));
		campiService.save(new Campi("0005", "Richiesto"));
		campiService.save(new Campi("0005", "Durata"));
		campiService.save(new Campi("0005", "Reddito"));

		// GIOVANI
		campiService.save(new Campi("0006", "Importo"));
		campiService.save(new Campi("0006", "Richiesto"));
		campiService.save(new Campi("0006", "Durata"));
		campiService.save(new Campi("0006", "Reddito"));

		// GREEN
		campiService.save(new Campi("0007", "Importo"));
		campiService.save(new Campi("0007", "Richiesto"));
		campiService.save(new Campi("0007", "Durata"));
		campiService.save(new Campi("0007", "Reddito"));
		campiService.save(new Campi("0007", "Classe energetica"));
	}

	public void createListino() {

		// FISSO
		listinoService.save(new Listino("0001", 80, 30, 3.5));
		listinoService.save(new Listino("0001", 70, 30, 3.25));
		listinoService.save(new Listino("0001", 60, 30, 2.9));
		listinoService.save(new Listino("0001", 80, 20, 2.5));

		// RISTRUTTURAZIONE
		listinoService.save(new Listino("0003", 80, 30, 4.13));
		listinoService.save(new Listino("0003", 70, 30, 3.99));
	}
}