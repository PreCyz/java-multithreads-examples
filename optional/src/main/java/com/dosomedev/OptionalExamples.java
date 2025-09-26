package com.dosomedev;

import java.util.Optional;

public class OptionalExamples {



    public String getFullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return "Cannot provide full name.";
        } else {
            return firstName + " " + lastName;
        }
    }

    public String getFullName(Optional<String> firstName, Optional<String> lastName) {
        return firstName
                .flatMap(first -> lastName.map(last -> first + " " + last))
                .orElse("Cannot provide full name.");
    }

    public String getValuesWithMap() {
        Animal animal = new Animal();
        animal.setName("Nala");
        Optional<Animal> animalOptional = Optional.ofNullable(animal);

        Optional<String> nameOptionalWithMap = animalOptional.map(Animal::getName);
        if (nameOptionalWithMap.isPresent()) {
            return "Animal name (map): " + nameOptionalWithMap.get();
        } else {
            return "Animal name (map) is null";
        }
    }

    public String getValuesWithFlatMap() {
        Animal animal = new Animal();
        animal.setName("Nala");
        Optional<Animal> animalOptional = Optional.ofNullable(animal);

        // This will work.
        Optional<String> nameOptionalWithFlatMap = animalOptional.flatMap(flatAnimal -> Optional.ofNullable(flatAnimal.getName()));
        if (nameOptionalWithFlatMap.isPresent()) {
            return "Animal name (flatMap): " + nameOptionalWithFlatMap.get();
        } else {
            return "Animal name (flatMap) is null";
        }
    }

    public void takingValues() {
        Optional<String> a1 = Optional.of("Maxi");         // Taking a non-null value.
        //Optional<String> a2 = Optional.of(null);               // Error: Cannot take null values.
        Optional<String> a3 = Optional.ofNullable("Maxi"); // Taking a value that might be null.
        Optional<String> a4 = Optional.ofNullable(null);   // Taking a value that is null.
        Optional<String> a5 = Optional.empty();                  // Creating an empty Optional.

        IO.println("a1: " + a1);
        //IO.println("a2: " + a2);
        IO.println("a3: " + a3);
        IO.println("a4: " + a4);
        IO.println("a5: " + a5);
    }

    public void getValuesWithIfPresentOrElse(String name) {
        Optional<String> maybeName = Optional.ofNullable(name);

        maybeName.ifPresentOrElse(
                theName -> IO.println("name: " + theName),
                () -> IO.println("name is null")
        );
    }

    public String getNameConditional(String name) {
        Optional<String> maybeName = Optional.ofNullable(name);

        String result = maybeName
                .map(String::toUpperCase)
                .filter(nameString -> nameString.length() > 5)
                .orElse("Default Name");

        return result;
    }

    public String provideDefaultValueWithOrElse(String email) {
        Optional<String> maybeDefault = Optional.ofNullable(email);

        return maybeDefault.orElse("default@example.com");
    }

    public String provideDefaultValueWithOrElseGet(String email) {
        Optional<String> maybeDefault = Optional.ofNullable(email);

        return maybeDefault.orElseGet(() -> {
            IO.println("Given eMail was null. Generating default eMail.");
            return "default@example.com";
        });
    }

    public String throwDefaultException(String email) throws MyCustomException {
        Optional<String> maybeDefault = Optional.ofNullable(email);

        return maybeDefault.orElseThrow(() -> new MyCustomException("My custom message!"));
    }
}
