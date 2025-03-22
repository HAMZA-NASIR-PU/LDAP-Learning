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

## **🚀 Separating Web Application and Resource Server in OAuth 2.0**  

### **Understanding the Separation**  
In an OAuth 2.0 architecture, a single application can act as both a **web application (client)** and a **resource server (API)**. However, for better security, scalability, and maintainability, these two roles can be separated into distinct applications:  

1. **Web Application (Client)** – A frontend or backend application responsible for user authentication. It obtains access tokens from the authorization server (e.g., Microsoft Entra ID).  
2. **Resource Server (API)** – A backend service that exposes protected APIs. It verifies and validates access tokens before serving requests.  

### **Why Separate Them?**  
- **Security** – Reduces the attack surface by keeping the authentication logic separate from business logic.  
- **Scalability** – Each service can be independently scaled based on load.  
- **Flexibility** – Allows different clients (web, mobile, or third-party apps) to access the API securely.  
- **Microservices Compatibility** – Aligns with modern architectures where multiple services communicate securely.  

### **How the Communication Works**  
1. The user logs into the **Web Application (Client)**, which redirects them to the **Authorization Server** (Microsoft Entra ID).  
2. Once authenticated, the **Web Application** receives an **access token** and stores it securely.  
3. The **Web Application** includes the **access token** in the `Authorization` header of API requests.  
4. The **Resource Server (API)** validates the access token using Microsoft Entra ID before responding.  

### **Example Implementation Using Spring Boot**  
#### **1. Web Application (Client)**
This application handles authentication and requests access tokens.  

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          azure:
            client-id: your-client-id
            client-secret: your-client-secret
            authorization-grant-type: authorization_code
            scope: openid, profile, email, api.read
        provider:
          azure:
            issuer-uri: https://login.microsoftonline.com/{tenant-id}/v2.0
```

#### **2. Resource Server (API)**
This application protects its endpoints by validating incoming tokens.  

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://login.microsoftonline.com/{tenant-id}/v2.0
```

And in Java:  
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/protected").authenticated()
                .anyRequest().permitAll()
            )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
}
```

### **Conclusion**  
Separating the web application and resource server in OAuth 2.0 provides better security, flexibility, and scalability. The web application handles authentication, while the resource server protects its APIs. This approach aligns well with microservices architecture and best practices.  

Best MS Entra ID OAuth 2.0 Articles:
https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-entra-developer-guide?tabs=SpringCloudAzure5x#protect-a-resource-serverapi
https://learn.microsoft.com/en-gb/entra/identity-platform/id-tokens
