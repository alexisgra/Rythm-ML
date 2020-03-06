# Rythm-ML

Rythm-ML is a tool for creating rhythm using a DSL.

## Usage

### Requirements

- Java 8
- Any text editor (VSCode is recommended)

```bash
java -jar rythmML.jar
```

There are some example files of scenario [here](dsl/src/main/resources/scenario)
using the DSL.

## Visual Studio Code - Syntax Highlighting

There is Visual Studio Code Plugin for syntax highlighting. See the [README](syntax-highlighting/README.md) 
file for more details.

## Installation (locally)

### Requirements

- Java 8
- Maven 3+

```bash
mvn clean install
cd shell/target
java -jar shell-0.0.1-SNAPSHOT.jar
```
It should install dependencies in your `<USER_DIRECTORY>/.m2` folder.

## License
Copyright © 2020

- Mathieu Paillart <mathieu.paillart@etu.unice.fr>
- Alexis Segura <alexis.segura@etu.unice.fr>
- Nathan Strobbe <nathan.strobbe@etu.unice.fr>

This work is free. You can redistribute it and/or modify it under the
terms of the Do What The Fuck You Want To Public License, Version 2,
as published by Sam Hocevar. See the [License](LICENSE) file for more details.