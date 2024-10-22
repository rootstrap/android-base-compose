package com.rootstrap.yourAppName.ui.navigation

/**
 * Examples of navigation routes
 *  Example of nav with arguments:
 *         composable(
 *             route = NavRoutes.BusinessDetails.route,
 *             arguments = listOf(navArgument("id") { type = NavType.StringType })
 *         ) { backStackEntry ->
 *             backStackEntry.arguments?.getString("id")?.run {
 *               BusinessDetailPage(this)
 * 			   } ?: run {
 * 				 ErrorPage(message = "404")
 * 			   }
 *         }
 *
 * Example of calling a route with arguments:
 *         AppData.mainNavController.navigate(
 *             NavRoutes.BusinessDetailPage.withArg(
 *                 business.id
 *             )
 *         )
 *
 * To setup deep links:
 *         composable(
 *             route = NavRoutes.BusinessDetailPage.route,
 *             arguments = listOf(navArgument("id") { type = NavType.StringType }),
 * 				// URI is your page deeplink in this case "awesomewebpage.com",
 * 				// get your uri from your desired configuration: const var, env, gradle, etc..
 *               deepLinks = listOf(navDeepLink { uriPattern = "https://awesomewebpage.com/business/{id}"}),
 *         ) { backStackEntry ->
 *              backStackEntry.arguments?.getString("id")?.run {
 *                  BusinessDetailPage(this)
 * 			    } ?: run {
 *                    ErrorPage(message = "404")
 *               }
 *         }
 * **/

sealed class MainRoutes(val route: String) {
    /*
    Example of nav with arg
    object BusinessDetailPage : NavRoutes("business_details/{id}") {
        fun withArg(id: String) = "business_details/$id"
    }*/
    object MainPage : MainRoutes("main")
    object ErrorPage : MainRoutes("error/{message}") {
        fun withArg(errorMessage: String) = "error/$errorMessage"
    }
}
