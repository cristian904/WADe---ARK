import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MuseumComponent } from './museum/museum.component';
import { MuseumDetailComponent } from './museum/museum-detail/museum-detail.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { ArtComponent } from './art/art.component';
import { ArtDetailComponent } from './art/art-detail/art-detail.component';
import { ArtistComponent } from './artist/artist.component';
import { ArtistDetailComponent } from './artist/artist-detail/artist-detail.component';
import { RouterModule } from '@angular/router';
import { ArtListComponent } from './art/art-list/art-list.component';
import { ArtItemComponent } from './art/art-list/art-item/art-item.component';
import { ArtService } from './services/art.service';


const appRoutes: Routes = [
  { path: 'museum', component: MuseumComponent },
  { path: 'art', component: ArtComponent}, 
  { path: 'art/:id', component: ArtDetailComponent},
  { path: 'artist', component: ArtistComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StatisticsComponent,
    //Museums
    MuseumComponent,
    MuseumDetailComponent,
    //Works of art
    ArtComponent,
    ArtDetailComponent,
    ArtListComponent,
    ArtItemComponent,
    //Artists
    ArtistComponent,
    ArtistDetailComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ArtService],
  bootstrap: [AppComponent]
})
export class AppModule { }
