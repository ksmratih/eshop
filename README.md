# Kusuma Ratih Hanindyani - 2306256406

## Module 1

### Reflection 1

In implementing the Edit Product and Delete Product features for this Spring Boot application, I ensured that all variable and method names are self-explanatory. I structured my functions to perform only one task, ensuring they have no side effects. Instead of relying on unnecessary comments, I used clear function and variable names to improve code clarity, reducing the need for inline explanations.

From a secure coding perspective, I implemented null checks in findById to prevent potential NullPointerExceptions when accessing product properties. I also ensured that UUIDs are automatically assigned upon product creation, preventing duplicate or missing IDs.

One improvement I made was ensuring UUIDs are correctly handled in the repository instead of the controller. Initially, I didn’t use UUIDs at all, but I later improved my code by generating UUIDs in the repository layer while keeping productId as a String. This made the controller cleaner, as it no longer needed to process or convert UUIDs.