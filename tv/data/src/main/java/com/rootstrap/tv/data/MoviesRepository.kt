package com.rootstrap.tv.data

import com.rootstrap.domain.HomeRow
import com.rootstrap.domain.Movie
import java.util.UUID

class MoviesRepository {

    companion object {
        const val TOP_TEN = "Top 10 Chapters"
        const val NOW_PLAYING = "Now playing"
        const val DRAMA = "DRAMA"
    }

    suspend fun getMovie(id: String): Movie {
        return Movie(
            id = UUID.randomUUID().toString(),
            posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877187808/BOLD_9290_EPISODE_IMAGE_2716221_1920x1080.jpg",
            name = "S37 E181",
            description = "Steffy’s worries grew stronger when she walked into the Forrester CEO's office to find Hope touching Finn during an affectionate moment"
        )
    }

    suspend fun getFeaturedChapters(): List<Movie> {
        return listOf(
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/05/28/2341462595953/BOLD_9289_EPISODE_IMAGE_2709812_1920x1080.jpg",
                name = "S37 E180",
                description = "Hope finds herself in an unexpected situation"
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877187808/BOLD_9290_EPISODE_IMAGE_2716221_1920x1080.jpg",
                name = "S37 E181",
                description = "Steffy’s worries grew stronger when she walked into the Forrester CEO's office to find Hope touching Finn during an affectionate moment"
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877187809/BOLD_9291_EPISODE_IMAGE_2716224_1920x1080.jpg",
                name = "S37 E182",
                description = "Hope joined Brooke in the design office and expressed her admiration of Finn, leaving Brooke concerned and suspecting Hope has feelings for Steffy’s husband."
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877699691/BOLD_9292_EPISODE_IMAGE_2716172_1920x1080.jpg",
                name = "S37 E183",
                description = "Brooke eventually hears the entirety of Hope’s fantasies and thoughts, making her highly concerned and worried that she is going after Finn"
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343880771607/BOLD_9293R_EPISODE_IMAGE_2716281_1920x1080.jpg",
                name = "S37 E184",
                description = "Brooke felt as though Steffy was pushing her out, and Steffy said she was more concerned about Hope spending time with her husband than the line"
            ),
        )
    }

    suspend fun getFavoriteChapter(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        list.addAll(listOf(
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/05/14/2337464899919/BOLD_9275_EPISODE_IMAGE_2694527_1920x1080.jpg",
                name = "S37 E166",
                description = "Steffy vents to Liam about Hope’s possible influence over Finn"
            )
        ))
        return list
    }

    suspend fun nowPlayingChapter(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        list.addAll(listOf(
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/05/07/2335339075820/BOLD_9273_EPISODE_IMAGE_2689635_1920x1080.jpg",
                name = "S37 E164",
                description = "Liam argues that Finn is not supporting Steffy as he should. R.J. and Luna decide their future."
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/05/07/2335339587794/BOLD_9274_EPISODE_IMAGE_2690860_1920x1080.jpg",
                name = "S37 E165",
                description = "Hope expresses frustration that Steffy encouraged Thomas and Douglas to leave Los Angeles"
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/05/28/2341462595953/BOLD_9289_EPISODE_IMAGE_2709812_1920x1080.jpg",
                name = "S37 E180",
                description = "Hope finds herself in an unexpected situation"
            )
        ))
        return list
    }

    suspend fun getTop10Chapters(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
       list.addAll(listOf(
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/05/28/2341462595953/BOLD_9289_EPISODE_IMAGE_2709812_1920x1080.jpg",
               name = "S37 E180",
               description = "Hope finds herself in an unexpected situation"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877187808/BOLD_9290_EPISODE_IMAGE_2716221_1920x1080.jpg",
               name = "S37 E181",
               description = "Steffy’s worries grew stronger when she walked into the Forrester CEO's office to find Hope touching Finn during an affectionate moment"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877187809/BOLD_9291_EPISODE_IMAGE_2716224_1920x1080.jpg",
               name = "S37 E182",
               description = "Hope joined Brooke in the design office and expressed her admiration of Finn, leaving Brooke concerned and suspecting Hope has feelings for Steffy’s husband."
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343877699691/BOLD_9292_EPISODE_IMAGE_2716172_1920x1080.jpg",
               name = "S37 E183",
               description = "Brooke eventually hears the entirety of Hope’s fantasies and thoughts, making her highly concerned and worried that she is going after Finn"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343880771607/BOLD_9293R_EPISODE_IMAGE_2716281_1920x1080.jpg",
               name = "S37 E184",
               description = "Brooke felt as though Steffy was pushing her out, and Steffy said she was more concerned about Hope spending time with her husband than the line"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/04/2343880771608/BOLD_9294_EPISODE_IMAGE_2716337_1920x1080.jpg",
               name = "S37 E185",
               description = "Steffy got agitated as she told Brooke that Hope could be putting bad ideas into Finn’s head regarding Sheila"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346154563973/BOLD_9295_EPISODE_IMAGE_2719260_1920x1080.jpg",
               name = "S37 E186",
               description = "Sheila learns of a shocking secret"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346156611929/BOLD_9296_EPISODE_IMAGE_2719535_1920x1080.jpg",
               name = "S37 E187",
               description = "Steffy is blindsided by Ridge’s announcement"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346157123648/BOLD_9297_EPISODE_IMAGE_2719404_1920x1080.jpg",
               name = "S37 E188",
               description = "Katie confesses her mixed emotions about his new family to Bill"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346158147662/BOLD_9298_EPISODE_IMAGE_2719406_1920x1080.jpg",
               name = "S37 E189",
               description = "The situation between Katie and Poppy intensifies"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346158659584/BOLD_9299_EPISODE_IMAGE_2722496_1920x1080.jpg",
               name = "S37 E190",
               description = "Sheila lets it be known that she is not to be messed with"
           ),
           Movie(
               id = UUID.randomUUID().toString(),
               posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/18/2348049987883/BOLD_9300_EPISODE_IMAGE_2723927_1920x1080.jpg",
               name = "S37 E191",
               description = "Hope is blindsided by Thomas’s good news"
           )

       ))
        return list
    }

    suspend fun getHomeRows(): List<HomeRow> {
        val topTenRow = HomeRow(
            name = TOP_TEN,
            rowItems = getTop10Chapters()
        )
        val nowPlayingChapter = HomeRow(
            name = NOW_PLAYING,
            rowItems = nowPlayingChapter()
        )
        val favoriteChapter = HomeRow(
            name = DRAMA,
            rowItems = getFavoriteChapter()
        )
        return listOf(
            topTenRow,
            nowPlayingChapter,
            favoriteChapter
        )
    }

    suspend fun myFavourites(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        list.addAll(listOf(
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346156611929/BOLD_9296_EPISODE_IMAGE_2719535_1920x1080.jpg",
                name = "S37 E187",
                description = "Steffy is blindsided by Ridge’s announcement"
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346157123648/BOLD_9297_EPISODE_IMAGE_2719404_1920x1080.jpg",
                name = "S37 E188",
                description = "Katie confesses her mixed emotions about his new family to Bill"
            ),
            Movie(
                id = UUID.randomUUID().toString(),
                posterUri = "https://thumbnails.cbsig.net/_x/w400/CBS_Production_Entertainment_VMS/2024/06/11/2346158147662/BOLD_9298_EPISODE_IMAGE_2719406_1920x1080.jpg",
                name = "S37 E189",
                description = "The situation between Katie and Poppy intensifies"
            ),
        ))
        return list
    }
}
