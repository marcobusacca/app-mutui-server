package com.advancia.spring.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.advancia.spring.api.dto.user.UserDataDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDataDTO;
import com.advancia.spring.db.configuration.pojo.Prodotto;
import com.advancia.spring.db.configuration.pojo.VincoliProdotti;

@Repository
public class UserRestDAO {

    @Autowired
    private DataSource dataSource;

    public List<Prodotto> getAvailableProducts(UserDataDTO userDataDTO) throws SQLException {

        // LISTA PRODOTTI DISPONIBILI PER L'UTENTE ATTUALMENTE LOGGATO
        List<Prodotto> prodottiDisponibili = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            // LISTA DEI PRODOTTI SALVATI NEL DB
            List<Prodotto> prodotti = new ArrayList<>();

            // LISTA DEI VINCOLI SALVATI NEL DB
            List<VincoliProdotti> vincoli = new ArrayList<>();

            // QUERY PER RECUPERARE TUTTI I PRODOTTI DAL DB
            String prodottiQuery = "SELECT codice, descrizione FROM prodotto";
            PreparedStatement prodottiStatement = connection.prepareStatement(prodottiQuery);

            ResultSet prodottiResultSet = prodottiStatement.executeQuery();
            while (prodottiResultSet.next()) {
                prodotti.add(new Prodotto(prodottiResultSet.getString(1), prodottiResultSet.getString(2)));
            }

            // QUERY PER RECUPERARE TUTTI I VINCOLI DAL DB
            String vincoliProdottiQuery = "SELECT codice_prodotto, eta, cliente FROM vincoli_prodotti";
            PreparedStatement vincoliProdottiStatement = connection.prepareStatement(vincoliProdottiQuery);

            ResultSet resultSet = vincoliProdottiStatement.executeQuery();
            while (resultSet.next()) {
                vincoli.add(new VincoliProdotti(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getBoolean(3)));
            }

            // CONTROLLO QUALI PRODOTTI VINCOLATI POSSO MOSTRARE ALL'UTENTE
            for (VincoliProdotti vincolo : vincoli) {

                Optional<Prodotto> prodottoConVincolo = prodotti.stream()
                        .filter(prodotto -> prodotto.getCodice().equals(vincolo.getCodiceProdotto())).findFirst();

                if (prodottoConVincolo.isPresent()) {

                    if (vincolo.isCliente()) {
                        if (!userDataDTO.isCliente()) {
                            continue;
                        }
                    }
                    if (vincolo.getEta() <= userDataDTO.getEta()) {
                        continue;
                    }

                    prodottiDisponibili.add(prodottoConVincolo.get());
                }
            }

            // LISTA CODICI DEI PRODOTTI CHE HANNO DEI VINCOLI
            List<String> codiciProdottiConVincoli = vincoli.stream()
                    .map(VincoliProdotti::getCodiceProdotto)
                    .collect(Collectors.toList());

            // INSERISCO, NEI PRODOTTI DISPONIBILI ALL'UTENTE, I PRODOTTI SENZA VINCOLI
            prodottiDisponibili.addAll(prodotti.stream()
                    .filter(prodotto -> !codiciProdottiConVincoli.contains(prodotto.getCodice()))
                    .collect(Collectors.toList()));

            // ORDINO LA LISTA DI PRODOTTI DISPONIBILI PER CODICI DEI PRODOTTI
            Collections.sort(prodottiDisponibili, Comparator.comparing(Prodotto::getCodice));
        }

        return prodottiDisponibili;
    }

    public List<String> getCampiInputByCodiceProdotto(String codiceProdottoSelezionato) throws SQLException {

        List<String> campiInput = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String query = "SELECT input FROM campi WHERE codice_prodotto LIKE (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codiceProdottoSelezionato);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                campiInput.add(resultSet.getString(1));
            }
        }

        return campiInput;
    }

    public Map<String, Double> getLoggedUserFinalResult(LoggedUserFormDataDTO loggedUserFormDataDTO)
            throws SQLException {

        // CREAZIONE OGGETTO RESPONSE CON TASSO ANNUALE E RATA MENSILE
        Map<String, Double> response = new HashMap<>();

        try (Connection connection = dataSource.getConnection()) {

            // RECUPERO DATI DAL LOGGED_USER_FORM_DATA_DTO
            Prodotto prodottoSelezionato = loggedUserFormDataDTO.getProdottoSelezionato();
            LoggedUserFormDTO loggedUserFormDTO = loggedUserFormDataDTO.getLoggedUserForm();

            // RECUPERO DATI DAL LOGGED_USER_FORM_DTO
            double totaleImporto = loggedUserFormDTO.getImporto();
            double totaleRichiesto = loggedUserFormDTO.getRichiesto();
            int durata = loggedUserFormDTO.getDurata();

            // CALCOLO LTV MUTUO
            if ("Ristrutturazione".equals(prodottoSelezionato.getDescrizione())) {
                totaleRichiesto += loggedUserFormDTO.getCostoRistrutturazione();
            }
            double ltv = totaleRichiesto * 100 / totaleImporto;

            // TASSO D'INTERESSE FINALE
            double tasso;

            // QUERY PER RECUPERARE IL TASSO D'INTERESSE FINALE DAL DB
            String query = "SELECT tasso FROM listino WHERE codice_prodotto LIKE (?) AND (?) <= ltv AND (?) <= durata ORDER BY tasso LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, prodottoSelezionato.getCodice());
            statement.setDouble(2, ltv);
            statement.setInt(3, durata);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                // TASSO OTTENUTO DALLA QUERY
                tasso = resultSet.getDouble(1);

                // CALCOLO RATA MENSILE
                double tassoMensile = tasso / 12 / 100;
                int numeroRate = durata * 12;
                double rataMensile = (totaleRichiesto * tassoMensile) / (1 - Math.pow(1 + tassoMensile, -numeroRate));

                // INSERIMENTO DATI NELL'OGGETTO RESPONSE
                response.put("tasso", tasso);
                response.put("rata", rataMensile);
            }
        }

        return response;
    }
}
