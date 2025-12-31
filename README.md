🎬 Movie App (TVMaze API)

An Android application that allows users to browse and explore popular TV shows using a clean and modern user interface. The app fetches real-time show data from the TVMaze API, including titles, ratings, summaries, and posters.

📱 Features

🔍 Browse popular TV shows

🖼️ Display show posters, names, and ratings

📄 Read detailed show summaries

❤️ Mark favorite shows

🎨 Modern UI using RecyclerView and CardView

🌐 REST API integration using Retrofit2

🛠️ Built With

Language: Java

API: TVMaze API

Libraries & Tools:

Retrofit2 – API communication

Glide – Image loading

RecyclerView – Displaying show lists

CardView – UI cards

ConstraintLayout – Responsive UI design

📷 Screenshots
Login Screen
<img src="doc/login_screen.png" width="300"/>
Splash Screen
<img src="doc/splash_screen.png" width="300"/>
Shows List
<img src="doc/movies_list_screen_1.png" width="300"/>
Show Detail
<img src="doc/movie_detail_screen_1.png" width="300"/>
Favorites
<img src="doc/favorite_movies.png" width="300"/>
📊 UML & Architecture
UML Sequence Diagram
<img src="doc/Movie-app_UML_Sequence_diagram.png" width="800"/>
High-Level Component Diagram
<img src="doc/Movie-app_High_Level_Architecture_Diagram.png" width="800"/>
🚀 Getting Started
Prerequisites

Android Studio (Electric Eel or newer)

Internet connection

No API key required (TVMaze API is public 🎉)

🔧 Installation
git clone https://github.com/renu-123-pixel/Movie-app.git


Open the project in Android Studio

Let Gradle sync

Run the app on an emulator or physical device

🌐 API Reference (TVMaze)

Example endpoints used in the app:

https://api.tvmaze.com/shows
https://api.tvmaze.com/search/shows?q=batman

📁 Project Structure
MovieApp/
└── app/
    └── src/
        └── main/
            ├── java/
            │   └── com/yourpackage/movieapp/
            │       ├── Activity/
            │       ├── Adapter/
            │       ├── Domain/
            │       └── Helper/
            │
            ├── res/
            │   ├── layout/
            │   ├── drawable/
            │   ├── values/
            │   └── xml/
            │
            └── AndroidManifest.xml

📌 Future Enhancements

🔍 Search functionality

🎭 Genre-based filtering

📄 Pagination for large lists

💾 Offline caching

🌙 Dark mode support

🙋‍♀️ Author

Renu
GitHub: renu-123-pixel
