import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-use-case',
  templateUrl: './use-case.component.html',
  styleUrls: ['./use-case.component.css']
})
export class UseCaseComponent implements OnInit {
input = new FormControl('');

  constructor(private appService: AppService, private router: Router, private fb: FormBuilder) { 
    this.form = this.fb.group({
      input: ['', Validators.required]
    });
  }

  form!: FormGroup;
  showMessage = false;
  showErrorMEssage = false;
  newModule = false;

  ngOnInit(): void {
  }

  downloadUseCase() {
    if (this.form.valid) {
      console.log(this.form.value);
      this.downloadWord(this.form.value['input']);
    } else {
      this.showErrorMEssage = true;
    }
    console.log(this.input.value); // { first: '', last: '' }
    // this.downloadWord(this.input.value);
  }

  downloadWord(value: any) {
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

  downloadWordOpenAiAzure(value: any) {
    this.appService.downloadWordFileAzureOpenAi(value).subscribe({
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

  dismissAlert() {
    this.showErrorMEssage = false;
    this.showMessage = false;
  }

}
