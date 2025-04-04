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

https://www.amazon.in/LDAP-Programming-JavaTM-Rob-Weltman/dp/0201657589
https://www.amazon.com/Programming-Management-Integration-Clayton-Donley/dp/1930110405

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
