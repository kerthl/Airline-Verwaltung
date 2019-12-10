import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppModule } from '../app.module';
import { KarteComponent } from './karte.component';



@NgModule({
    imports: [
        AppModule,
        RouterModule.forChild([
            {
                path: '',
                component: KarteComponent
            }
        ])
    ],
    declarations: [KarteComponent],
    exports: [KarteComponent]
})
export class KarteComponentModule {
}
