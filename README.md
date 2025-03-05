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

2. Following SOLID principles makes my project easier to maintain. With SRP, modifying how cars are stored only requires changes in `CarRepository`, without affecting `CarServiceImpl` or `CarController`, making debugging and testing more efficient. OCP allows me to introduce improvements in `CarServiceImpl` or `ProductServiceImpl` without modifying `CarService` or `ProductService`, keeping the existing system stable. LSP ensures that if I replace `CarServiceImpl` with a different implementation, my project remains functional without errors. The Interface Segregation Principle (ISP) ensures that `CarService` and `ProductService` only contain relevant methods, preventing unnecessary dependencies in the controllers. Lastly, Dependency Inversion Principle (DIP) ensures that `CarController` depends on `CarService` instead of `CarServiceImpl`, making the system flexible and testable because I can easily swap service implementations without modifying the controllers.

3. Without SOLID principles, my project would be harder to manage. If `CarController` depended directly on `CarServiceImpl`, switching to a new storage system would require major changes to the controller. A lack of SRP would make `CarController` responsible for handling database logic, leading to large and complex classes that are difficult to modify. Without OCP, adding new features would require modifying existing service classes, increasing the risk of breaking the system. Ignoring DIP would tightly couple `CarController` to `CarServiceImpl`, making it impossible to replace the implementation without modifying the controller. By following SOLID principles, my project remains clean, scalable, and adaptable to future changes.

</details>

<details>
<Summary><b>Module 4</b></Summary>

### Reflection 1

1. By following the TDD workflow, I was able to improve my code correctness, maintainability, and workflow structure. The RED commits helped in clarifying expectations before implementing features. An example would be writing the Order model tests to ensure that invalid statuses were rejected early. The GREEN commits helped in implementing only the necessary logic to pass tests. The REFACTOR commits helped me in cleaning up hardcoded strings which improved maintainability without breaking existing functionality. In order to improve future tests, I would need to improve the edge case coverage.

2. F.I.R.S.T. principle:
- Fast: My unit tests ran efficiently as they did not interact with external dependencies . Using Mockito to mock `OrderRepository` sped up execution. 
- Independent: Tests were isolated and did not depend on each other by using `@BeforeEach` ensuring a clean state before every test. Mocking prevented unintended dependencies, ensuring test independence.
- Repeatable: Since no external resources were used tests produced consistent results on every run. Tests like `testSetStatusToCancelled()` ran reliably regardless of previous executions.
- Self-validating: I used assertions like `assertEquals()`, `assertThrows()`, and `assertTrue()` to ensure clear pass/fail results. Error handling tests, such as `testUpdateStatusInvalidOrderId()`, correctly validated exceptions.
- Timely: Tests were written before implementation, ensuring the code was only developed to pass the necessary requirements.

</details>