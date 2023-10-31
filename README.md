# android-base-compose

Rootstrap Android Copose Base
Use sample-app branch to check how to setup your project.

## Error handling 
We use the `ErrorHandler` class that extends `AbstractCoroutineContextElement` and implements `CoroutineExceptionHandler` to handle errors in coroutines.
To listen to those errors we use the `ErrorNotifier` singleton that exposes a flow of errors. Usually you will only need to listen to it in your app's main activity. In this template it is being listened in the `AppActivity` class

# Lint
How to check lint issues: ktlintCheck and ktlintFormat

### Navigation and Deep Links
- Add nav config and deep link example with a guide for it.
  [Issue](https://github.com/rootstrap/android-base-compose/issues/9)

Note: To add deep links:
Add the .well-known/assetlinks.json file to your server to register the app
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