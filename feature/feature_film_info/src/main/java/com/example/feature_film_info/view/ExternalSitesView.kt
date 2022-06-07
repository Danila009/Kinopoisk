package com.example.feature_film_info.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.IMDb.externalSite.ExternalSite
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.feature_film_info.enums.SiteType

@Composable
internal fun ExternalSitesView(
    externalSites: Response<ExternalSite>,
){
    if(externalSites is Response.Success){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "External Sites",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = secondaryBackground
            )

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Все ->",
                    color = secondaryBackground
                )
            }
        }

        LazyRow(content = {
            item {
                externalSites.data?.imDb?.let {
                    SiteInfoView(siteType = SiteType.IMDb, it.url)
                }

                externalSites.data?.theMovieDb?.let {
                    SiteInfoView(siteType = SiteType.THE_MOVIE_DB, it.url)
                }

                externalSites.data?.rottenTomatoes?.let {
                    SiteInfoView(siteType = SiteType.ROTTEN_TOMATOES, it.url)
                }

                externalSites.data?.netflix?.let {
                    SiteInfoView(siteType = SiteType.NETFLIX, it.url)
                }

                externalSites.data?.googlePlay?.let {
                    SiteInfoView(siteType = SiteType.GOOGLE_PLAY, it.url)
                }

                externalSites.data?.filmAffinity?.let {
                    SiteInfoView(siteType = SiteType.FILM_AFFINITY, it.url)
                }

                externalSites.data?.freebase?.let {
                    SiteInfoView(siteType = SiteType.FREEBASE, it.url)
                }

                externalSites.data?.gnd?.let {
                    SiteInfoView(siteType = SiteType.GND, it.url)
                }

                externalSites.data?.viaf?.let {
                    SiteInfoView(siteType = SiteType.VIAF, it.url)
                }

                externalSites.data?.alloCine?.let {
                    SiteInfoView(siteType = SiteType.ALLO_CINE, it.url)
                }

                externalSites.data?.allMovie?.let {
                    SiteInfoView(siteType = SiteType.ALL_MOVIE, it.url)
                }

                externalSites.data?.port?.let {
                    SiteInfoView(siteType = SiteType.PORT, it.url)
                }

                externalSites.data?.dnf?.let {
                    SiteInfoView(siteType = SiteType.DNF, it.url)
                }

                externalSites.data?.movieMeter?.let {
                    SiteInfoView(siteType = SiteType.MOVIE_METER, it.url)
                }

                externalSites.data?.boxOfficeMojo?.let {
                    SiteInfoView(siteType = SiteType.BOX_OFFICE_MOJO, it.url)
                }

                externalSites.data?.csfd?.let {
                    SiteInfoView(siteType = SiteType.CSFD, it.url)
                }

                externalSites.data?.kinenote?.let {
                    SiteInfoView(siteType = SiteType.KINENOTE, it.url)
                }

                externalSites.data?.allcinema?.let {
                    SiteInfoView(siteType = SiteType.ALL_CINEMA, it.url)
                }

                externalSites.data?.kinopoisk?.let {
                    SiteInfoView(siteType = SiteType.KINOPOISK, it.url)
                }

                externalSites.data?.elonet?.let {
                    SiteInfoView(siteType = SiteType.ELONET, it.url)
                }

                externalSites.data?.ldiF?.let {
                    SiteInfoView(siteType = SiteType.LDI_F, it.url)
                }

                externalSites.data?.cineplex?.let {
                    SiteInfoView(siteType = SiteType.CINEPLEX, it.url)
                }

                externalSites.data?.eDb?.let {
                    SiteInfoView(siteType = SiteType.E_DB, it.url)
                }

                externalSites.data?.elonet?.let {
                    SiteInfoView(siteType = SiteType.ELONET, it.url)
                }

                externalSites.data?.elCinema?.let {
                    SiteInfoView(siteType = SiteType.EL_CINEMA, it.url)
                }

                externalSites.data?.scope_dk?.let {
                    SiteInfoView(siteType = SiteType.SCOPE_DK, it.url)
                }

                externalSites.data?.swedishFilmDatabaseFilm?.let {
                    SiteInfoView(siteType = SiteType.SWEDISH_FILM_DATABASE_FILM, it.url)
                }

                externalSites.data?.elonet?.let {
                    SiteInfoView(siteType = SiteType.ELONET, it.url)
                }

                externalSites.data?.elFilm?.let {
                    SiteInfoView(siteType = SiteType.EL_FILM, it.url)
                }

                externalSites.data?.ofDb?.let {
                    SiteInfoView(siteType = SiteType.OF_DB, it.url)
                }

                externalSites.data?.openMediaDatabase?.let {
                    SiteInfoView(siteType = SiteType.OPEN_MEDIA_DATABASE, it.url)
                }

                externalSites.data?.quoraTopic?.let {
                    SiteInfoView(siteType = SiteType.QUORA_TOPIC, it.url)
                }

                externalSites.data?.cinema_de?.let {
                    SiteInfoView(siteType = SiteType.CINEMA_DE, it.url)
                }

                externalSites.data?.deutscheSynchronkartei?.let {
                    SiteInfoView(siteType = SiteType.DEUTSCHE_SYNCHRONKARTEI, it.url)
                }

                externalSites.data?.movieWalker?.let {
                    SiteInfoView(siteType = SiteType.MOVIE_WALKER, it.url)
                }

                externalSites.data?.tvGuide?.let {
                    SiteInfoView(siteType = SiteType.TV_GUIDE, it.url)
                }

                externalSites.data?.filmweb_pl?.let {
                    SiteInfoView(siteType = SiteType.FILM_WEB_PL, it.url)
                }

                externalSites.data?.movieplayer_it?.let {
                    SiteInfoView(siteType = SiteType.TV_COM, it.url)
                }

                externalSites.data?.isan?.let {
                    SiteInfoView(siteType = SiteType.TV_COM, it.url)
                }

                externalSites.data?.isan?.let {
                    SiteInfoView(siteType = SiteType.ISAN, it.url)
                }

                externalSites.data?.eidr?.let {
                    SiteInfoView(siteType = SiteType.EIDR, it.url)
                }

                externalSites.data?.afiCatalogOfFeature?.let {
                    SiteInfoView(siteType = SiteType.AFI_CATALOG_OF_FEATURE, it.url)
                }

                externalSites.data?.theNumbers?.let {
                    SiteInfoView(siteType = SiteType.THE_NUMBERS, it.url)
                }

                externalSites.data?.tcmMovieDatabase?.let {
                    SiteInfoView(siteType = SiteType.TCM_MOVIE_DATABASE, it.url)
                }

                externalSites.data?.cine_gr?.let {
                    SiteInfoView(siteType = SiteType.CINE_GR, it.url)
                }

                externalSites.data?.bfiNationalArchive?.let {
                    SiteInfoView(siteType = SiteType.BFI_NATIONAL_ARCHIVE, it.url)
                }

                externalSites.data?.exploitationVisa?.let {
                    SiteInfoView(siteType = SiteType.EXPLOITATION_VISA, it.url)
                }

                externalSites.data?.sratim?.let {
                    SiteInfoView(siteType = SiteType.SRATIM, it.url)
                }

                externalSites.data?.cineRessources?.let {
                    SiteInfoView(siteType = SiteType.CINE_RESSOURCES, it.url)
                }

                externalSites.data?.cinemathequeQuebecoise?.let {
                    SiteInfoView(siteType = SiteType.CINEMATHEQUE_QUEBECOISE, it.url)
                }

                externalSites.data?.encyclopaediaBritannicaOnline?.let {
                    SiteInfoView(siteType = SiteType.ENCYCLOPAEDIA_BRITANNICA_ONLINE, it.url)
                }

                externalSites.data?.bechdelTestMovieList?.let {
                    SiteInfoView(siteType = SiteType.BECHDEL_TEST_MOVIE_LIST, it.url)
                }

                externalSites.data?.movieplayer_it?.let {
                    SiteInfoView(siteType = SiteType.MOVIEPLAYER_IT, it.url)
                }

                externalSites.data?.mYmovies?.let {
                    SiteInfoView(siteType = SiteType.M_YMOVIES, it.url)
                }

                externalSites.data?.cinematografo?.let {
                    SiteInfoView(siteType = SiteType.CINEMATOGRAFO, it.url)
                }

                externalSites.data?.lumiere?.let {
                    SiteInfoView(siteType = SiteType.LUMIERE, it.url)
                }

                externalSites.data?.bfi?.let {
                    SiteInfoView(siteType = SiteType.BFI, it.url)
                }

                externalSites.data?.prisma?.let {
                    SiteInfoView(siteType = SiteType.PRISMA, it.url)
                }

                externalSites.data?.cineMagia?.let {
                    SiteInfoView(siteType = SiteType.CINE_MAGIA, it.url)
                }

                externalSites.data?.daum?.let {
                    SiteInfoView(siteType = SiteType.DAUM, it.url)
                }

                externalSites.data?.douban?.let {
                    SiteInfoView(siteType = SiteType.DOUBAN, it.url)
                }

                externalSites.data?.ilMondoDeiDoppiatori?.let {
                    SiteInfoView(siteType = SiteType.IL_MONDO_DEI_DOPPIATORI, it.url)
                }

                externalSites.data?.fandango?.let {
                    SiteInfoView(siteType = SiteType.FANDANGO, it.url)
                }

                externalSites.data?.moviepilot_de?.let {
                    SiteInfoView(siteType = SiteType.MOVIEPILOT_DE, it.url)
                }
            }
        })
    }
}

@Composable
private fun SiteInfoView(
    siteType: SiteType,
    url: String,
){
    SiteType.values().forEach { type ->
        if (type == siteType){
            SiteText(title = type.title,url)
        }
    }
}

@Composable
private fun SiteText(
    title: String,
    url: String,
){
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(5.dp),
        shape = AbsoluteRoundedCornerShape(10.dp)
    ) {
        TextButton(
            onClick = {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.W900
            )
        }
    }
}