# Kusuma Ratih Hanindyani - 2306256406

<details>
<Summary><b>Module 1</b></Summary>

### Reflection 1

In implementing the Edit Product and Delete Product features for this Spring Boot application, I ensured that all variable and method names are self-explanatory. I structured my functions to perform only one task, ensuring they have no side effects. Instead of relying on unnecessary comments, I used clear function and variable names to improve code clarity, reducing the need for inline explanations.

From a secure coding perspective, I implemented null checks in findById to prevent potential NullPointerExceptions when accessing product properties. I also ensured that UUIDs are automatically assigned upon product creation, preventing duplicate or missing IDs.

One improvement I made was ensuring UUIDs are correctly handled in the repository instead of the controller. Initially, I didn’t use UUIDs at all, but I later improved my code by generating UUIDs in the repository layer while keeping productId as a String. This made the controller cleaner, as it no longer needed to process or convert UUIDs.


### Reflection 2

Writing unit tests helped me verify the correctness and stability of the code, as it ensures that each function behaves as expected under different conditions, including positive scenarios, negative inputs, and edge cases. While code coverage helps measure how much of the source code is tested, achieving 100% coverage does not guarantee bug-free code, as logical errors and unforeseen scenarios can still exist.

When implementing additional functional test suites, I noticed a potential clean code issue. There were duplicated setup logic and instance variables across multiple test classes. This redundancy increases maintenance effort and makes refactoring more difficult. A better approach would be to extract common setup procedures into a base test class. This improves maintainability, reduces duplication, and enhances code scalability, ensuring a more structured and efficient testing framework.

</details>

<details>
<Summary><b>Module 2</b></Summary>

### Reflection 1

During the exercise, I encountered several code issues, including low test coverage and incorrect redirect behavior in ProductController. Initially, my test coverage was 44%, which I improved by adding missing test cases for `ProductRepository` and `HomeController`. This helped me reach 98%, and with further refinements, I finally achieved 100% coverage. 
Another issue was the incorrect redirect path `redirect:list`, which caused navigation errors and infinite loops. I fixed this by using absolute paths like `redirect:/product/list`, ensuring proper redirection after creating, editing, or deleting products.

The CI/CD pipeline effectively supports Continuous Integration and Continuous Deployment by automating testing, code quality analysis, and deployment. As soon as changes pass the tests, the application is deployed, making the workflow efficient and seamless. It ensures that all changes are tested before deployment, reducing manual effort and improving software stability. This setup helps keep things running smoothly by following best practices, making development and deployment easier and more reliable.

</details>

<details>
<Summary><b>Module 3</b></Summary>

### Reflection 1

1. In my project, I applied the SOLID principles to keep the code organized and maintainable. I followed the Single Responsibility Principle (SRP) by separating `CarController` and `ProductController` so each class only handles its own tasks. The Open-Closed Principle (OCP) is implemented using interfaces like `CarService` and `ProductService`, allowing new features to be added without modifying existing code. The Liskov Substitution Principle (LSP) ensures that `CarServiceImpl` and `ProductServiceImpl` can replace their interfaces without issues, making the system more flexible.
2. These principles make my project easier to manage and expand. With SRP, changes to one part of the code do not affect other parts, reducing errors. OCP allows me to add a new service without changing existing classes. LSP makes sure that different implementations of a service can be used interchangeably. The Interface Segregation Principle (ISP) keeps interfaces small and focused, so classes do not have to implement unnecessary methods. Dependency Inversion Principle (DIP) ensures that controllers depend on service interfaces instead of specific implementations, making the system more flexible and testable.
3. Without SOLID, the project would be harder to maintain. If controllers depended on specific service implementations, switching to a different database or storage system would require major code changes. A lack of SRP would make classes too complex, and without OCP, adding new features could break existing code. Ignoring these principles would lead to tight coupling, making the system hard to modify and expand. By following SOLID, my code is clean, scalable, and easier to update.

</details>