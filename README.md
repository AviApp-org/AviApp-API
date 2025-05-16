# AviApp-API

## AMBIENTE DEV
- A aplicação, por padrão, roda usando as configurações do arquivo `application.properties-dev`
- Para rodar a aplicação em modo de desenvolvimento, basta executar o comando `mvn spring-boot:run`
- Usa como base o banco h2 (http://localhost:8080/h2-console) 
- Tem um arquivo .sql instanciando algumas informações no banco para facilitar o desenvolvimento

## AMBIENTE PROD
- A aplicação, por padrão, roda usando as configurações do arquivo `application.properties-prod`
- Para rodar a aplicação em modo de prod, basta executar o docker compose, caso seja a primeira vez ou tenha feito alguma alteração na API ou no banco, basta executar o comando `docker-compose up --build` ou docker-compose build e depois docker-compose up
- Atenção, para usar as funcionalidades que envolvem pesquisa por data deve ser uusado sempre o ambiente de produção