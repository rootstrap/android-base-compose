# Domain Module

This module contains the Models used across the app.

## Contents

- Models 
- Repositories 
- Errors 

## Models

This package contains the models used across the app. These are simple Kotlin data classes that represent the core entities in the app.

Examples:

- User
- Post
- Comment

## Repositories

This package contains the repository interfaces that are implemented in the Data layer.
Keeping these interfaces in the Domain layer allows the Data layer to remain isolated.

Examples:

- IUserRepository
- IPostRepository
- ICommentRepository

## Errors

Contains error handling related classes like:

- ErrorNotifier - Used to notify errors to the UI
- ErrorHandler - Handles exceptions in coroutines
