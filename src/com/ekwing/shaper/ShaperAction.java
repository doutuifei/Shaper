package com.ekwing.shaper;

import com.ekwing.shaper.bean.FileBean;
import com.ekwing.shaper.bean.ShapeBean;
import com.ekwing.shaper.listener.OnOkClickListener;
import com.ekwing.shaper.parse.ShapeDomParse;
import com.ekwing.shaper.ui.PrefixDialog;
import com.ekwing.shaper.utils.Utils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.refactoring.openapi.impl.RenameRefactoringImpl;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShaperAction extends AnAction {

    private List<FileBean> fileList;

    private String prefix = Constants.PREFIX;

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (fileList == null) {
            fileList = new ArrayList<>();
        } else {
            fileList.clear();
        }
        VirtualFile[] virtualFiles = e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
        for (VirtualFile file : virtualFiles) {
            if (file.isDirectory()) {
                VirtualFile[] children = file.getChildren();
                for (VirtualFile child : children) {
                    addFile(child);
                }
            } else {
                addFile(file);
            }
        }
        if (fileList.isEmpty()) {
            Messages.showMessageDialog("没有有效的文件", "Tips", Messages.getInformationIcon());
            return;
        }
        getPrefix();
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Application application = ApplicationManager.getApplication();
        application.saveAll();
        boolean result = application.runReadAction(new Computable<Boolean>() {
            @Override
            public Boolean compute() {
                Iterator<FileBean> iterator = fileList.iterator();
                while (iterator.hasNext()) {
                    FileBean fileBean = iterator.next();
                    VirtualFile virtualFile = fileBean.getVirtualFile();
                    String newName = createNewName(virtualFile);
                    if (newName == null) {
                        iterator.remove();
                        continue;
                    }
                    fileBean.setNewName(newName);
                    PsiFile psiFile = PsiManager.getInstance(project).findFile(virtualFile);
                    fileBean.setPsiFile(psiFile);
                }
                return true;
            }
        });
        if (result) {
            for (FileBean fileBean : fileList) {
                if (fileBean.needRename()) {
                    RenameRefactoringImpl refactoring = new RenameRefactoringImpl(project, fileBean.getPsiFile(), fileBean.getNewName(), true, true);
                    refactoring.run();
                }
            }
        }
    }

    private void getPrefix() {
        PrefixDialog dialog = new PrefixDialog();
        dialog.setTitle("设置文件名前缀");
        dialog.setSize(Utils.getDialogWidth(), Utils.getDialogHeight());
        Point point = Utils.getDialogCenterLocation();
        dialog.setLocation(point.x, point.y);
        dialog.setResizable(false);
        dialog.setDefaultPrefix(prefix);
        dialog.setOnClickListener(new OnOkClickListener() {
            @Override
            public void onOkClick(String text) {
                prefix = text;
                if (Utils.isEmpty(text)) {
                    prefix = Constants.PREFIX;
                    dialog.setDefaultPrefix(prefix);
                }
            }
        });
        dialog.setVisible(true);
    }

    private void addFile(VirtualFile file) {
        String fileType = file.getFileType().getName().toLowerCase();
        if (!Constants.XML.equals(fileType)) {
            return;
        }
        fileList.add(new FileBean(file));
    }

    /**
     * 生成name
     *
     * @param virtualFile
     * @return
     */
    private String createNewName(VirtualFile virtualFile) {
        try {
            InputStream inputStream = virtualFile.getInputStream();
            ShapeBean shapeBean = new ShapeDomParse().parse(inputStream);
            if (shapeBean != null) {
                String shapeName = shapeBean.getSimpleName().toLowerCase();
                StringBuilder builder = new StringBuilder();
                if (prefix.endsWith(Constants.SEPARATOR)) {
                    builder.append(prefix);
                } else {
                    builder.append(prefix);
                    builder.append(Constants.SEPARATOR);
                }
                builder.append(shapeName);
                builder.append(Constants.POINT);
                builder.append(Constants.XML);
                return builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}