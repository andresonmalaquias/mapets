import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-basic',
  templateUrl: './modal-basic.component.html',
  styleUrls: ['./modal-basic.component.scss']
})
export class ModalBasicComponent implements OnInit {
  body = '';
  bodyParam = '';
  title = '';
  buttonColor = '';
  hasCancel = false;

  public visible = false;

  constructor(
    public dialogRef: MatDialogRef<ModalBasicComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
    this.title = data.title;
    this.body = data.body;
    this.bodyParam = data.bodyParam;
    this.buttonColor = data.buttonColor;
    this.hasCancel = data.hasCancel;
   }

  ngOnInit(): void {
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }

  onConfirm(): void {
    this.dialogRef.close(true);
  }
}
