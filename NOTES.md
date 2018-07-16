
# Implementation notes

## Stack

- Java 8
- Gradle 4.8
- jUnit 5
- Mockito 2

## Usage

### Build

```bash
./gradlew jar
```

### Run

Run command from root folder:

```bash
java -jar build/libs/tic-tac-toe-1.0-SNAPSHOT.jar config.properties
```

Help:

```
usage: java -jar tic-tac-toe-1.0-SNAPSHOT.jar <file>

DESCRIPTION
 <file>   path to the configuration file
```

Example configuration file:

```
dimension=3
player_1=O
player_2=X
computer=C
```


## Command line argument parsing

- Instead of parsing command line arguments manually I would use
[Commons CLI](https://commons.apache.org/proper/commons-cli/) in order not to reinvent the wheel.
However, that is forbidden in this task.


## Domain objects

- Domain objects are always valid and immutable. They are created or a `DomainException` is thrown.
- There is slight overhead but it prevents many bugs.
- Some objects are final because they are not intended to be inherited.
- Programming to an interface is a good practice.

## Game

- I assume that there will be multiple types of computer players which would have various AI capabilities.
- I don't think there will be need for multiple types of boards. However, it is a good practice program to an interface.

## Testing

- Interfaces were created for final classes (because final classes are hard to mock).

## SOLID principle

Solid principles were used.


- **Single responsibility** - Classes have only one responsibility which is encapsulated in the class.
- **Open/closed principle** - Classes are open for extension, but closed for modification. 
- **Liskov substitution principle** - Objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.
- **Interface segregation** - Many client-specific interfaces are better than one general-purpose interface - see `com.martinvana.tic_tac_toe.game.board` package
- **Dependency inversion principle** - Classes depend upon abstraction (interfaces) not concrete classes.

## Interesting features

- Thanks to *Gradle* things such as build (`./gradlew jar`) or tests (`./gradlew test`) are automated.
- CI is in place (*Travis*).
- Immutable objects are used instead of primitive types.
- Defensive programming - `final` modifier is used in order to prevent bugs.
- The app configuration is loaded using built-in `Properties` class.
- Regular expression is used for validation - see `PlayCharacterImpl`.
- Regular expression is used for input parsing - see `HumanPlayer`.
- AI present is very dumb. It's a feature not a bug.
- Variable arguments were used in `PlayerListImpl` class.
- Unit testing, parametrized tests, and mocking were used.
- Code is readable and prepared for extension. 
