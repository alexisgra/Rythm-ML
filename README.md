
# Rythm-ML



Rythm-ML is a tool for creating rhythm using a DSL.



## Visual Studio Code - Syntax Highlighting



There is Visual Studio Code Plugin for syntax highlighting. See the [README](syntax-highlighting/README.md)

file for more details.





## Usage



### Requirements



- Java 8

- Any text editor (VSCode is recommended)

- Port 8769 need to be free



```java -jar rythmML.jar```



There are some example files of scenario [here](dsl/src/main/resources/scenario) using the DSL.

### Configuration

We tested the solution on macOs, windows 10 and Fedora. It works well on macOs and Windows 10. On Gnome, the visualization in the shell doesn't work with the default web browser "Epiphany". 
The jar will write some files into the work file folder. So it needs to have writing rights (by default it's ok).

### Basic use case (Tutorial)


For this little tutorial we provide a workspace folder that includes three musics. We recommend you to use this workspace for the tutorial.

To be able to achieve this tutorial, go in the ```tutorial``` folder and run ```java -jar rythmML.jar```.

1. Once the shell is launched, you can type  ```help```  to see all the possible commands.

2. The ```howto``` commands give you access to a complete documentation of our language.

3. To use the different features of the shell, you need to specify the .rythm file you are working on.

To do this run the following command :

```workfile PATH_TO_THE_RYTHM_FILE``` so for example ```workfile ./workspace/scenario1.rythm```

4. Once the work file set up, you can play the music with ```play``` and stop it with ```stop```.

5. You can visualize the partition with the ```visu``` command. If you want to visualize only a section you can run ```visu section SECTION_NAME``` by specifying a section name declared in the scenario that you loaded.

6. You can export your .rythm partition in midi file with the  ```export``` .


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
