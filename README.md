# android-base-compose

Rootstrap Android Copose Base
Use sample-app branch to check how to setup your project.

## Error handling 
We use ErrorHandler that extends AbstractCoroutineContextElement and implements CoroutineExceptionHandler to handle errors in coroutines.
To listen to those errors we use ErrorNotifier singleton that exposes a flow of errors. There is an example of how to listen to those errors in AppActivity.kt
