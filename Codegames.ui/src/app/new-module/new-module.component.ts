import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-new-module',
  templateUrl: './new-module.component.html',
  styleUrls: ['./new-module.component.css']
})
export class NewModuleComponent implements OnInit {

  showMessage = false;
  showErrorMEssage = false;
  newModule = false;
  drop = false;
  butt = false;
  form!: FormGroup;

  constructor(private fb: FormBuilder, private appService:AppService) { 
    this.form = this.fb.group({
      input: ['', Validators.required],
      dropdown: ['', Validators.required],
      buttons: ['',Validators.required]
    });
  }

  ngOnInit(): void {
  }

  dismissAlert() {
    this.showErrorMEssage = false;
    this.showMessage = false;
  }

  dropCheck(event:any){
    // var x = document.
    console.log(event.target.value);
    if(event.target.value === 'Yes'){
      this.drop = true;
    }else {
      this.drop = false;
    }
  }

  buttCheck(event:any){
    console.log(event.target.value);
    if(event.target.value === 'ButYes'){
      this.butt = true;
    }else {
      this.butt = false;
    }
  }

  downloadUseCase() {
    if (this.form.valid) {
      console.log(this.form.value);
      this.downloadWord(this.form.value['input']);
    } else {
      this.showErrorMEssage = true;
    }
    // this.downloadWord(this.input.value);
  }

  downloadWord(value: any) {
    if(this.butt){
      value=value+' with buttons as '+this.form.value['buttons']
    }else if(this.drop){
      value=value+' with dropdowns as '+this.form.value['dropdown']
    }else if(this.butt && this.drop){
      value=value+' with dropdowns as '+this.form.value['dropdown']+' and buttons as '+this.form.value['buttons']
    }
    this.appService.downloadWordFile(value).subscribe({
      next: (blob: Blob) => {
        console.log(blob);
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'Use Case Document.docx';
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
        this.showMessage = true;
      },
      error: (err: any) => {
        this.showErrorMEssage = false;
      }
    });
  }

}
