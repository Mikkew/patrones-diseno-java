# �️ Patrones de Diseño Estructurales

Los **patrones de diseño estructurales** ayudan a organizar y relacionar clases y objetos para formar estructuras más grandes y flexibles, facilitando la reutilización y el mantenimiento del código.

> 💡 Estos patrones se centran en cómo se componen los objetos y las clases para formar sistemas más complejos.


## 🗂️ ¿Cuándo usar cada patrón estructural?

| Patrón      | Definición                                                                                                                                                                                    | ¿Cuándo implementarlo?                                                                 | Caso práctico |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|----------------------|
| Adapter     | Permite que objetos con interfaces incompatibles colaboren entre sí.                                                                                                                          | Cuando necesitas que dos interfaces incompatibles trabajen juntas.                     | <ul><li>[`AdapterPattern_01.java`](./c01_adapter/AdapterPattern_01.java)</li><li>[`AdapterPattern_02.java`](./c01_adapter/AdapterPattern_02.java)</li></ul> |
| Bridge      | Permite dividir una clase grande, o un conjunto de clases estrechamente relacionadas, en dos jerarquías separadas (abstracción e implementación) que pueden desarrollarse independientemente. | Cuando quieres separar una abstracción de su implementación para que evolucionen de forma independiente. | <ul><li>[`BridgePattern_01.java`](./c02_bridge/BridgePattern_01.java)</li><li>[`BridgePattern_02.java`](./c02_bridge/BridgePattern_02.java)</li><li>[`BridgePattern_03.java`](./c02_bridge/BridgePattern_03.java)</li></ul> |
| Composite   | Permite componer objetos en estructuras de árbol y trabajar con esas estructuras como si fueran objetos individuales.                                                                         | Cuando necesitas tratar objetos individuales y composiciones de objetos de manera uniforme. | <ul><li>[`CompositePattern_01.java`](./c03_composite/CompositePattern_01.java)</li><li>[`CompositePattern_02.java`](./c03_composite/CompositePattern_02.java)</li></ul> |
| Decorator   | Permite añadir funcionalidades a objetos colocando estos objetos dentro de objetos encapsuladores especiales que contienen estas funcionalidades.                                             | Cuando quieres añadir responsabilidades adicionales a un objeto de manera dinámica.    | <ul><li>[`DecoratorPattern_01.java`](./c04_decorator/DecoratorPattern_01.java)</li><li>[`DecoratorPattern_02.java`](./c04_decorator/DecoratorPattern_02.java)</li></ul> |
| Facade      | Proporciona una interfaz simple para un conjunto de interfaces en un subsistema, haciendo el subsistema más fácil de usar.                                                                    | Cuando quieres proporcionar una interfaz simple para un subsistema complejo.           | <ul><li>[`FacadePattern_01.java`](./c05_facade/FacadePattern_01.java)</li><li>[`FacadePattern_02.java`](./c05_facade/FacadePattern_02.java)</li></ul> |
| Flyweight   | Permite que un programa soporte grandes cantidades de objetos de forma eficiente compartiendo datos comunes entre ellos.                                                                      | Cuando necesitas manejar eficientemente un gran número de objetos similares.           | <ul><li>[`FlyweightPattern_01.java`](./c06_flyweight/FlyweightPattern_01.java)</li><li>[`FlyweightPattern_02.java`](./c06_flyweight/FlyweightPattern_02.java)</li></ul> |
| Proxy       | Permite proporcionar un sustituto o representante de otro objeto para controlar el acceso a este.                                                                                             | Cuando necesitas controlar el acceso a un objeto o añadir lógica extra al acceder a él.| <ul><li>[`ProxyPattern_01.java`](./c07_proxy/ProxyPattern_01.java)</li><li>[`ProxyPattern_02.java`](./c07_proxy/ProxyPattern_02.java)</li></ul> |


## 🎯 ¿Por qué usar patrones estructurales?

El diseño sin patrones estructurales puede llevar a:

- Código difícil de mantener y extender.
- Duplicación de lógica de composición.
- Dificultad para integrar sistemas o bibliotecas externas.
- Estructuras rígidas y poco flexibles.

Los patrones estructurales ayudan a:
- Componer objetos y clases de manera flexible.
- Reducir el acoplamiento entre componentes.
- Facilitar la integración y extensión del sistema.