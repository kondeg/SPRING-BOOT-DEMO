import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalService } from '../services/modal.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss']
})
export class CreateProductComponent implements OnInit{
  ngOnInit(): void {

  }

  constructor(private productService: ProductService, private modalService: ModalService) {

  }

   form = new FormGroup( {
     title: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3)
   
     ])
  })
  
  get title() {
    return this.form.controls.title as FormControl
  }


  submit() {
    console.log(this.form.value)
    this.productService.create({ 
        title: this.form.value.title as string,
        price: 13.5,
        description: 'Description',
        image: 'https://i.pravatar.cc',
        category: 'electronic', 
        rating: {
          rate: 1,
          count: 2
        }
      }
    ).subscribe(()=>
      this.modalService.close()
    )
  }

}
