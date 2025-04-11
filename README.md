# **LDAP Learning Repository**  

This repository is dedicated to my journey of learning **Lightweight Directory Access Protocol (LDAP)**. Here, I will document concepts, implementations, and hands-on exercises to build a strong understanding of LDAP.  

## **What You'll Find Here**  
✅ LDAP fundamentals and concepts  
✅ Setting up an LDAP server locally  
✅ Performing LDAP queries and authentication  
✅ LDAP integration with Java and Spring Boot  
✅ Managing users, groups, and roles in LDAP  
✅ Advanced LDAP topics like security and replication  
✅ Real-world examples and best practices  

## **Why This Repository?**  
I created this repository to document my learning and share practical implementations with others who are also interested in LDAP. Contributions and discussions are always welcome!  

🚀 **Stay tuned for more updates!**  

---

- https://www.amazon.in/LDAP-Programming-JavaTM-Rob-Weltman/dp/0201657589
- https://www.amazon.com/Programming-Management-Integration-Clayton-Donley/dp/1930110405
- https://directory.apache.org/apacheds/basic-ug/1.2-some-background.html
- https://vvratha.blogspot.com/2011/10/creating-partitions-in-apacheds.html?utm_source=chatgpt.com
- https://directory.apache.org/apacheds/basic-ug/1.1-what-apacheds-is.html

According to  Wiki:
**The Lightweight Directory Access Protocol (LDAP /ˈɛldæp/) is an open, vendor-neutral, industry standard application protocol for accessing and maintaining distributed directory information services over an Internet Protocol (IP) network.[1] Directory services play an important role in developing intranet and Internet applications by allowing the sharing of information about users, systems, networks, services, and applications throughout the network.[2] As examples, directory services may provide any organized set of records, often with a hierarchical structure, such as a corporate email directory. Similarly, a telephone directory is a list of subscribers with an address and a phone number.**

## LDAP PROGRAMMING WITH JAVA By Rob Weltman and Tony Dahbura

**Part 1**
- Chapter 1 What can you Find in a Directory ?
- Chapter 2 The Lingua Franca of Directories is LDAP
- Chapter 3 May we Introduce - Direcory for SDK for Java

- Sneakernet
- Transactional integrity in databases means ensuring that all operations within a transaction are completed successfully, or none of them are, maintaining data consistency and integrity. This is often achieved through the ACID properties: Atomicity, Consistency, Isolation, and Durability.
- Jini—the framework for Java object lookup and collaboration.
- Xerox Clearinghouse
- Grapevine
- Network Information System (NIS) and NIS+
- X.500: The “Heavyweight” Directory Service
- A `directory information tree (DIT)` is data represented in a hierarchical tree-like structure consisting of the Distinguished Names (DNs) of directory service entries. Both the X. 500 protocols and the Lightweight Directory Access Protocol (LDAP) use directory information trees as their fundamental data structure.
- Java Naming and Directory Interface (JNDI)
- The Java Naming and Directory Interface (JNDI) is a Java API for a directory service that allows Java software clients to discover and look up data and resources (in the form of Java objects) via a name. Like all Java APIs that interface with host systems, JNDI is independent of the underlying implementation.
- The root DSE is the entry at the top of the LDAP server directory information tree. All the namingcontexts (suffixes) in the LDAP server are directly below the root DSE. The root DSE contains information about the LDAP server, including the namingcontexts that are configured and the capabilities of the server. Each directory server has a unique entry called RootDSE. It provides data about the server, such as its capabilities, the LDAP version it supports, and the naming contexts it uses.
- A `Bind DN` is an object that you bind to inside LDAP to give you permissions to do whatever you're trying to do. Some (many?) LDAP instances don't allow anonymous binds, or don't allow certain operations to be conducted with anonymous binds, so you must specify a bindDN to obtain an identity to perform that operation. In a similar non-technical way - and yes this is a stretch - a bank will allow you to walk in and look at their interest rates without giving them any sort of ID, but in order to open an account or withdraw money, you have to have an identity they know about - that identity is the bindDN.

## Difference between Base DN and Bind DN

### 1. **Bind DN (Distinguished Name)**:
The **Bind DN** is the **username** or **identity** that is used to authenticate (or bind) to the LDAP server. It identifies the user (or service) that is requesting access to the LDAP directory and is used to establish a connection.

- **Purpose**: The Bind DN is used to authenticate to the server. The LDAP server verifies the credentials associated with the Bind DN (typically paired with a password) to allow the connection.
- **Format**: It is typically a full Distinguished Name (DN), which is a unique identifier for an entry in the directory.
  
Example of a Bind DN:
- `uid=admin,ou=system` – In this example, the `uid=admin` specifies the user ID (`admin`), and `ou=system` specifies that the user is in the `system` organizational unit. This Bind DN could be for an administrator account that has permission to perform various operations on the LDAP server.

### 2. **Base DN**:
The **Base DN** defines the starting point or root of the directory tree where the LDAP search begins. It specifies the scope or the location within the directory structure from which to start searching for entries. 

- **Purpose**: The Base DN is used to specify where to start searches within the directory. It's like saying "begin searching from here" in the LDAP hierarchy. It is not involved in authentication; it's purely for the purpose of searching or querying the directory.
- **Format**: Like the Bind DN, the Base DN is also a Distinguished Name (DN) that represents the "root" or starting point of the search in the directory.

Example of a Base DN:
- `dc=example,dc=com` – This would typically represent the root of the directory for an organization or domain. The `dc` stands for "domain component," and in this case, it's referring to the domain `example.com`.

### Key Differences:

| **Aspect**         | **Bind DN**                                | **Base DN**                                |
|--------------------|--------------------------------------------|--------------------------------------------|
| **Purpose**        | Used for **authentication** to the LDAP server. | Used for specifying the **starting point** for LDAP searches. |
| **Role**           | Identifies the user or service binding to the server (typically a full DN). | Defines where to start a search in the directory. |
| **Example**        | `uid=admin,ou=system`                      | `dc=example,dc=com`                        |
| **Usage in Code**  | Used when creating a connection and binding to the LDAP server (e.g., to authenticate). | Used in search operations to define where the search should start. |

### Example to Clarify:

Imagine you have the following LDAP structure:

```
dn: dc=example,dc=com
    ├── ou=users
    │   ├── uid=user1
    │   └── uid=user2
    └── ou=groups
        ├── cn=admins
        └── cn=users
```

- **Bind DN**: This would be the identity used to authenticate, such as `uid=admin,ou=system,dc=example,dc=com`. This is the administrator who has permission to access and modify entries within the LDAP server.
- **Base DN**: For a search, you might start from the top of the directory, such as `dc=example,dc=com`, or you might want to search within a specific organizational unit, like `ou=users,dc=example,dc=com`, to find entries under that organizational unit.

### How They Work Together in Code:

When you connect to the LDAP server:

1. **Bind DN**: The server will authenticate the user represented by the Bind DN.
2. **Base DN**: Once authenticated, any search operation will use the Base DN to know where in the directory to begin searching.

### Example in Code:

```java
String bindDN = "uid=admin,ou=system,dc=example,dc=com";  // User used to authenticate
String baseDN = "dc=example,dc=com";  // The base where you start your search

// Bind to the server with the Bind DN
LDAPConnection connection = new LDAPConnection(serverAddress, portNumber, bindDN, password);

// Perform a search starting from the Base DN
SearchResult searchResult = connection.search(baseDN, SearchScope.SUB, "(objectClass=*)", "*");
```

- **Bind DN** is used to authenticate the connection (i.e., "who you are").
- **Base DN** is used to define the root location where the search starts (i.e., "where to search").

---

## What is an LDIF File ?
An **LDIF** (LDAP Data Interchange Format) file is a **standard plain-text format** used to represent directory entries for **LDAP (Lightweight Directory Access Protocol)**-based directories, like OpenLDAP or Microsoft Active Directory.

### 🗂️ What LDIF Is Used For:
- Importing/exporting directory data
- Modifying LDAP directory entries
- Backing up or transferring data between LDAP servers

---

### 🔧 LDIF File Structure:
Each **entry** represents an LDAP object and follows this structure:

```ldif
dn: cn=John Doe,ou=users,dc=example,dc=com
objectClass: inetOrgPerson
cn: John Doe
sn: Doe
givenName: John
mail: john.doe@example.com
```

- `dn`: Distinguished Name (unique path in the directory tree)
- `objectClass`: Type of LDAP object (like `inetOrgPerson`, `organizationalUnit`)
- Attributes: Key-value pairs like `cn`, `sn`, `mail`

---

### 📁 File Extension:
`.ldif`

---

### ✏️ LDIF for Modifications:
LDIF can also represent **add**, **modify**, or **delete** operations:

#### Example: Modify Entry
```ldif
dn: cn=John Doe,ou=users,dc=example,dc=com
changetype: modify
replace: mail
mail: john.d.new@example.com
```

---

### 🛠️ Common Tools that Use LDIF:
- `ldapadd` (adds entries)
- `ldapmodify` (modifies entries)
- `ldapsearch` (can export in LDIF format)

---

## 🔐 What is SASL?

**Simple Authentication and Security Layer (SASL)** is a framework for authentication and data security in Internet protocols.  
It **decouples authentication mechanisms** from application protocols like SMTP, IMAP, LDAP, XMPP, etc.

---

## 🧩 SASL: A Pluggable Framework

SASL is not an authentication protocol by itself — it's a **framework** that allows different authentication mechanisms to be plugged into application protocols.

> ✨ Think of it as a **universal adapter** for authentication.

---

## 🛠️ How SASL Works

1. **Client Requests Authentication**  
   🧑‍💻 Client wants to access a service (e.g., email server).
   
2. **Server Offers SASL Mechanisms**  
   🖥️ Server says: "I support PLAIN, DIGEST-MD5, SCRAM, etc."

3. **Client Picks One**  
   ✔️ Client chooses the best supported mechanism.

4. **Exchange Happens**  
   🔄 Challenge-response communication follows.

5. **Access Granted or Denied**  
   ✅ or ❌

---

## 🔍 Common SASL Mechanisms

| 🔑 Mechanism     | 🔎 Description                                 |
|------------------|-----------------------------------------------|
| **PLAIN**        | Sends username & password in plain text (base64-encoded). Use only over TLS! |
| **CRAM-MD5**     | Challenge-response with HMAC-MD5 hash         |
| **DIGEST-MD5**   | More secure, supports integrity and encryption |
| **SCRAM**        | Salted Challenge Response Authentication       |
| **GSSAPI**       | Used with Kerberos for strong enterprise auth  |
| **EXTERNAL**     | Authentication through external mechanism like TLS client certs |

---

## 🌐 Where is SASL Used?

- 📬 **SMTP** – Email sending
- 📥 **IMAP / POP3** – Email receiving
- 📡 **XMPP** – Instant messaging (e.g., Jabber)
- 🧾 **LDAP** – Directory access (e.g., Active Directory)

---

## 🧱 Why Use SASL?

✅ **Flexibility** – Pluggable auth modules  
✅ **Security** – Mechanisms support integrity, encryption  
✅ **Standardized** – Widely supported in modern protocols  
✅ **Extensible** – New mechanisms can be added as needed  

---

## 🧠 Quick Analogy

> SASL is like a **socket** in the wall —  
> You can plug in a phone charger, laptop, or any device (i.e., authentication mechanism).  
> The socket stays the same, but the plug can change.

---

## ✨ Hierarchy in object classes

In LDAP, **`person`** and **`organizationalPerson`** are **child classes (subclasses)** of the `top` class. LDAP uses an **object-oriented hierarchy**, so object classes inherit from one another.

---

### 🧬 Hierarchy Example:

Here's a simplified inheritance chain:

```
top
└── person
    └── organizationalPerson
        └── inetOrgPerson (commonly used for user entries)
```

- `top` ➝ base class (every object class ultimately inherits from it).
- `person` ➝ adds `cn` (common name), `sn` (surname), etc.
- `organizationalPerson` ➝ adds things like `title`, `ou` (organizational unit), etc.
- `inetOrgPerson` ➝ adds internet-related attributes like `mail`, `uid`, etc.

---

### 📌 Why This Matters:
Inheritance allows LDAP entries to:
- Include all attributes from parent classes.
- Be validated according to the schema rules.
- Be extensible without duplicating attribute definitions.

---

## ✨ Some Crucial Java Naming and Directory Interface(JNDI) interfaces/classes and Spring LDAP

https://docs.spring.io/spring-ldap/reference/introduction.html

- javax.naming.directory.DirContext
- java.util.HashTable
- javax.naming.NamingEnumeration
- javax.naming.SearchControls
- org.springframework.ldap.core.LdapClient
- org.springframework.ldap.core.LdapTemplate
- org.springframework.ldap.core.AttributesMapper
- org.springframework.ldap.query.DefaultContainerCriteria
- org.springframework.ldap.core.DirContextOperations
- org.springframework.ldap.core.support.BaseLdapPathContextSource
- org.springframework.ldap.core.support.LdapContextSource
- org.springframework.security.authentication.UsernamePasswordAuthenticationToken
- org.springframework.security.ldap.authentication.LdapAuthenticationProvider works as AuthenticationManager
- org.springframework.security.authentication.ProviderManager
- java.security.Principal
- org.springframework.security.core.Authentication

`org.springframework.security.authentication.UsernamePasswordAuthenticationToken` extends `org.springframework.security.authentication.AbstractAuthenticationToken` implements `org.springframework.security.core.Authentication` extends 'java.security.Principal' and `java.io.Serializable`

## ✨ LDAP BEST TUTORIALS

- https://www.zytrax.com/books/ldap/ch3/
- https://docs.oracle.com/javase/jndi/tutorial/ldap/schema/object.html
- https://www.digitalocean.com/community/tutorials/understanding-the-ldap-protocol-data-hierarchy-and-entry-components
- https://www.ibm.com/docs/en/i/7.4.0?topic=ldap-directory-server-concepts
- https://www.ibm.com/docs/en/sdse/6.3.1?topic=troubleshooting-directory-server-overview
- https://www.ibm.com/docs/en/sdsu/8.0.1?topic=directory-server-overview
- https://docs.spring.io/spring-ldap/docs/1.3.x/reference/html/odm.html

Best Docs for Spring LDAP ==> https://docs.spring.io/spring-ldap/docs/1.3.x/reference/html/index.html
