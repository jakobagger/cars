# Week 3  

- Where and why you have used a @OneToMany annotation

   _Brugt i både Car og Member entity-klasser for at vise deres forbindelse til Reservation, liste af reservations som attribut for begge klasser._
   
- Where an why you have used a @ManyToOne annotation
- 
  _Brugt til Reservation entity klasse på dens Car og Member fields, for at vise at en member kan have mange reservationer og at en bil kan være reserveret mange gange._
  
- The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
 
  _Cascade betyder at de ændringer man har aktiveret det for (delete fx, men kunne også være update eller persist.) ikke kun bliver gjort for den ene entity men også for dens
  relaterede tabeller. FetchType.LAZY eller.EAGER kan sættes for en oneToMany relation og bestemmer om hele listen af relaterede rækker også automatisk skal hives ud af databasen hver gang (EAGER) eller ikke (LAZY). Fx vil man måske gerne kunne vise data om en by uden at hive alle dens indbyggere med ud hver gang
  MappedBy sættes i oneToMany for at angive hvilket field i manyToOne-klassen det skal forbindes med_
  
- -How/where you have (if done) added user defined queries to you repositories
  
- -a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
 
- -a few words about where you have used inheritance in your project, and how it's reflected in your database

  _Member klassen extender superklassen UserWithRoles fra security-pakken. Ud af de forskellige strategier der findes for at 'oversætte' arv fra OOP til relationel database bruger vi SINGLE table strategy, dvs. Members og UserWithRole ligger i samme tabel, men en discriminator-column til at adskille hvad der er hvad._

- -What are the pros & cons of using the Single Table Strategy for inheritance?

     _Bedst for performance, effektive polymorfiske queries. Til gengæld gør det at man har forskellige klasser i samme tabel at man ikke kan sætte NO_NULL på en kolonne som kun den ene klasse har, hvilket kan føre til dataintegritetsfejl._
  
- -how are passwords stored in the database with the changes suggested in part-6 of the exercise

     _Med security-pakken bliver passwords nu lagret i databasen i hashet form._
