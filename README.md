# android-base-compose

Rootstrap Android Copose Base
Use sample-app branch to check how to setup your project.

## Error handling 
We use the `ErrorHandler` class that extends `AbstractCoroutineContextElement` and implements `CoroutineExceptionHandler` to handle errors in coroutines.
To listen to those errors we use the `ErrorNotifier` singleton that exposes a flow of errors. Usually you will only need to listen to it in your app's main activity. In this template it is being listened in the `AppActivity` class

# Lint
How to check lint issues: ktlintCheck and ktlintFormat