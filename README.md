# escalaveis_PB_TP1
> Projeto feito como TP1 para a disciplina *Projeto de Bloco: Engenharia de Softwares Escaláveis* do *Instituto Infnet* 
> Por João Cicero - 2025

## Serviço de Issues
`Joao_Ramos_PB_TP1/`
Projeto REST API em Java SpringBoot 

## Serviço de Auditoria
`HistoryService/`
Projeto REST API SpringBoot para propositos de auditoria.

## Frontend
`Joao_Ramos_PB_TP1_frontend/`
Projeto React que consome os endpoints da API.


### Rodando Localmente
1. Faça o download do repositorio 
2. Rode o EventBroker RabbitMQ
    ```
    docker run -e RABBITMQ_DEFAULT_USER=EvBrokerJoao_Ramos_PB -e RABBITMQ_DEFAULT_PASS=12345678_ev -p 5672:5672 -p 15672:15672  rabbitmq:3-management
    ```
3. Rode a **Serviço de Issues**
    ```
    cd Joao_Ramos_PB_TP1/
    mvn spring-boot:run
    ```
4. Em um novo terminal, rode o **Serviço de Auditoria**
    ```
    cd HistoryService/
    mvn spring-boot:run
    ```
5. Em um novo terminal, rode o app **frontend**
    ```
    cd Joao_Ramos_PB_TP1/
    npm install
    npm run
    ```
6. Acesse o portal atravéz de `http://localhost:3000`

### Rodando com imagens do docker hub
1. Baixe e rode a imagem do frontend
    ```
    docker pull joaoramos15285/escalaveis_pb_frontend:v0.1
    docker run -d -p 3000:3000 --name pb_frontend 
    ```
2. Baixe e rode a imagem do RabbitMQ
    ```
    docker run -d -e RABBITMQ_DEFAULT_USER=EvBrokerJoao_Ramos_PB -e RABBITMQ_DEFAULT_PASS=12345678_ev -p 5672:5672 -p 15672:15672  rabbitmq:3-management
    ```
3. Baixe e rode a imagem da API
    ```
    docker pull joaoramos15285/escalaveis_pb_api:v0.1
    docker run -d -p 8080:8080 --name pb_api joaoramos15285/escalaveis_pb_api:v0.1 
    ```
4. Baixe e rode a imagem do History
    ```
    docker pull joaoramos15285/escalaveis_pb_history:v0.1
    docker run -d -p 8081:8081 --name pb_history joaoramos15285/escalaveis_pb_history:v0.1 
    ```

### Postman
É possivel acessar as APIs do **Serviço de Issues** e o **Serviço de Auditoria** atravéz do postman usando as colections disponiveis:
- `postman\collections\Servico_de_Issues.postman_collection.json`
- `postman\collections\Servico_de_Auditoria.postman_collection.json`

Para saber como importar uma collection do postman [Clique Aqui](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/#import-postman-data)

### RabbitMQ
Para acessar o manager do rabbitMQ (Broker de eventos) basta acessar `http://localhost:15672/` uma vez que o container do serviço estiver rodando.
As credenciais para acesso ao portal são:
> **username :** `EvBrokerJoao_Ramos_PB` 
> **password :** `12345678_ev`