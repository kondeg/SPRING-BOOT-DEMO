import { Component, Input, OnInit } from "@angular/core";
import { IProduct } from "../../models/product";

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html'
})

export class ProductComponent implements OnInit{
    ngOnInit(): void {

    }
    
    @Input() product: IProduct
    details = false
}