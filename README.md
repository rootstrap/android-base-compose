# android-base-compose

Android Compose Base is a boilerplate project created by Rootstrap for new projects using Kotlin and Jetpack Compose. 
The main objective is helping any new projects jump-start into feature development by providing a solid foundation.

## How to use this template
You can use this open-source project as a template of your new Android projects. Steps:

1. **Clone**: Clone the repository to your local machine.

2. **Build with Android Studio**: Open the project in Android Studio and build it.

Use Example module as an example of how to setup your project (see the login/sign up examples).

## Tech Stack

- Minimum SDK level 24
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) for in-app navigation
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) from Architecture Components
- [Retrofit](https://square.github.io/retrofit/) for network requests
- [Coil](https://coil-kt.github.io/coil/) for image loading
- [Koin](https://insert-koin.io/) for dependency injection

## Architecture

The app uses MVVM architecture and Clean Architecture principles with a separation of concerns between:

- **Presentation Layer**: ViewModels and Compose UI. ViewModels retrieve data from use-cases and expose it to Compose UI.
- **Domain Layer**: Models
- **Use-cases**: Business logic
- **Data Layer**: Repositories, network, local data sources

## Error handling 
We use the `ErrorHandler` class that extends `AbstractCoroutineContextElement` and implements `CoroutineExceptionHandler` to handle errors in coroutines.
To listen to those errors we use the `ErrorNotifier` singleton that exposes a flow of errors. Usually you will only need to listen to it in your app's main activity. In this template it is being listened in the `AppActivity` class

## Lint

To check lint issues, use `ktlintCheck` and `ktlintFormat`.

## State management

UI package contains a base ViewModel, `BaseViewModel`, designed for use in Android applications developed in Kotlin.
It extends `ViewModel` and is designed to be used with Jetpack Compose UI components.
This base ViewModel facilitates state and navigation event management within Android UI components.

- **State Management**: Utilizes `MutableStateFlow` for managing UI state, providing a thread-safe way to observe and update the UI state reactively.
- **Navigation Events**: Uses a `Channel` to handle one-time navigation events, ensuring that events are handled once and state management is decoupled from event handling.
- Ensure your UI state classes implement the UiState interface provided in this package for type safety.

## Navigation and Deep Links

- Add nav config and deep link example with a guide for it.
  [Issue](https://github.com/rootstrap/android-base-compose/issues/9)

**Note**: To add deep links:
Add the `.well-known/assetlinks.json` file to your server to register the app
Check the documentation: [Docs](https://developer.android.com/training/app-links/verify-android-applinks)

To test deep link locally call with adb:
```
 adb shell am start -a android.intent.action.VIEW \
                -c android.intent.category.BROWSABLE \
                -d "http://awesomewebpage.com"
```

Note: Once you upload the app to Google Play Store you will find the .well-known/assetlinks.json file config in the signature menu

To verify the assetlinks.json was registered with the app use:
```
- adb shell pm verify-app-links --re-verify PACKAGE_NAME'
- adb shell pm get-app-links PACKAGE_NAME
```
When verified, you should see the following output:
```
            com.rs.androidcomposebase
                ID: 01234567-89ab-cdef-0123-456789abcdef
                Signatures: [***]
                Domain verification state:
                    awesomewebpage.com: verified
```

Navigation:
Example of nav with arguments:
```
        composable(
             route = NavRoutes.BusinessDetails.route,
             arguments = listOf(navArgument("id") { type = NavType.StringType })
         ) { backStackEntry ->
             backStackEntry.arguments?.getString("id")?.run {
              BusinessDetailPage(this)
 			   } ?: run {
 				 ErrorPage(message = "404")
 			   }
         }
```

Example of calling a route with arguments:
 ```
        AppData.mainNavController.navigate(
             NavRoutes.BusinessDetailPage.withArg(
                 business.id
             )
         )
```

To set deep links:
```
       composable(
            route = NavRoutes.BusinessDetailPage.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            // URI is your page deeplink in this case "awesomewebpage.com",
 	    // get your uri from your desired configuration: const var, env, gradle, etc..
            deepLinks = listOf(navDeepLink { uriPattern = "https://awesomewebpage.com/business/{id}"}),
         ) { backStackEntry ->
              backStackEntry.arguments?.getString("id")?.run {
                  BusinessDetailPage(this)
              } ?: run {
                  ErrorPage(message = "404")
              }
         }
```
