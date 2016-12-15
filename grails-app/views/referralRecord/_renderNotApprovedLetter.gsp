<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <style>
    div.footer {
        display: block; text-align: center;
        position: running(footer);
    }
    @page {
        @top-center { content: element(header) }
    }
    @page {
        @bottom-left { content: element(footer) }
    }
    @page {
        size: 8in 11.5in; /* width height */
        margin-bottom: 100px;
    }

    </style>
</head>

<body>

<table width="100%">
    <tr>

        <td>
            <span style="font-size: 14pt; color: #1969ff">Genomic Medicine Multidisciplinary Team</span><br/>
            <span style="font-size: 11pt; color: #1969ff">Wellcome Trust Centre for Human Genetics</span><br/>
            <span style="font-size: 11pt; color: #1969ff">Roosevelt Drive</span><br/>
            <span style="font-size: 11pt; color: #1969ff">Headington</span><br/>
            <span style="font-size: 11pt; color: #1969ff">OX3 7BN</span><br/>
        </td>
        <td><rendering:inlineJpeg bytes="${ouh_nhs}" height="40px"/><br/>&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160; &#160;&#160;<rendering:inlineJpeg bytes="${oxford_uni}" height="40px"/></td>
    </tr>
</table>

<br/>

<br/>

<span style="font-size: 12pt">${form?.clinician?.professionalTitle} ${form?.clinician?.forename} ${form?.clinician?.surname}</span><br/>

<g:each in="${form.coApplicants}" var="coApplicant">
    <span style="font-size: 12pt">${coApplicant?.professionalTitle} ${coApplicant?.forename} ${coApplicant?.surname}</span><br/>
</g:each>

<span style="font-size: 12pt">${form?.clinician?.departmentName}</span><br/>

<g:each in="${form.coApplicants}" var="coApplicant">
    <span style="font-size: 12pt">${coApplicant?.clinician?.departmentName}</span><br/>
</g:each>

<span style="font-size: 12pt">${form?.clinician?.address}</span><br/>
<span style="font-size: 12pt">${form?.clinician?.postcode}</span><br/>

<g:each in="${form.coApplicants}" var="coApplicant">
    <span style="font-size: 12pt">${form?.clinician?.address}</span><br/>
    <span style="font-size: 12pt">${form?.clinician?.postcode}</span><br/>
</g:each>

<p style="font-size: 12pt"> ${new Date().format('dd/MM/yyy')}</p>

<p style="font-size: 12pt"> Dear ${form?.clinician?.professionalTitle} ${form?.clinician?.surname},
    <g:each in="${form.coApplicants}" var="coApplicant">
        <span style="font-size: 12pt"> ${coApplicant?.professionalTitle} ${coApplicant?.surname},</span>
    </g:each>
</p>

<p style="font-size: 12pt; font-weight: bold">Genomic Medicine MDT application: ${form?.uniqueRef}</p>

<p style="font-size: 12pt">I am writing on behalf of the Genomic Medicine MultiDisciplinary Team (GM-MDT) with reference to your above application for genomic sequencing.</p>

<p style="font-size: 12pt">Thank you very much for submitting this application. Your proposal was discussed by the GM-MDT at the meeting held on ${form?.meetingDate} with expert review provided by ${form?.clinician?.professionalTitle} ${coApplicant?.assignedTo?.forename} ${coApplicant?.assignedTo?.surname}.  The discussion raised some uncertainties over the likelihood of the proband’s condition having a genetic basis in view of the adult onset, lack of family history, and plausible non-genetic causes for the phenotype.  Furthermore, the absence of clear pathways or genes reported in association with this combination of features was concluded to make genomic analysis extremely challenging, and without the support of additional cases with the same phenotype therefore unlikely to be productive.  For these reasons whole genome sequencing through the 100,000 Genomes Project is not offered at this stage.  However, the GM-MDT was sympathetic to the lack of alternative routes for investigation and would be happy to reconsider this application should the case advance in such a way that evidence for a genetic cause becomes more compelling.</p>

<p style="font-size: 12pt">If you would like to discuss this further, please contact the GM-MDT coordinator, Jude Craft (Judith.Craft@ouh.nhs.uk), who will arrange appropriate input and assistance.</p>

<p style="font-size: 12pt">Yours sincerely,</p>

<rendering:inlineJpeg bytes="${julian_knight_signature}" height="72px"/>

<p>
    <span style="font-size: 12pt">Professor Julian Knight</span><br/>
    <span style="font-size: 12pt">Chair, GM-MDT</span><br/>
    <span style="font-size: 12pt">Professor of Genomic Medicine and Honorary Consultant Physician</span><br/>
</p>

<div class='footer'>
    <rendering:inlineJpeg bytes="${ox_brc_logos}" height="74px"/>
</div>

</body>
</html>