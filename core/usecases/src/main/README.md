# UseCases Module

This module contains the use cases of the app.

## Contents

- Repositories 
- Use Cases 

## Repositories

This package contains the repository interfaces that are implemented in the Data layer. 
Keeping these interfaces in the Domain layer allows the Data layer to remain isolated.

Examples:

- IUserRepository
- IPostRepository
- ICommentRepository

## Use Cases

This package contains use case classes that encapsulate the app's business logic.

Examples:

- GetUsersUseCase
- GetPostUseCase
- AddCommentUseCase

