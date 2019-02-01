/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sleuthkit.autopsy.report;

import org.apache.commons.io.FileUtils;
import org.openide.util.lookup.ServiceProvider;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.casemodule.NoCurrentCaseException;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.coreutils.MessageNotifyUtil;
import org.sleuthkit.datamodel.SleuthkitCase;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.Content;
import org.sleuthkit.datamodel.ContentTag;
import org.sleuthkit.datamodel.FileSystem;
import org.sleuthkit.datamodel.Image;
import org.sleuthkit.datamodel.LocalFilesDataSource;
import org.sleuthkit.datamodel.SpecialDirectory;
import org.sleuthkit.datamodel.TagName;
import org.sleuthkit.datamodel.TskCoreException;
import org.sleuthkit.datamodel.TskData;
import org.sleuthkit.datamodel.Volume;
import org.sleuthkit.datamodel.VolumeSystem;

/**
 *
 */
@ServiceProvider(service = GeneralReportModule.class)
public class CreatePortableCaseModule implements GeneralReportModule {
    private static final Logger logger = Logger.getLogger(CreatePortableCaseModule.class.getName());
    private CreatePortableCasePanel configPanel;
    
    private Case currentCase = null;
    private SleuthkitCase skCase = null;
    private File caseFolder = null;
    
    // Maps old object ID from current case to new object ID in portable case
    private Map<Long, Long> newObjIdMap = new HashMap<>();
    
    private long tempObjId = 1;
    
    public CreatePortableCaseModule() {
    }

    @NbBundle.Messages({
        "CreatePortableCaseModule.getName.name=Portable Case"
    })
    @Override
    public String getName() {
        return Bundle.CreatePortableCaseModule_getName_name();
    }

    @NbBundle.Messages({
        "CreatePortableCaseModule.getDescription.description=Copies selected tagged items to a new single-user case that will work anywhere"
    })
    @Override
    public String getDescription() {
        return Bundle.CreatePortableCaseModule_getDescription_description();
    }

    @Override
    public String getRelativeFilePath() {
        return "";
    }
    
    /**
     * Convenience method to avoid code duplication.
     * Assumes that if an exception is supplied then the error is SEVERE. Otherwise
     * it is logged as a WARNING.
     * 
     * @param logWarning
     * @param dialogWarning
     * @param ex
     * @param progressPanel 
     */
    private void handleError(String logWarning, String dialogWarning, Exception ex, ReportProgressPanel progressPanel) {
        if (ex == null) {
            logger.log(Level.WARNING, logWarning); //NON-NLS
        } else {
            logger.log(Level.SEVERE, logWarning, ex);
        }
        MessageNotifyUtil.Message.error(dialogWarning);
        progressPanel.setIndeterminate(false);
        progressPanel.complete(ReportProgressPanel.ReportStatus.ERROR);
    }

    @NbBundle.Messages({
        "CreatePortableCaseModule.generateReport.verifying=Verifying selected parameters...",
        "CreatePortableCaseModule.generateReport.creatingCase=Creating portable case database...",
        "CreatePortableCaseModule.generateReport.copyingTags=Copying tags...",
        "# {0} - tag name",
        "CreatePortableCaseModule.generateReport.copyingFiles=Copying files tagged as {0}...",
        "# {0} - output folder",
        "CreatePortableCaseModule.generateReport.outputDirDoesNotExist=Output folder {0} does not exist",
        "# {0} - output folder",
        "CreatePortableCaseModule.generateReport.outputDirIsNotDir=Output folder {0} is not a folder",
        "CreatePortableCaseModule.generateReport.noTagsSelected=No tags selected for export.",
        "CreatePortableCaseModule.generateReport.caseClosed=Current case has been closed",
        "CreatePortableCaseModule.generateReport.errorCopyingTags=Error copying tags",
        "CreatePortableCaseModule.generateReport.errorCopyingFiles=Error copying tagged files"
    })
    @Override
    public void generateReport(String reportPath, ReportProgressPanel progressPanel) {
        progressPanel.setIndeterminate(true);
        progressPanel.start();
        progressPanel.updateStatusLabel(Bundle.CreatePortableCaseModule_generateReport_verifying());
        
        // TODO TODO cancellation!!!
        
        // Validate the input parameters
        File outputDir = new File(configPanel.getOutputFolder());
        if (! outputDir.exists()) {
            handleError("Output folder " + outputDir.toString() + " does not exist",
                    Bundle.CreatePortableCaseModule_generateReport_outputDirDoesNotExist(outputDir.toString()), null, progressPanel);
            return;
        }
        
        if (! outputDir.isDirectory()) {
            handleError("Output folder " + outputDir.toString() + " is not a folder",
                    Bundle.CreatePortableCaseModule_generateReport_outputDirIsNotDir(outputDir.toString()), null, progressPanel);
            return;
        }
        
        List<TagName> tagNames = configPanel.getSelectedTagNames();
        if (tagNames.isEmpty()) {
            handleError("No tags selected for export",
                    Bundle.CreatePortableCaseModule_generateReport_noTagsSelected(), null, progressPanel);
            return;            
        }
        
        // Save the current case object
        try {
            currentCase = Case.getCurrentCaseThrows();
        } catch (NoCurrentCaseException ex) {
            handleError("Current case has been closed",
                    Bundle.CreatePortableCaseModule_generateReport_caseClosed(), null, progressPanel);
            return;
        } 
        
        
        // Create the case.
        // skCase and caseFolder will be set here.
        progressPanel.updateStatusLabel(Bundle.CreatePortableCaseModule_generateReport_creatingCase());
        createCase(outputDir, progressPanel);
        if (skCase == null) {
            // The error has already been handled
            return;
        }
        
        // Copy the selected tags
        progressPanel.updateStatusLabel(Bundle.CreatePortableCaseModule_generateReport_copyingTags());
        try {
            for(TagName tagName:tagNames) {
                skCase.addOrUpdateTagName(tagName.getDisplayName(), tagName.getDescription(), tagName.getColor(), tagName.getKnownStatus());
            }
        } catch (TskCoreException ex) {
            handleError("Error copying tags", Bundle.CreatePortableCaseModule_generateReport_errorCopyingTags(), ex, progressPanel);
            return;
        }
        
        // Copy the tagged files
        try {
            for(TagName tagName:tagNames) {
                addFilesToPortableCase(tagName, progressPanel);
            }
        } catch (TskCoreException ex) {
            handleError("Error copying tagged files", Bundle.CreatePortableCaseModule_generateReport_errorCopyingFiles(), ex, progressPanel);
            return;
        }        

        // Close the case        
        skCase.close();
        
        progressPanel.complete(ReportProgressPanel.ReportStatus.COMPLETE);
        
    }

    /**
     * Create the case directory and case database. 
     * skCase will be set if this completes without error.
     * 
     * @param outputDir  The parent for the case folder
     * @param progressPanel 
     */
    @NbBundle.Messages({
        "# {0} - case folder",
        "CreatePortableCaseModule.createCase.caseDirExists=Case folder {0} already exists",
        "# {0} - Autopsy file",
        "CreatePortableCaseModule.createCase.errorWritingAutFile=Error writing to file {0}",  
        "CreatePortableCaseModule.createCase.errorCreatingDatabase=Error creating case database",
    })
    private void createCase(File outputDir, ReportProgressPanel progressPanel) {
        
        
        String caseName;

        // Create the case folder
        caseName = currentCase.getDisplayName() + " (Portable)";
        caseFolder = Paths.get(outputDir.toString(), caseName).toFile();

        if (caseFolder.exists()) {
            
            // TEMP TEMP TEMP !!!!!!!!!!!
            // JUST DELETE FOR TESTING
            try {
                FileUtils.deleteDirectory(caseFolder);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            
            //handleError("Case folder " + caseFolder.toString() + " already exists",
            //    Bundle.CreatePortableCaseModule_createCase_caseDirExists(caseFolder.toString()), null, progressPanel);  
            //return;
        }
        caseFolder.mkdirs();
            
        String dbFilePath = Paths.get(caseFolder.toString(), "autopsy.db").toString();

        // Put a fake .aut file in it (TEMP obviously)
        String autFileName = "PortableCaseTest.aut";
        File autFile = Paths.get(caseFolder.toString(), autFileName).toFile();
        try (PrintWriter writer = new PrintWriter(autFile, "UTF-8")) {
                String data =
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                        "<AutopsyCase>" +
                        "  <SchemaVersion>4.0</SchemaVersion>" +
                        "  <CreatedDate>2019/01/08 15:13:03 (EST)</CreatedDate>" +
                        "  <ModifiedDate>2019/01/08 15:13:05 (EST)</ModifiedDate>" +
                        "  <CreatedByAutopsyVersion>4.10.0</CreatedByAutopsyVersion>" +
                        "  <SavedByAutopsyVersion>4.10.0</SavedByAutopsyVersion>" +
                        "  <Case>" +
                        "    <Name>PortableCaseTest1_20190108_151303</Name>" +
                        "    <DisplayName>PortableCaseTest</DisplayName>" +
                        "    <Number/>" +
                        "    <Examiner/>" +
                        "    <ExaminerPhone/>" +
                        "    <ExaminerEmail/>" +
                        "    <CaseNotes/>" +
                        "    <CaseType>Single-user case</CaseType>" +
                        "    <Database/>" +
                        "    <CaseDatabase>autopsy.db</CaseDatabase>" +
                        "    <TextIndex/>" +
                        "  </Case>" +
                        "</AutopsyCase>";
                writer.println(data);    
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            handleError("Error writing to file " + autFile.toString(),
                    Bundle.CreatePortableCaseModule_createCase_errorWritingAutFile(autFile), ex, progressPanel);
            return;
        }
        
        // Create the Sleuthkit case
        try {
            skCase = SleuthkitCase.newCase(dbFilePath);
        } catch (TskCoreException ex) {
            handleError("Error creating case database",
                    Bundle.CreatePortableCaseModule_createCase_errorCreatingDatabase(), ex, progressPanel);
            return;
        }
    }
    
    private void addFilesToPortableCase(TagName tagName, ReportProgressPanel progressPanel) throws TskCoreException {
        List<ContentTag> tags = currentCase.getServices().getTagsManager().getContentTagsByTagName(tagName);
        
        System.out.println("\nFiles tagged with " + tagName.getDisplayName());
        for (ContentTag tag : tags) {
            Content content = tag.getContent();
            if (content instanceof AbstractFile) {
                AbstractFile file = (AbstractFile) content;
                System.out.println("  Want to export file " + content.getName());
                copyContent(file);
            }
        }
    }
    
    /**
     * Returns the object ID for the given content object in the portable case.
     * @param child
     * @return
     * @throws TskCoreException 
     */
    private long copyContent(Content content) throws TskCoreException {
                
        System.out.println("copyContent: " + content.getName() + " " + content.getId());
        if (newObjIdMap.containsKey(content.getId())) {
            System.out.println("  In map - new ID = " + newObjIdMap.get(content.getId()));
            return newObjIdMap.get(content.getId());
        }
        
        // Otherwise:
        // - Make parent of this object (if applicable)
        // - Copy this content
        long parentId = 0;
        if (content.getParent() != null) {
            parentId = copyContent(content.getParent());
        }
        
        if (content instanceof Image) {
            Image image = (Image)content;
            System.out.println("  Creating new image " + image.getName() + " with obj ID " + this.tempObjId);
        } else if (content instanceof VolumeSystem) {
            VolumeSystem vs = (VolumeSystem)content;
            System.out.println("  Creating new vs " + vs.getName() + " with obj ID " + this.tempObjId);
        } else if (content instanceof Volume) {
            Volume vs = (Volume)content;
            System.out.println("  Creating new volume " + vs.getName() + " with obj ID " + this.tempObjId);
        } else if (content instanceof FileSystem) {
            FileSystem fs = (FileSystem)content;
            System.out.println("  Creating new file system " + fs.getName() + " with obj ID " + this.tempObjId);
        } else if (content instanceof AbstractFile) {
            AbstractFile file = (AbstractFile)content;
            
            if (file instanceof LocalFilesDataSource) {
                System.out.println("  Creating new local file data source " + file.getName() + " with obj ID " + this.tempObjId);   
            } else {
                if (file.isDir()) {
                    System.out.println("  Creating new local directory " + file.getName() + " with obj ID " + this.tempObjId
                            + " (" + file.getClass().getName() + ")");   
                } else {
                    System.out.println("  Creating new local file " + file.getName() + " with obj ID " + this.tempObjId 
                            + " (" + file.getClass().getName() + ")");   
                }
            }
            
        } else {
            // Uh oh?
            System.out.println("  Oh no!!! Trying to copy instance of " + content.getClass().getName());
        }
        
        newObjIdMap.put(content.getId(), tempObjId);
        tempObjId++;

        return newObjIdMap.get(content.getId());
    }
    

    @Override
    public JPanel getConfigurationPanel() {
        configPanel = new CreatePortableCasePanel();
        return configPanel;
    }    
}
