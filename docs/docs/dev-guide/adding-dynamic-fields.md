---
sidebar_position: 1
title: Adding Dynamic Fields
---

# Adding Dynamic Fields to Entities

Our Odoo-clone architecture relies on a **Metadata-Driven UI** and a **JSONB Entity-Attribute-Value (EAV)** model. This means you can add new fields to an entity (like a Contact, User, or Sales Order) *without* writing complex database migrations, creating new database columns, or hardcoding new React components.

Every entity extending `BaseEntity` contains a `customAttributes` map mapped to PostgreSQL JSONB via Hibernate 6:

```java title="backend/src/main/java/com/odoo/clone/backend/core/model/BaseEntity.java"
@JdbcTypeCode(SqlTypes.JSON)
@Column(columnDefinition = "jsonb")
private Map<String, Object> customAttributes = new HashMap<>();