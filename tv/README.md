# TV Module

This module contains the TV/Android TV version of the app built with Jetpack Compose.

## Overview

The main functionalities include:

- Browsing and searching for videos
- A video detail page with descriptions and related information
- Video playback functionality

## Architecture

This module shares the same architecture and dependencies as the main app module, including:

- MVVM (Model-View-ViewModel) pattern
- Use of ViewModels for managing UI-related data
- Retrofit for handling network requests
- Coil for image loading and rendering

## Android TV Specifics

Unlike the traditional approach, this app uses Jetpack Compose for the UI instead of the Leanback library,
catering specifically to Android TV's requirements.

Key Android TV specifics:

- **Navigation**: Utilizes a NavigationDrawer for navigating between different sections of the app:
- Home
- Search
- Favourites
- Movies
- Explore nested screens in `NestedScreens.kt` for more detail.

- **Video Playback**: Features a custom video playback UI using the Media3 library.
- **Home Screen Carousel**: Implements a custom carousel for the home screen.
