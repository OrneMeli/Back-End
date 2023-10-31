# trabajo-final-backend
TP final backend

[Letra de la consigna AQUÍ](https://docs.google.com/document/d/1WWAushxuiv35CR3xMlotsx9fRNEOT2JX/edit)

## Tasks a hacer
- [ ] Crear proyecto en Spring con h2, web y
- [ ] Agregar a mano las dependencias de Junit y log4j
- [ ] Agregar application.properties y log4j.properties
- [ ] Crear 4 carpetas:
  - [ ] controller
  - [ ] service
  - [ ] persistence
    - [ ] dao 
  - [ ] model


* ### Controller (@RestController + @RequestMapping("/rutaCorrespondiente"))
Capa que expone los métodos HTTP con @GetMapping, @PostMapping, etc. El @RequestMapping le da genéricamente un inicio de url. Las funciones son las que ejecutan las funciones del service. En general, las funciones tienen como valor de retorno un ResponseEntity<T>. Como necesita una instancia del Service, solamente hace falta hacer un @Autowired al atributo Service que haga falta (sin instanciar el constructor).

* ### Service (@Service)
Capa que ejecuta la lógica de negocio. Se trae un @Autowired del Repository que corresponda.

* ### Persistence
Capa que se conecta con la base de datos. Todas las conexiones, los stmts, etc se hacen en esta capa.
  - **Entities**: Se instancian como @Entity, y se van armando los campos de la tabla. Se puede mapear como @Table, y luego asume que los nombres de los atributos son los nombres de las columnas, o también se puede agregar @Column(name="new_name") si se tuviera que cambiar el nombre de la columna. Es importante saber cuál de los atributos es el @Id.
  - **Repository**: son las capas que finalmente se conectan con la base de datos correspondiente. Habrá una conexión de ORM (JPA) según el tipo de base de datos (MySQL, H2, etc). Se usa un __public interface ModelRepository extends JPARepository<T, Integer>__

* ### Model (DTO)
Representación del modelo de negocio en clase. Los atributos de estos modelos son los que finalmente el frontend va a consumir. No necesariamente tiene las mismas propiedades que vienen de la base de datos.

