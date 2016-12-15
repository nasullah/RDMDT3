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

<p style="font-size: 12pt; font-weight: bold"> Genomic Medicine MDT application: ${form?.uniqueRef}</p>

<p style="font-size: 12pt">Your request for genomic sequencing has been reviewed with recruitment to the 100,000 Genomes Project approved as summarised in the table below:</p>

<table border="0.1" width="100%">

    <tr>
        <th width="25%"><span style="font-weight: normal;font-size: 12pt"> Number of samples</span></th>
        <th width="75%"><span style="font-weight: normal;font-size: 12pt"> ${form?.approvedIdentityOfFamilyMembersSamplesForSeq}</span></th>
    </tr>

    <tr>
        <th width="25%"><span style="font-weight: normal;font-size: 12pt"> Programme</span></th>
        <th width="75%"><span style="font-weight: normal;font-size: 12pt"> ${form?.approvedProgram}</span></th>
    </tr>

    <tr>
        <th width="25%"><span style="font-weight: normal;font-size: 12pt"> Category</span></th>
        <th width="75%"><span style="font-weight: normal;font-size: 12pt"> ${form?.approvedTargetCategory?.diseaseGroup} : ${form?.approvedTargetCategory?.diseaseName}</span></th>
    </tr>

    <tr>
        <th width="25%"><span style="font-weight: normal;font-size: 12pt"> Further notes</span></th>
        <th width="75%"><span style="font-weight: normal;font-size: 12pt"> ${form?.approvalDetails}</span></th>
    </tr>

</table>

<p style="font-size: 12pt">Unless you prefer to recruit this family yourself, the recruitment pack (containing all consent materials and blood tubes) will be sent to the Clinical Genetics Genetic Counselling team who will make an appointment to recruit this family.  Please advise if you would prefer this to be directed to you.</p>

<p style="font-size: 12pt">If you would like to discuss this further, please contact the GM-MDT coordinator, Jude Craft (Judith.Craft@ouh.nhs.uk), who will arrange appropriate input and assistance. </p>

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