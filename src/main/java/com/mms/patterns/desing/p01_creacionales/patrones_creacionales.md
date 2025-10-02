# 🌱 Patrones de Diseño Creacionales

Los **patrones de diseño creacionales** son un conjunto de soluciones probadas para resolver problemas comunes relacionados con la **creación de objetos** en programación orientada a objetos.  
Su objetivo principal es **desacoplar la creación de objetos de su uso**, permitiendo un código más flexible, mantenible y escalable.

> 💡 En lugar de usar `new Clase()` directamente, estos patrones ofrecen formas más inteligentes y controladas de instanciar objetos.



## 🗂️ ¿Cuándo usar cada patrón creacional?

| Patrón                | Definición | ¿Cuándo implementarlo?                                                                 | Caso práctico                             |
|-----------------------|------------------------------|---------------------------------------------------------------------------------------|-------------------------------------------------------------|
| Builder               | Permite construir objetos complejos paso a paso. El patrón permite producir diferentes tipos y representaciones de un objeto usando el mismo proceso de construcción. | Cuando la creación de un objeto complejo requiere varios pasos o configuraciones.     | <ul><li>[`BuilderPattern_01.java`](./c01_builder/BuilderPattern_01.java)</li><li>[`BuilderPattern_02.java`](./c01_builder/BuilderPattern_02.java)</li></ul> |
| Factory Method        | Define una interfaz para crear objetos, pero permite que las subclases alteren el tipo de objetos que se crearán. | Cuando quieres delegar la creación de objetos a subclases o permitir extensibilidad.  | <ul><li>[`FactoryMethodPattern_01.java`](./c02_factory_method/FactoryMethodPattern_01.java)</li><li>[`FactoryMethodPattern_02.java`](./c02_factory_method/FactoryMethodPattern_02.java)</li></ul> |
| Abstract Factory      | Permite producir familias de objetos relacionados sin especificar sus clases concretas. | Cuando necesitas crear familias de objetos relacionados sin acoplarte a sus clases.   | <ul><li>[`AbstractFactoryPattern_01.java`](./c03_abstract_factory/AbstractFactoryPattern_01.java)</li><li>[`AbstractFactoryPattern_02.java`](./c03_abstract_factory/AbstractFactoryPattern_02.java)</li></ul> |
| Prototype             | Permite copiar objetos existentes sin que el código dependa de sus clases. | Cuando necesitas clonar objetos existentes de manera eficiente y flexible.            | <ul><li>[`PrototypePattern_01.java`](./c04_prototype/PrototypePattern_01.java)</li><li>[`PrototypePattern_02.java`](./c04_prototype/PrototypePattern_02.java)</li></ul> |
| Inmutabilidad         | Crea objetos cuyo estado no puede cambiar después de su creación. | Cuando necesitas objetos que no cambian su estado después de ser creados.             | <ul><li>[`InmutabilidadPattern_01.java`](./c05_inmutabilidad/InmutabilidadPattern_01.java)</li><li>[`InmutabilidadPattern_02.java`](./c05_inmutabilidad/InmutabilidadPattern_02.java)</li></ul> |
| Singleton             | Garantiza que una clase tenga una única instancia y proporciona un punto de acceso global a ella. | Cuando necesitas una única instancia global y controlada de una clase.                | <ul><li>[`SingletonPattern_01.java`](./c06_singleton/SingletonPattern_01.java)</li><li>[`SingletonPattern_02.java`](../../main/java/com/mms/patterns/desing/p01_creacionales/c06_singleton/SingletonPattern_02.java)</li><li>[`SingletonPattern_03.java`](./c06_singleton/SingletonPattern_03.java)</li></ul> |
| Factory Function      | Permite crear objetos usando funciones, facilitando la creación flexible y reutilizable. | Cuando quieres crear objetos de manera flexible usando funciones en vez de clases.    | <ul><li>[`FactoryFunctionPattern_01.java`](./c07_factory_function/FactoryFunctionPattern_01.java)</li><li>[`FactoryFunctionPattern_02.java`](.c07_factory_function/FactoryFunctionPattern_02.java)</li></ul> |


## 🎯 ¿Por qué usar patrones creacionales?

La creación directa de objetos puede llevar a:

- Código rígido y difícil de modificar.
- Alto acoplamiento entre clases.
- Dificultad para extender o reutilizar.
- Lógica de creación dispersa.

Los patrones creacionales ayudan a:
- Centralizar la lógica de creación.
- Soportar diferentes tipos de objetos sin cambiar el código cliente.
- Mejorar la reutilización y mantenibilidad.