/**
 * ! Patrón Builder:
 * Es un patrón de diseño creacional que nos permite construir objetos complejos
 * paso a paso.
 *
 * El patrón nos permite producir distintos tipos y representaciones
 * de un objeto empleando el mismo código de construcción.
 *
 * * Es útil cuando necesitamos construir un objeto complejo con muchas partes
 * * y queremos que el proceso de construcción sea independiente de las partes
 * * que lo componen.
 */

package com.mms.patterns.desing.p01_creacionales.c01_builder;

import java.util.ArrayList;
import java.util.List;

// Builder para construir consultas SQL de manera fluida y flexible
class QueryBuilder {
    private String table;
    private List<String> fields = new ArrayList<>();
    private List<String> conditions = new ArrayList<>();
    private List<String> orderFields = new ArrayList<>();
    private Integer limitCount;

    public QueryBuilder(String table) {
        this.table = table;
    }

    public QueryBuilder select(String... fields) {
        this.fields = List.of(fields);
        return this;
    }

    public QueryBuilder where(String condition) {
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder orderBy(String field, String direction) {
        if (!direction.equalsIgnoreCase("ASC") && !direction.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Dirección no válida. Use 'ASC' o 'DESC'");
        }
        this.orderFields.add(field + " " + direction.toUpperCase());
        return this;
    }

    public QueryBuilder orderBy(String field) {
        return orderBy(field, "ASC");
    }

    public QueryBuilder limit(int count) {
        this.limitCount = count;
        return this;
    }

    public String execute() {
        final String fieldsClause = !fields.isEmpty() ? String.join(", ", fields) : "*";
        final String whereClause = !conditions.isEmpty() ? "WHERE " + String.join(" AND ", conditions) : "";
        final String orderByClause = !orderFields.isEmpty() ? "ORDER BY " + String.join(", ", orderFields) : "";
        final String limitClause = limitCount != null ? "LIMIT " + limitCount : "";

        return String.format("SELECT %s FROM %s %s %s %s",
                        fieldsClause,
                        table,
                        whereClause,
                        orderByClause,
                        limitClause)
                .replaceAll("\\s+", " ") // Replace multiple spaces with single space
                .trim();
    }
}

/**
 * Clase demostrativa del patrón Builder aplicado a la construcción de consultas SQL.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que una aplicación necesita generar consultas SQL dinámicamente según los filtros y opciones que el usuario seleccione.
 * Usando el patrón Builder, se puede construir la consulta paso a paso, agregando solo las partes necesarias (campos, condiciones, orden, límite),
 * evitando errores de sintaxis y mejorando la legibilidad del código.
 *
 * Así, el código es más flexible, fácil de mantener y de extender para nuevas opciones de consulta.
 */
public class BuilderPattern_02 {
    public static void main(String[] args) {
        final QueryBuilder query = new QueryBuilder("users")
                .select("id", "name", "email")
                .where("age > 20")
                .where("country = 'CHI'")
                .orderBy("name")
                .orderBy("age", "DESC")
                .limit(10);

        final String sql = query.execute();
        System.out.println(sql);
    }
}
