package com.madman.moviesapp.utils

import com.madman.moviesapp.R
import com.madman.moviesapp.data.MoviesEntity
import com.madman.moviesapp.data.TVShowEntity

object DataDummy {
    fun generateDummyMovies(): List<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "Thriller, Drama",
                "January 16, 2019",
                "M. Night Shyamalan",
                "PG-13",
                "4.2",
                R.drawable.poster_glass
            )
        )
        movies.add(
            MoviesEntity(
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure",
                "July 6, 2018",
                "James Wan",
                "PG-13",
                "4.0",
                R.drawable.poster_aquaman
            )
        )
        movies.add(
            MoviesEntity(
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Music, Drama",
                "Oktober 24, 2018",
                "Bryan Singer",
                "PG-13",
                "4.0",
                R.drawable.poster_bohemian
            )
        )
        movies.add(
            MoviesEntity(
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Drama",
                "November 21, 2018",
                "Steven Caple Jr",
                "PG-13",
                "3.7",
                R.drawable.poster_creed
            )
        )
        movies.add(
            MoviesEntity(
                "Fantastic Beasts: The Crimes of Grindelwald",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Fantasy, Drama, Adventure",
                "November 16, 2018",
                "David Yates",
                "PG-13",
                "3.5",
                R.drawable.poster_crimes
            )
        )
        movies.add(
            MoviesEntity(
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Action, Adventure, Science Fiction",
                "April 27, 2018",
                "Anthony Russo",
                "PG-13",
                "4.5",
                R.drawable.poster_infinity_war
            )
        )
        movies.add(
            MoviesEntity(
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Family, Animation",
                "November 21, 2018",
                "Phil Johnston",
                "PG",
                "3.8",
                R.drawable.poster_ralph
            )
        )
        movies.add(
            MoviesEntity(
                "Robin Hood",
                "When soldier Robin happens upon the dying Robert of Loxley, he promises to return the man's sword to his family in Nottingham. There, he assumes Robert's identity; romances his widow, Marion; and draws the ire of the town's sheriff and King John's henchman, Godfrey.",
                "Adventure",
                "Mei 12, 2010",
                "Ridley Scott",
                "PG-13",
                "4.1",
                R.drawable.poster_robin_hood
            )
        )
        movies.add(
            MoviesEntity(
                "Serenity",
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "Thriller, Mystery",
                "January 25, 2019",
                "Steven Knight",
                "PG-13",
                "3.0",
                R.drawable.poster_serenity
            )
        )
        movies.add(
            MoviesEntity(
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "Action, Adventure",
                "December 17, 2021",
                "Jon Watts",
                "PG-13",
                "4.6",
                R.drawable.poster_spiderman
            )
        )
        return movies
    }

    fun generateTVShow(): List<TVShowEntity> {
        val TVShow = ArrayList<TVShowEntity>()

        TVShow.add(
            TVShowEntity(
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Crime, Drama, Mystery",
                "2012",
                "Greg Berlanti",
                "TV-14",
                "3.5",
                R.drawable.poster_arrow
            )
        )
        TVShow.add(
            TVShowEntity(
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Sci-Fi & Fantasy, Drama",
                "2019",
                "Jeremy Carver",
                "TV-MA",
                "3.7",
                R.drawable.poster_doom_patrol
            )
        )
        TVShow.add(
            TVShowEntity(
                "Dragon Ball Absalon",
                "The series begins twelve years after Goku is seen leaving on Shenron not at the end of Dragon Ball GT, and diverges entirely into its own plot from there, on an alternate timeline from the one which shows Goku Jr. fighting Vegeta Jr. at the World Martial Arts Tournament. In this series, Majuub has reached new levels of power, and has honed the techniques taught to him by Goku.",
                "Action, Adventure",
                "1989",
                "Akira Toriyama",
                "TV-PG",
                "4.0",
                R.drawable.poster_dragon_ball
            )
        )
        TVShow.add(
            TVShowEntity(
                "Fairy Tail: Dragon Cry",
                "Natsu Dragneel and his friends travel to the island Kingdom of Stella, where they will reveal dark secrets, fight the new enemies and once again save the world from destruction.",
                "Action, Adventure, Fantasy",
                "2017",
                "Tatsuma Minamikawa",
                "15",
                "3.0",
                R.drawable.poster_fairytail
            )
        )
        TVShow.add(
            TVShowEntity(
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). ",
                "Animation, Comedy",
                "1999",
                "Seth MacFarlane",
                "TV-14",
                "4.0",
                R.drawable.poster_family_guy
            )
        )
        TVShow.add(
            TVShowEntity(
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. ",
                "Drama, Sci-fi & Fantasy",
                "2014",
                "Greg Berlanti",
                "TV-14",
                "3.9",
                R.drawable.poster_flash
            )
        )
        TVShow.add(
            TVShowEntity(
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Action & Adventure, Drama",
                "2011",
                "David Benioff",
                "TV-MA",
                "4.2",
                R.drawable.poster_god
            )
        )
        TVShow.add(
            TVShowEntity(
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Drama, Crime",
                "2014",
                "Bruno Heller",
                "TV-14",
                "3.5",
                R.drawable.poster_gotham
            )
        )
        TVShow.add(
            TVShowEntity(
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Drama",
                "2015",
                "Shonda Rhimes",
                "TV-14",
                "3.2",
                R.drawable.poster_grey_anatomy
            )
        )
        TVShow.add(
            TVShowEntity(
                "Hanna",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Adventure, Drama",
                "2019",
                "David Farr",
                "TV-MA",
                "3.3",
                R.drawable.poster_hanna
            )
        )

        return TVShow
    }
}