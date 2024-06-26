# Real Estate Api
Building an API for managing office rental websites with high-level logic applying best practices in Java, Spring Framework including technologies such as Spring MVC, Spring Boot, Spring Security, Restful Web Service, Java 8, JPA, Spring Data JPA...

Especially, in this project, I have utilized tools such as Generic, Reflection, Annotation in Java 8 to self-build a framework similar to Spring Framework to apply to my project.
## Functionality to search for buildings based on customer requirements
- Search by approximate name: Building Name, Ward, Street, Direction, Grade...
- Search From_To: Room Rent Price, Floor Area...
- Exact search: Floor Area, Number of Basement Floors, District Code, Building Manager, Building Type...

### Some search functionalities required
- Search by address: address = street + ward + district
- Search by building manager: staffid
- Search by district: district_code
- Search by rental area: search by value or search by array [100,200]
- .....

## Search functionality has been updated with additional methods
1. Original pure code
- Runnable, lengthy code, not optimized
2. Apply Map
- Runs well, more optimized code, builds MapUtils function, uses Jdbc so the amount of code is still large
3. Apply Buider Pattern, Jpa
- Runs well, code is optimized, more concise, learned new techniques

