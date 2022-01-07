import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Location } from '@angular/common';

import {
  ValidateFieldsService,
  LoadService,
  NoWhiteSpaceDirective,
} from '../../../shared';
import { MetasService } from '../metas.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
  providers: [NoWhiteSpaceDirective],
})
export class FormComponent implements OnInit {
  form: FormGroup;
  ganhosForm: FormGroup;
  loggedUserId: number;
  routeId: string;
  metaId: number;
  user = {};
  ganhos: any;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private metasService: MetasService,
    public validate: ValidateFieldsService,
    private loadService: LoadService,
  ) {
    this.form = this.fb.group({
      nome: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      sexo: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      idadeMeses: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      tipo: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      personalidade: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      porte: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      indoor: [
        false,
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
    });
  }

  get estimativa() {
    let valorMeta: any = this.form.get('valor').value;
    let economiaMensal = this.form.get('valorEntrada').value;

    if (this.routeId) {
      if (!economiaMensal.includes('.')) {
        economiaMensal = this.validate.formatDecimal(
          this.form.get('valorEntrada').value
        );
      } else if (!valorMeta.includes('.')) {
        valorMeta = this.validate.formatDecimal(this.form.get('valor').value);
      }
    }

    let estimativa = valorMeta / economiaMensal;
    return estimativa;
  }

  get valorTotalParaGastos() {
    let economiaMensal: any;
    let renda: any = this.ganhosForm.get('ganhos').value;
    economiaMensal = this.form.get('valorEntrada').value;

    if (this.routeId) {
      if (!economiaMensal.includes('.')) {
        economiaMensal = this.validate.formatDecimal(
          this.form.get('valorEntrada').value
        );
      }
    } else {
      economiaMensal = this.validate.formatDecimal(
        this.form.get('valorEntrada').value
      );
    }

    return renda - economiaMensal;
  }

  ngOnInit(): void {
    // this.user = this.userService.getUser();
    // this.routeId = this.routerParam.snapshot.paramMap.get('id');
    // this.loggedUserId = this.userService.getUser().id;

    // if (this.routeId) {
      // this.getMetaById(this.routeId);
    // }

    // if (this.user) {
    //   this.getGanhos();
    // }

    // this.form.valueChanges.subscribe((val) => {
    //   console.log(val)
    //   // console.log(this.ganhosForm.get('ganhos').value);
    //   // this.form.controls.valor.setValue(this.validate.formatDecimal(this.form.controls.valor.value));
    //   // this.form.controls.valorEntrada.setValue(this.validate.formatDecimal(this.form.controls.valorEntrada.value));
    // });
  }

  backToList() {
    this.location.back();
  }

  onSubmit() {
    this.loadService.emitLoadEvent(true);

    let payload = this.form.getRawValue();
    // console.log(payload)
    payload.responsavel = 8;
    
    this.metasService.criarPet(payload).subscribe(
      (response) => {
        this.loadService.emitMessageEvent('Pet criada com sucesso!');

        setTimeout(() => this.backToList(), 2500);
      },
      (err) => {
        if (err.error.detail) {
          this.loadService.emitMessageEvent(err.error.detail);
        } else {
          this.loadService.emitMessageEvent('Erro no servidor');
        }
      }
    );

    this.loadService.emitLoadEvent(false);
  }

  formControl(controlName: string): AbstractControl {
    return this.form.controls[controlName];
  }

  getErrorMessage(formControlName: string) {
    let message = '';
    if (
      this.validate.hasErrorValidate(
        this.formControl(formControlName),
        'required'
      )
    ) {
      message = 'Campo obrigatório';
    } else if (
      this.validate.hasErrorValidate(
        this.formControl(formControlName),
        'minlength'
      )
    ) {
      message = `Quantidade mínima de ${this.validate.lengthValidar(
        this.formControl(formControlName),
        'minlength'
      )} caracteres`;
    } else if (
      this.validate.hasErrorValidate(
        this.formControl(formControlName),
        'maxlength'
      )
    ) {
      message = `Limite de ${this.validate.lengthValidar(
        this.formControl(formControlName),
        'maxlength'
      )} caracteres`;
    } else if (
      formControlName === 'cnpj' &&
      this.validate.hasErrorValidate(
        this.formControl(formControlName),
        'cpfOrCnpj'
      )
    ) {
      message = 'Dado inválido';
    } else if (
      this.validate.hasErrorValidate(this.formControl(formControlName), 'email')
    ) {
      message = 'Dado inválido';
    } else if (
      this.validate.hasErrorValidate(
        this.formControl(formControlName),
        'pattern'
      )
    ) {
      message = 'Dado inválido';
    } else if (
      this.validate.hasErrorValidate(
        this.formControl(formControlName),
        'limiteMetas'
      )
    ) {
      message =
        'Valoração máxima deve ser de até 20 salários mínimos (R$22.000,00)';
    }
    return message;
  }
}
