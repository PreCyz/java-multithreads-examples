package com.dosomedev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BabyNames {

    private final StreamType streamType;

    private final List<String> maleNames;

    private final List<String> femaleNames;

    public BabyNames(StreamType streamType) {
        this.streamType = streamType;
        this.maleNames = new ArrayList<>();
        this.femaleNames = new ArrayList<>();
    }

    public void loadBabyNames() {
        IO.print("Loading baby names...");
        try (InputStream inputStream = BabyNames.class.getClassLoader().getResourceAsStream("baby-names.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;

            int indexName = 0;
            int indexSex = 1;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    String sex = parts[indexSex];
                    String name = parts[indexName];

                    switch (sex) {
                        case "boy":
                            this.maleNames.add(name);
                            break;
                        case "girl":
                            this.femaleNames.add(name);
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            IO.println("The baby database could not be loaded.");
        }
        IO.println("done!");
    }

    public Stream<String> getMaleNames() {
        return this.createStream(maleNames);
    }

    public Stream<String> getFemaleNames() {
        return this.createStream(femaleNames);
    }

    private Stream<String> createStream(List<String> names) {
        return switch (this.streamType) {
            case SEQUENTIAL -> names.stream();
            case PARALLEL -> names.parallelStream();
            default -> throw new IllegalArgumentException("No stream type chosen.");
        };
    }
}
