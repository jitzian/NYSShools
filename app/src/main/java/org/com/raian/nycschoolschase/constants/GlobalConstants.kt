package org.com.raian.nycschoolschase.constants

class GlobalConstants {
    companion object {

        //BaseURL Endpoint
        const val baseURL = "https://data.cityofnewyork.us/resource/"

        val arrCities = arrayOf("Manhattan", "Bronx", "Brooklyn")

        val arrImagesCities = arrayOf(
            "https://static1.mansionglobal.com/production/media/article-images/5e8a465afae84d510ec9c38a43c2b935/large_GettyImages-640006562-1.jpg",
            "https://www.cityandstateny.com/sites/default/files/all/styles/special_list_main_article_image/public/bx-opener-web.png",
            "https://photolemur.com/uploads/blog/download4.jpeg",
            "https://external-preview.redd.it/PC5AmVnDVMX-DiiWvmD1Wau7YEVqGo0B3k1JqDkHipg.jpg?auto=webp&s=f39fe8ce4f1bede0f2f011bb18f48801cca1d21e"
        )

        const val textSnackBar = "Going to School position"

        val latAndLong = arrayOf("latitude", "longitude")

        //Constants for saving RoomDB
        const val dataBaseName = "nyc_schools_database.db"
        const val dataBaseVersion = 1

        //Weather
        /**
         * Current Weather
        HTTP: http://api.apixu.com/v1/current.json?key=b3d8bdefcb794ec6ac8144355191805&q=Paris
        HTTPS: https://api.apixu.com/v1/current.json?key=b3d8bdefcb794ec6ac8144355191805&q=Paris

        Forecast Weather
        HTTP: http://api.apixu.com/v1/forecast.json?key=b3d8bdefcb794ec6ac8144355191805&q=Paris
        HTTPS: https://api.apixu.com/v1/forecast.json?key=b3d8bdefcb794ec6ac8144355191805&q=Paris
         *
         * */

        const val weatherBaseUrl = "http://api.apixu.com/v1/"
        const val weatherKey = "b3d8bdefcb794ec6ac8144355191805"
    }
}