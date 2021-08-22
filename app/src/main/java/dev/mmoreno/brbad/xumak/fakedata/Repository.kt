package dev.mmoreno.brbad.xumak.fakedata

data class BreakingBadCharacter(
  val id: Int,
  val name: String,
  val nickname: String,
  val image: String
)

val characters = listOf(
  BreakingBadCharacter(
    1,
    "Walter White",
    "Heisenberg",
    "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg"
  ),
  BreakingBadCharacter(
    2,
    "Jesse Pinkman",
    "Cap n' Cook",
    "https://vignette.wikia.nocookie.net/breakingbad/images/9/95/JesseS5.jpg/revision/latest?cb=20120620012441"
  ),
  BreakingBadCharacter(
    3,
    "Skyler White",
    "Sky",
    "https://s-i.huffpost.com/gen/1317262/images/o-ANNA-GUNN-facebook.jpg"
  ),
  BreakingBadCharacter(
    4,
    "Walter White Jr.",
    "Flynn",
    "https://media1.popsugar-assets.com/files/thumbor/WeLUSvbAMS_GL4iELYAUzu7Bpv0/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2018/01/12/910/n/1922283/fb758e62b5daf3c9_TCDBRBA_EC011/i/RJ-Mitte-Walter-White-Jr.jpg"
  ),
  BreakingBadCharacter(
    5,
    "Henry Schrader",
    "Hank",
    "https://vignette.wikia.nocookie.net/breakingbad/images/b/b7/HankS5.jpg/revision/latest/scale-to-width-down/700?cb=20120620014136"
  ),
  BreakingBadCharacter(
    6,
    "Marie Schrader",
    "Marie",
    "https://vignette.wikia.nocookie.net/breakingbad/images/1/10/Season_2_-_Marie.jpg/revision/latest?cb=20120617211645"
  ),
  BreakingBadCharacter(
    7,
    "Mike Ehrmantraut",
    "Mike",
    "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_mike-ehrmantraut-lg.jpg"
  ),
  BreakingBadCharacter(
    8,
    "Saul Goodman",
    "Jimmy McGill",
    "https://vignette.wikia.nocookie.net/breakingbad/images/1/16/Saul_Goodman.jpg/revision/latest?cb=20120704065846"
  ),
  BreakingBadCharacter(
    9,
    "Gustavo Fring",
    "Gus",
    "https://vignette.wikia.nocookie.net/breakingbad/images/1/1f/BCS_S4_Gustavo_Fring.jpg/revision/latest?cb=20180824195925"
  ),
  BreakingBadCharacter(
    10,
    "Hector Salamanca",
    "Don Hector",
    "https://vignette.wikia.nocookie.net/breakingbad/images/b/b4/Hector_BCS.jpg/revision/latest?cb=20170810091606"
  ),
  BreakingBadCharacter(
    11,
    "Domingo Molina",
    "Krazy-8",
    "https://vignette.wikia.nocookie.net/breakingbad/images/e/e7/Krazy8.png/revision/latest?cb=20130208041554"
  ),
  BreakingBadCharacter(
    12,
    "Tuco Salamanca",
    "Tuco",
    "https://vignette.wikia.nocookie.net/breakingbad/images/a/a7/Tuco_BCS.jpg/revision/latest?cb=20170810082445"
  )
)