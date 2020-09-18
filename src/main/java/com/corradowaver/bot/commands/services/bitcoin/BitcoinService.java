package com.corradowaver.bot.commands.services.bitcoin;

import com.corradowaver.bot.commands.handlers.entities.BitcoinBody;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class BitcoinService {

  private static final String URL = "https://blockchain.info/ticker";

  public static List<BitcoinBody> getRates() throws UnirestException {
    HttpResponse<JsonNode> request = Unirest.get(URL).asJson();

    JSONObject jsonObject = request.getBody().getObject();

    List<String> currencies = List.of("USD", "EUR", "RUB");
    return currencies.stream().map(
        currency -> new BitcoinBody(
            currency,
            jsonObject.getJSONObject(currency).getInt("last"),
            jsonObject.getJSONObject(currency).getString("symbol")
        )
    ).collect(Collectors.toList());
  }
}
