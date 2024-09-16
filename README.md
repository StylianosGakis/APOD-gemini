# APOD + Gemini sample ✨

## :scroll: Description

An application showing random pictures
from [Astronomy Picture of the Day](https://apod.nasa.gov/apod/astropix.html)
with [Gemini](https://blog.google/technology/ai/google-gemini-ai/)

Browse a list of randomly picked photos from the APOD list. Select your favorite one and ask Gemini
to give you some more context and informatin about what you are looking at.

## :bulb: Motivation and Context

Built as a sample app to get started with Android Development. It tries to cover many commonly met
problems like setting up dependency injection. Structuring your app's screens and navigating between
them. Setting up networking with backend services to fetch information. Storing information locally
in a persistent manner using a database. Rendering images from the web. Structuring your screen's UI
state and logic to handle user input. Having error, loading and data states which are observed by
the UI layer. Adding ViewModels and scoping the work that they do to only while there is an observer
on that particular destination. And finally even spicing things up with some more advanced UI
animations with the addition of shared element transitions.

## 🚧 Built with

| What              | How                                                                                 |
|-------------------|-------------------------------------------------------------------------------------|
| ⭐️ User Interface | [Jetpack Compose](https://github.com/jetbrains/compose-jb)                          |
| 💉 DI             | [Koin](https://insert-koin.io/docs/quickstart/android/)                             |
| 🚦 Navigation     | [Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation)      |
| 🌊 Async          | [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)                  |
| 🌐 Networking     | [Retrofit](https://square.github.io/retrofit/)                                      |
| 🔢 Serialization  | [KotlinX Serialization](https://kotlinlang.org/docs/serialization.html#what-s-next) |
| 💾 Storage        | [Room](https://developer.android.com/training/data-storage/room)                    |
| 🏞 Image Loading  | [Coil](https://coil-kt.github.io/coil/)                                             |

## :camera_flash: Screenshots

| Light                                                   | Dark                                                   |
|---------------------------------------------------------|--------------------------------------------------------|
| <img src="media/APOD_collection_light.png" width="260"> | <img src="media/APOD_collection_dark.png" width="260"> |
| <img src="media/APOD_detail_light.png" width="260">     | <img src="media/APOD_detail_dark.png" width="260">     |
| <img src="media/APOD_picture.png" width="260">          |                                                        |

## Build locally

To run the app yourself, you need to have API keys for the service providing the APOD images and for
Gemini.  
Grab the APOD key [here](https://api.nasa.gov/).  
Grab your Gemini key by reading the
documentation [here](https://developer.android.com/ai/google-ai-client-sdk) and
following the
instructions which make you end up [here](https://aistudio.google.com/app/apikey) to generate the
key itself.

Once you got those tokens, go
to [untranslatablestrings_sample.xml](app/src/main/res/values/untranslatablestrings_sample.xml),
replace the empty strings with your API keys.  
Then, optimally, rename the file to just "untranslatablestrings.xml" since that one is part of
our [.gitignore](.gitignore), and ensure that you are not committing it.  
You do *not* want to accidentally commit those tokens and publish it somewhere online, as people
will find and abuse them. Especially the Gemini key.

## More interesting/relevant links

[This article](https://medium.com/androiddevelopers/type-safe-navigation-for-compose-105325a97657)
covers the support of androidx.navigation for type-safe navigation. The same
exists [in video form too](https://www.youtube.com/watch?v=8m1W4PyYMYQ)

[This tool](https://material-foundation.github.io/material-theme-builder/) was used to generate the material3 colors for
the app

[Documentation about compose @Previews](https://developer.android.com/develop/ui/compose/tooling/previews)

[Documentation about window insets and going edge to edge](https://developer.android.com/develop/ui/compose/layouts/insets)