import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

import { AuthService, ModalBasicComponent, NoWhiteSpaceDirective, ValidateFieldsService } from '../../shared';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
  providers: [NoWhiteSpaceDirective]
})
export class SignInComponent implements OnInit {
  form: FormGroup;
  loading = false;
  hide = true;

  constructor(
    public fb: FormBuilder,
    public validate: ValidateFieldsService,
    private authService: AuthService,
    public dialog: MatDialog,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: [
        '',
        Validators.compose([
          Validators.maxLength(20),
          Validators.required,
        ]),
      ],
      password: [
        '',
        Validators.compose([
          Validators.maxLength(30),
          Validators.required,
        ]),
      ],
    });
  }

  formControl(controlName: string): AbstractControl {
    return this.form.controls[controlName];
  }

  getErrorMessage(formControlName: string) {
    let message = '';
    if (this.validate.hasErrorValidate(this.formControl(formControlName), 'required')) {
      message = 'Campo obrigatório';
    } else if (this.validate.hasErrorValidate(this.formControl(formControlName), 'minlength')) {
      message = `Quantidade mínima de ${this.validate.lengthValidar(this.formControl(formControlName), 'minlength')} caracteres`;
    } else if (this.validate.hasErrorValidate(this.formControl(formControlName), 'maxlength')) {
      message = `Limite de ${this.validate.lengthValidar(this.formControl(formControlName), 'maxlength')} caracteres`;
    } else if (this.validate.hasErrorValidate(this.formControl(formControlName), 'pattern')) {
      message = 'Dado inválido';
    }
    return message;
  }

  onSubmit() {
    // this.authService.login(this.form.getRawValue()).subscribe(
    //   (data) => {
      //   },
      //   (err) => {
        //     // this.loadService.emitLoadEvent(false);
        //     if (err.status === 401) {
          //       this.form.controls['email'].setErrors({ invalid: true });
          //       this.form.controls['password'].setErrors({ invalid: true });
          //     } else {
            //       // this.loadService.emitMessageEvent('error.server');
            //     }
            //   }
            // );
    this.router.navigate(['/']);
  }

  consegui() {
    const dialogRef = this.dialog.open(ModalBasicComponent, {
			width: '430px',
			autoFocus: false,
			data: {
				title: 'Confirmação',
				body: 'Tem certeza que deseja excluir este item?',
				hasCancel: true,
			},
		});

		dialogRef.afterClosed().subscribe((deleteTechnician) => {
			// console.log(deleteTechnician);
			if (deleteTechnician) {
				// this.creditosService.deletarGanho(element.id).subscribe(
				// 	(response) => {
				// 		// TODO: Notificar
				// 		this.loadService.emitMessageEvent('Registro deletado com sucesso!');
				// 		this.retrieveList();
				// 	},
				// 	ex => {
				// 	});
			}
		});
  }
  
}
