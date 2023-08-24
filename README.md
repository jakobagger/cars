
## Week 1

>The idea with, and reasons for why to use, an **ORM-mapper**

*Object Relational Mapping binder objekt-orienteret kode sammen med relationelle databaser og automatiserer meget af interaktionen med databasen så man ikke skal have SQL-statements midt i sin Java-kode fx. ORM frameworks kan give en emtoder til at interagere med databasen gennem fx annotationer i ens kode.*  

>The meaning of the terms **JPA, Hibernate and Spring Data JPA** and how they are connected

*JPA er bare en specifikation af en måde ORM kan fungere i Java. Hibernate er et framework der implementerer denne specifikation. Spring Data JPA er en del af Spring der abstraherer en hel masse af arbejdet med repositories i et Spring projekt væk og sparer en for meget boilerplate-kode - gør man på få linier kan lave et repository interface for en entity-klasse og så få foræret en hel masse metoder*  
     
>How to create simple **Java entities** and map them to a database via the **Spring Data API**

  *Selve entity-klassen er et helt standard java-objekt, der så bliver mappet og manipuleret vha. annotationer. Som minimum skal selve klassen have @Entity annotation, en no-args constructor (i dette tilfælde genereret med Lombok-annotation), og et field der er annoteret som @Id der kan blive primary key i databasen.*  
  
>How to control the mapping between **individual fields** in an Entity class and their **matching columns** in the database

*Individuelle felter i klassen kan annoteres med @Column der tager parametre hvor man fx kan omdefinere det navn kolonnen skal have i databasen (systemet kan dog godt fx af sig selv lave om fra camelCase til under_score), eller om den skal være nullable eller ej*  
     
>How to **auto generate IDs**, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)

 *Det felt man har annoteret som @id kan yderligere annoteres med en metode til at auto-generere, for os   
     @GeneratedValue(strategy = GenerationType.IDENTITY), hvor parametret bestemmer at det skal være autoincrement i dette tilfælde*  
     
>How to use and define repositories and relevant query methods using **Spring Data JPAs repository pattern**

 *Gøres ved at lave et interface der extender JpaRepository og annotere det @Repository med definition af hvilken entity-klasse det skal forbindes til. Man får så en masse standard metoder forærende: 
     ![image](https://github.com/jakobagger/cars/assets/113458342/13b265b5-5562-47fd-8bb4-4075a3f62a59)
     
>How to write simple **"integration"** tests, using **H2** as a mock-database instead of MySQL

 *H2 er en in-memory database der kan bruges til at teste fx. Hvis man har added H2 som dependency til sit projekt (i Maven i pom-filen) vil projektet bruge mock-databasen hvis det ikke kan få fat i andre. Så kan man skrive tests på sine repository metoder der bruger denne database så de kan køres hurtigt uden at forbinde til en rigtig database. Testklasser af repository-klasserne skal annoteres med @DataJpaTest og have selve repositoryet de tester som @Autowired dependency*
 
>How to add **(dev) connection** details for you **local MySQL database**

*url, username og password for database er sat i application-properties som spring.datasources med variable navne. Derefter redigerer man run-konfigurationen og putter de environmetn-variabler ind man vil bruge ie. sætter de navne man nu har givet dem i applicationproperties = adressen på ens database (jdbc:mysql://localhost:3306/carbase) hvor sidste led carbase er det specifikke schema man skal bruge, og derudover den lokale bruger og password man har oprettet til sin MySQL database. Det kan også lade sig gøre at tilføje/ændre environment-variabler vha termnialen som man fx er nødt til når man har deployet på en linux VM.*
