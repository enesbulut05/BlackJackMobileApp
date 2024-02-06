package com.enesbulut.blackjack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameDeck extends AbstractDeck {

    public GameDeck() {
        this.createDeck();
    }

    // Deste oluştur
    public void createDeck() {
        /*String jsonContent = "[\n" +
                "    { \"name\": \"2\", \"value\": 2,\"card\": \"sinek2\" },\n" +
                "    { \"name\": \"2\", \"value\": 2,\"card\": \"maca2\" },\n" +
                "    { \"name\": \"A\", \"value\": 1, \"card\": \"karoas\" }\n" +
                "]";


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.cards = objectMapper.readValue(jsonContent,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Card.class));

            this.shuffleCards();
            this.cards = this.cards.subList((this.cards.size() / 2), this.cards.size()); // Destenin yarısını sil
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String jsonString = "[\n" +
                "  { \"name\": \"2\", \"value\": 2,\"card\": \"sinek2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2,\"card\": \"maca2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2,\"card\": \"kupa2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2,\"card\": \"karo2\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"sinek3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"maca3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"kupa3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"karo3\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"sinek4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"maca4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"kupa4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"karo4\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"sinek5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"maca5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"kupa5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"karo5\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"sinek6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"maca6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"kupa6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"karo6\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"sinek7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"maca7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"kupa7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"karo7\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"sinek8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"maca8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"kupa8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"karo8\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"sinek9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"maca9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"kupa9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"karo9\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"sinek10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"maca10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"kupa10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"karo10\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"sinekkiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"macakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"kupakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"karokiz\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"sinekvale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"macavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"kupavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"karovale\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"sinekpapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"macapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"kupapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"karopapaz\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"sinekas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"macaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"kupaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"karoas\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"sinek2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"maca2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"kupa2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"karo2\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"sinek3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"maca3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"kupa3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"karo3\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"sinek4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"maca4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"kupa4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"karo4\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"sinek5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"maca5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"kupa5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"karo5\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"sinek6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"maca6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"kupa6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"karo6\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"sinek7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"maca7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"kupa7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"karo7\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"sinek8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"maca8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"kupa8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"karo8\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"sinek9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"maca9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"kupa9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"karo9\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"sinek10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"maca10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"kupa10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"karo10\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"sinekkiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"macakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"kupakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"karokiz\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"sinekvale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"macavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"kupavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"karovale\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"sinekpapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"macapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"kupapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"karopapaz\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"sinekas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"macaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"kupaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"karoas\" },  \n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"sinek2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"maca2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"kupa2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"karo2\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"sinek3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"maca3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"kupa3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"karo3\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"sinek4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"maca4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"kupa4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"karo4\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"sinek5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"maca5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"kupa5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"karo5\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"sinek6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"maca6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"kupa6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"karo6\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"sinek7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"maca7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"kupa7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"karo7\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"sinek8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"maca8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"kupa8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"karo8\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"sinek9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"maca9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"kupa9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"karo9\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"sinek10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"maca10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"kupa10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"karo10\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"sinekkiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"macakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"kupakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"karokiz\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"sinekvale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"macavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"kupavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"karovale\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"sinekpapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"macapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"kupapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"karopapaz\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"sinekas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"macaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"kupaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"karoas\" },  \n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"sinek2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"maca2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"kupa2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"karo2\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"sinek3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"maca3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"kupa3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"karo3\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"sinek4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"maca4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"kupa4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"karo4\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"sinek5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"maca5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"kupa5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"karo5\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"sinek6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"maca6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"kupa6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"karo6\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"sinek7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"maca7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"kupa7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"karo7\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"sinek8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"maca8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"kupa8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"karo8\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"sinek9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"maca9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"kupa9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"karo9\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"sinek10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"maca10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"kupa10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"karo10\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"sinekkiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"macakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"kupakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"karokiz\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"sinekvale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"macavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"kupavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"karovale\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"sinekpapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"macapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"kupapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"karopapaz\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"sinekas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"macaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"kupaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"karoas\" },  \n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"sinek2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"maca2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"kupa2\" },\n" +
                "  { \"name\": \"2\", \"value\": 2, \"card\": \"karo2\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"sinek3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"maca3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"kupa3\" },\n" +
                "  { \"name\": \"3\", \"value\": 3, \"card\": \"karo3\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"sinek4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"maca4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"kupa4\" },\n" +
                "  { \"name\": \"4\", \"value\": 4, \"card\": \"karo4\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"sinek5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"maca5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"kupa5\" },\n" +
                "  { \"name\": \"5\", \"value\": 5, \"card\": \"karo5\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"sinek6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"maca6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"kupa6\" },\n" +
                "  { \"name\": \"6\", \"value\": 6, \"card\": \"karo6\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"sinek7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"maca7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"kupa7\" },\n" +
                "  { \"name\": \"7\", \"value\": 7, \"card\": \"karo7\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"sinek8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"maca8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"kupa8\" },\n" +
                "  { \"name\": \"8\", \"value\": 8, \"card\": \"karo8\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"sinek9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"maca9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"kupa9\" },\n" +
                "  { \"name\": \"9\", \"value\": 9, \"card\": \"karo9\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"sinek10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"maca10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"kupa10\" },\n" +
                "  { \"name\": \"10\", \"value\": 10, \"card\": \"karo10\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"sinekkiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"macakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"kupakiz\" },\n" +
                "  { \"name\": \"Q\", \"value\": 10, \"card\": \"karokiz\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"sinekvale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"macavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"kupavale\" },\n" +
                "  { \"name\": \"J\", \"value\": 10, \"card\": \"karovale\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"sinekpapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"macapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"kupapapaz\" },\n" +
                "  { \"name\": \"K\", \"value\": 10, \"card\": \"karopapaz\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"sinekas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"macaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"kupaas\" },\n" +
                "  { \"name\": \"A\", \"value\": 1, \"card\": \"karoas\" }\n" +
                "]\n";

        List<Card> cards = parseJsonToCardList(jsonString);
        this.cards = cards;
        shuffleCards();
        shuffleCards();

    }
    private static List<Card> parseJsonToCardList(String jsonString) {
        List<Card> cards = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON dizisini JsonNode'a parse et
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // JsonNode üzerinde dönerek Card nesnelerini oluştur
            Iterator<JsonNode> elements = jsonNode.elements();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                String name = node.get("name").asText();
                int value = node.get("value").asInt();
                String card = node.get("card").asText();

                cards.add(new Card(name, value, card));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cards;
    }

    // Desteyi karıştır
    public void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    // Kart çek
    public Card buyCard() {
        return this.buyCard(0);
    }

    // Index'e göre kart çek
    public Card buyCard(int index) {
    	try {
			Card card = this.cards.get(index);
        this.cards.remove(card);
        return card;
		} catch (Exception e) {
			createDeck();
			System.out.println("Yeni Deste Oluşturuldu.");
			Card card = this.cards.get(index);
	        this.cards.remove(card);
	        return card;
		}
    }

}
