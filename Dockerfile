# Utiliser une image OpenJDK 21 et installer Maven manuellement
FROM openjdk:21-jdk-slim AS builder

# Installer Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copier le fichier pom.xml pour préparer l'installation des dépendances
COPY pom.xml .

# Installer les dépendances Maven pour éviter de re-télécharger à chaque modification de code
RUN mvn dependency:go-offline

# Copier le code source
COPY src ./src

# Compiler et empaqueter le projet
RUN mvn clean install

# Utiliser une image OpenJDK 21 pour exécuter l'application
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copier le JAR compilé depuis l'étape de construction
COPY --from=builder /app/target/flutter_api_java-0.0.1-SNAPSHOT.jar /app/flutter_api_java.jar

# Exposer le port de l'application
EXPOSE 8001

# Lancer l'application
CMD ["java", "-jar", "flutter_api_java.jar"]
