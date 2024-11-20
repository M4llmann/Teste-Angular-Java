# Usar a imagem oficial do Node.js versão 18.2.12 como base para o build
FROM node:18-alpine AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo package.json e o package-lock.json
COPY package*.json ./

# Instalar as dependências do Angular
RUN npm install

# Copiar o restante do código-fonte do projeto
COPY . .

# Construir o projeto Angular para produção
RUN npm run build --prod

# Usar a imagem do Nginx para servir os arquivos estáticos
FROM nginx:alpine

# Copiar os arquivos gerados pelo build do Angular para o diretório de arquivos estáticos do Nginx
COPY --from=build /app/dist/ /usr/share/nginx/html

# Expor a porta 80 para acessar a aplicação
EXPOSE 80

# Rodar o Nginx para servir a aplicação
CMD ["nginx", "-g", "daemon off;"]
