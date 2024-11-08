# Usa uma imagem base do OpenJDK
FROM openjdk:11

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia todo o código do projeto para o contêiner
COPY . .

# Compila o projeto usando Maven
RUN ./mvnw clean install

# Expõe a porta padrão para o Java (8080)
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "target/app.jar"]