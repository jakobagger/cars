
## Week 2

- What are the benefits of using a RESTful API  

     Det er simpelt, skalerbart og kan kommunikere i forskellige dataformater. Fordi det er standardiseret gør det det nemt for forskellige apps at komminkere med hinanden.

- What is JSON, and why does JSON fit so well with REST?
     Står for JavaScript Object Notation og er et format til at sende data som strenge af tekst. Fordi det er standardiseret og letvægt (ren tekst) passer det godt sammen med REST da de begge hjælper til at få forskellige apps til at snakke sammen.

- How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
     Har lavet RestControllers der kalder servicemetoder. Serviceklasserne laver de Request DTOer der kommer oppefra om til entity-objekter der kan gemmes i databasen, og laver entity-objekter fra databasen om til passende DTOer (MemberResponse og CarResponse) når der skal sendes data retur.

-  What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints
     At man kan bestemme præcis hvilke dele af et objekt der skal vises hvornår og kun behøver sende relevante dele afsted - performance og security. Derudover kan databasen være normaliseret i en grad hvor tabellerne ikke mapper simpelt til et enkelt objekt.

- Explain shortly the concept mocking in relation to software testing
     Betyde at man simulerer de dele der er udenom testen for at isolere den, jeg har fx brugt in-memory databasen H2 til at teste så mine tests ikke er afhængige af ekstern database adgang og det i øvrigt går betydeligt hurtigere.

- How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
     annoterede testklasser med @DataJpaTest og @Autowired relevant repository-klasser.

- Explain the concept Build Server and the role Github Actions play here
  Build Server: server der lytter efter pushes og henter automatisk og derefter prøver at bygge projektet og sender besked tilbage om det lykkedes. Vi har sat vores på på Azure vha. Github Actions

- Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
     Maven kan automatisere de forskellige trin i at bygge vores projekt. Dette kan køres med tekstkommandoer i en terminal dvs det er scriptable. GitHUb actions kan derfor bruge Maven til at bygge vores projekt når Actions registrer at et nyt commit er pushet.

-  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin
     På dette stadie af projektet er appen deployet på Azure App Service, platform as a service, og bruger en Azure MySQL database, Database as a Service.
     Så PaaS og DaaS indtil videre.
