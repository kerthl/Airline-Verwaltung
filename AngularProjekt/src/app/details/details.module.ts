import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DetailsComponent } from './details.component';
import { AppModule } from '../app.module';



@NgModule({
    imports: [
        AppModule,
        RouterModule.forChild([
            {
                path: '',
                component: DetailsComponent
            }
        ])
    ],
    declarations: [DetailsComponent],
    exports: [DetailsComponent]
})
export class DetailsComponentModule {
}
