# Persistencia JPA: Hibernate

## 1

- Configurar JPA a través del archivo de persistencia.xml 

  Luego de haber agregado las dependencias debemos configurar la base de datos con JPA a través del archivo persistence.xml.

  En la carpeta “resources” tendremos que crear una carpeta con nombre META-INF y dentro de ella crear el archivo persistence.xml los nombres deben tener ese exacto formato ya que es como son buscados internamente por JPA
  
  ![img_aula2_a](https://user-images.githubusercontent.com/103906625/235043354-b3d39321-7a84-4077-8670-a73ac7abd01b.jpeg)

  Dentro del archivo persistence.xml vamos a colocar las siguientes propiedades:
  
  ``` xml
  <persistence version="2.2"
    xmlns="http://xmlns.jcp.org/xml/ns/persistence"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">

  <persistence-unit name="tienda" transaction-type="RESOURCE_LOCAL">
  <class>br.com.alura.loja.modelo.Produto</class><!--opcional-->
          <properties>
  <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
      <property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:tienda"/>
      <property name="javax.persistence.jdbc.user" value="sa"/>
      <property name="javax.persistence.jdbc.password" value=""/>

  <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
  <property name="hibernate.hbm2ddl.auto" value="update"/>        
          </properties>
      </persistence-unit>
  </persistence>


- Mapear entidades JPA y sus relaciones
- Comprender cómo funciona el ciclo de vida de una entidad JPA

  Aquí vimos los diferentes estados de una entidad y como pasar de un estado a otro utilizando JPA:

  ![img_aula4_a](https://user-images.githubusercontent.com/103906625/235042651-fd0c3e0e-7351-434e-9cd4-c973e9ad8770.jpeg)

  Para eso vimos el esquema gráfico donde comenzamos por el estado transiente que tiene una entidad al ser instanciada, luego pasamos al estado Managed utilizando el método persist() de JPA hasta el momento que sincronizamos la información con la base de datos utilizando el método ```flush()``` o ```commit()``` de JPA.

- Realizar consultas a través de JPQL

## 2

- Modelar correctamente las relaciones bidireccionales
- Usar la función Seleccionar nueva para realizar consultas avanzadas
- Comprender la diferencia entre las relaciones EAGER y LAZY
- Descubrir la característica de búsqueda conjunta para planificar consultas
- Explore la API de criterios JPA
- Aprenda a mapear entidades usando herencia y clave compuesta
