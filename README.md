# StanInitiativeJavaFX
StanInitiative project on JavaFX

# Config
	applicationId "stan.initiative"
	versionCode 1606081414
	versionName "0.01bgnrmk"

# Build information
## build
```
javac -sourcepath ./src/main/java -d bin -classpath lib/* ./src/main/java/stan/initiative/Main.java
```

## build css
```
javafxpackager -createbss -srcdir ./src/main/css -outdir bin/css -srcfiles StanTheme.css -v
```

## copy res
### Windows
```
xcopy .\src\main\res .\bin\res /E
```

## run
```
java -classpath lib/*;bin stan.initiative.Main
```

## build jar
```
javafxpackager -createjar -appclass stan.initiative.Main -srcdir bin -classPath "lib/StanVoiceRecognition.jar" -outfile StanInitiative
```

## runjar
```
javaw -jar StanInitiative.jar
```

## dependencies
[![StanVoiceRecognition](https://img.shields.io/badge/github-StanVoiceRecognition-blue.svg?style=true)](https://github.com/kepocnhh/StanVoiceRecognition)