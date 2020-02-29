package com.ekwing.shaper.bean;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;

public class FileBean {

    private VirtualFile virtualFile;

    private PsiFile psiFile;

    private String oldName;

    private String newName;

    public FileBean() {
    }

    public FileBean(VirtualFile virtualFile) {
        this.virtualFile = virtualFile;
        oldName = virtualFile.getName();
    }

    public VirtualFile getVirtualFile() {
        return virtualFile;
    }

    public void setVirtualFile(VirtualFile virtualFile) {
        this.virtualFile = virtualFile;
    }

    public PsiFile getPsiFile() {
        return psiFile;
    }

    public void setPsiFile(PsiFile psiFile) {
        this.psiFile = psiFile;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public boolean needRename() {
        return !oldName.equals(newName);
    }

}