# 🤝 Patrones de Diseño de Comportamiento

Los **patrones de comportamiento** se enfocan en cómo interactúan los objetos y clases entre sí, definiendo la comunicación, la asignación de responsabilidades y el flujo de control en el sistema.

> 💡 Estos patrones permiten diseñar sistemas flexibles, desacoplados y fáciles de mantener, facilitando la colaboración entre objetos.


## 🗂️ ¿Cuándo usar cada patrón de comportamiento?

| Patrón                | Definición | ¿Cuándo implementarlo?                                                                 | Caso práctico en el proyecto |
|-----------------------|------------------------------|---------------------------------------------------------------------------------------|-----------------------------|
| Chain of Responsibility | Permite pasar solicitudes a lo largo de una cadena de manejadores. Cada manejador decide procesar la solicitud o pasarla al siguiente. | Cuando quieres pasar una solicitud a través de una cadena de manejadores hasta que alguno la procese. | <ul><li>[`ChainResponsibilityPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c01_chain_responsibility/ChainResponsibilityPattern_01.java)</li><li>[`ChainResponsibilityPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c01_chain_responsibility/ChainResponsibilityPattern_02.java)</li></ul> |
| Command               | Convierte una solicitud en un objeto independiente que contiene toda la información sobre la solicitud. | Cuando necesitas encapsular una petición como un objeto para parametrizar acciones, colas o deshacer operaciones. | <ul><li>[`CommandPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c02_command/CommandPattern_01.java)</li><li>[`CommandPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c02_command/CommandPattern_02.java)</li></ul> |
| Iterator              | Permite recorrer elementos de una colección sin exponer su representación subyacente. | Cuando necesitas recorrer una colección de objetos sin exponer su representación interna. | <ul><li>[`IteratorPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c03_iterator/IteratorPattern_01.java)</li><li>[`IteratorPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c03_iterator/IteratorPattern_02.java)</li><li>[`IteratorPattern_03.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c03_iterator/IteratorPattern_03.java)</li></ul> |
| Mediator              | Permite reducir las dependencias caóticas entre objetos comunicando a través de un objeto mediador central. | Cuando quieres reducir las dependencias directas entre objetos que interactúan mucho entre sí. | <ul><li>[`MediatorPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c04_mediator/MediatorPattern_01.java)</li><li>[`MediatorPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c04_mediator/MediatorPattern_02.java)</li></ul> |
| Memento               | Permite guardar y restaurar el estado previo de un objeto sin revelar los detalles de su implementación. | Cuando necesitas guardar y restaurar el estado previo de un objeto sin violar su encapsulamiento. | <ul><li>[`MementoPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c05_memento/MementoPattern_01.java)</li><li>[`MementoPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c05_memento/MementoPattern_02.java)</li></ul> |
| Observer              | Permite que objetos suscriptores reciban notificaciones automáticas de los cambios que ocurren en otros objetos. | Cuando varios objetos necesitan ser notificados automáticamente de cambios en otro objeto. | <ul><li>[`ObserverPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c06_observer/ObserverPattern_01.java)</li><li>[`ObserverPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c06_observer/ObserverPattern_02.java)</li></ul> |
| State                 | Permite que un objeto altere su comportamiento cuando cambia su estado interno. | Cuando el comportamiento de un objeto debe cambiar según su estado interno. | <ul><li>[`StatePattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c07_state/StatePattern_01.java)</li><li>[`StatePattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c07_state/StatePattern_02.java)</li></ul> |
| Strategy              | Permite definir una familia de algoritmos, ponerlos en clases separadas y hacer sus objetos intercambiables. | Cuando necesitas definir una familia de algoritmos y hacerlos intercambiables en tiempo de ejecución. | <ul><li>[`StrategyPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c08_strategy/StrategyPattern_01.java)</li><li>[`StrategyPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c08_strategy/StrategyPattern_02.java)</li></ul> |
| Template Method       | Define el esqueleto de un algoritmo en una clase base, permitiendo que las subclases redefinan ciertos pasos. | Cuando quieres definir el esqueleto de un algoritmo y permitir que las subclases redefinan ciertos pasos. | <ul><li>[`TemplateMethodPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c09_template_method/TemplateMethodPattern_01.java)</li><li>[`TemplateMethodPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c09_template_method/TemplateMethodPattern_02.java)</li></ul> |
| Visitor               | Permite añadir nuevas operaciones a objetos existentes sin modificar sus clases. | Cuando necesitas realizar operaciones sobre una estructura de objetos sin modificar sus clases. | <ul><li>[`VisitorPattern_01.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c10_visitor/VisitorPattern_01.java)</li><li>[`VisitorPattern_02.java`](../../main/java/com/mms/patterns/desing/p03_comportamiento/c10_visitor/VisitorPattern_02.java)</li></ul> |


## 🎯 ¿Por qué usar patrones de comportamiento?

El diseño sin patrones de comportamiento puede llevar a:

- Código rígido y difícil de modificar.
- Objetos con demasiadas responsabilidades.
- Alta dependencia entre clases.
- Dificultad para reutilizar lógica de interacción.

Los patrones de comportamiento ayudan a:
- Desacoplar la comunicación entre objetos.
- Repartir responsabilidades de forma clara.
- Facilitar la extensión y el mantenimiento del sistema.