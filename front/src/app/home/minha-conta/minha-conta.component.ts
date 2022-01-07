import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-minha-conta',
  templateUrl: './minha-conta.component.html',
  styleUrls: ['./minha-conta.component.scss']
})
export class MinhaContaComponent implements OnInit {
  userData = {
    nome: 'Daniel',
    sobrenome: 'Araujo',
    email: 'daniel.carmo2008@gmail.com',
    cpf: '02031516230',
    celular: '92 995039079'
  }

  imageUrl: any;
  userImage: any;

  deletedImage = false;
  userSaved = false;

  user: any = {};
  
  @ViewChild('userForm') public userForm: NgForm;
  @ViewChild('imagePreview') imagePreview: ElementRef;
  @ViewChild('classImage', {static: false}) classImage: ElementRef;

  constructor() { }

  ngOnInit(): void {
  }

  deleteImage() {
    this.deletedImage = true;
    this.user.imageUrl = null;
    this.userImage = null;
    this.imageUrl = '';
    this.classImage.nativeElement.value = '';
  }

  imageChange(event) {
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      this.userImage = event.target.files[0];

      reader.readAsDataURL(this.userImage);
      reader.onload = () => {
        if ((this.userImage.type === 'image/jpeg' ||
          this.userImage.type === 'image/png') &&
          this.userImage.size < 1048576) {
            this.imageUrl = this.userImage.name;
            this.imagePreview.nativeElement.src = URL.createObjectURL(this.userImage);
        } else {
          // this.loadService.emitMessageEvent('error.invalid-image');
          this.userImage = null;
          this.imageUrl = this.user.imageUrl;
          this.classImage.nativeElement.value = '';
        }
      };
    }
  }

}
