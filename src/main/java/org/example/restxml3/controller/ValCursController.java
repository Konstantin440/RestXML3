package org.example.restxml3.controller;

import lombok.RequiredArgsConstructor;
import org.example.restxml3.model.ClientCB;
import org.example.restxml3.model.ValCurs;
import org.example.restxml3.model.Valute;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/valute")
public class ValCursController {
    private final ClientCB clientCB;

    @GetMapping("/today")
    public ResponseEntity<ValCurs> toDay() {
        return ResponseEntity.ok(clientCB.getValCurs());
    }

    @GetMapping("currency/{name}/{data}")
    public String getCurrency(@PathVariable("name") String name,
                              @PathVariable("data") LocalDate data) {

        String text = "Значение " + name + " на дату: " + data + "= ";

        ValCurs valCurs = clientCB.getValCursByDate(data);
        List<Valute> valuteList = valCurs.getValuteList();

        for (Valute valute : valuteList) {
            if (valute.getName().equals(name)) {
                return text + valute.getValue();
            }
        }

        return "Валюта не найдена";
    }

    @GetMapping("history/{name}")
    public List<String> getHistory(@PathVariable("name") String name) {
        List<String> history = new ArrayList<>();
        boolean currencyFound = false;

        for (int i = 0; i <= 10; i++) {
            LocalDate localDate = LocalDate.now().minusYears(i);
            ValCurs valCurs = clientCB.getValCursByDate(localDate);

            List<Valute> valuteList = valCurs.getValuteList();

            for (Valute valute : valuteList) {
                if (valute.getName().equals(name)) {
                    currencyFound = true;
                    String text = String.format("Стоимость валюты %s в %s составляет %s",
                            valute.getName(), localDate, valute.getValue());
                    history.add(text);
                }
            }
        }

        if (!currencyFound) {
            return Collections.singletonList("Валюта не найдена");
        }

        return history;
    }
}

