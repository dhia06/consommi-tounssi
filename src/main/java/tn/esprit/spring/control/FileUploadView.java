package tn.esprit.spring.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;



@Named
@RequestScoped
public class FileUploadView {
     
    private UploadedFile file;
   // private UploadedFiles files;
    
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
//    public UploadedFiles getFiles() {
//        return files;
//    }
 
//    public void setFiles(UploadedFiles files) {
//        this.files = files;
//    }
// 
    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            System.out.println("hhhahahahhahahahahahahahahaha wi wi ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
//    public void uploadMultiple() {
//        if (files != null) {
//            for (UploadedFile f : files.getFiles()) {
//                FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            }
//        }
//    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
