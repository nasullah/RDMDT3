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
<span style="font-size: 12pt">${form?.clinician?.departmentName}</span><br/>
<span style="font-size: 12pt">${form?.clinician?.address}</span><br/>
<span style="font-size: 12pt">${form?.clinician?.postcode}</span><br/>

<br/>

<g:each in="${form.coApplicants}" var="coApplicant">
    <span style="font-size: 12pt">Co-applicant: ${coApplicant?.coApplicant?.professionalTitle} ${coApplicant?.coApplicant?.forename} ${coApplicant?.coApplicant?.surname}</span><br/>
</g:each>

<p style="font-size: 12pt"> ${new Date().format('dd/MM/yyy')}</p>

<p style="font-size: 12pt"> Dear ${form?.clinician?.professionalTitle} ${form?.clinician?.surname},
    <g:each in="${form.coApplicants}" var="coApplicant">
        <span style="font-size: 12pt"> ${coApplicant?.coApplicant?.professionalTitle} ${coApplicant?.coApplicant?.surname},</span>
    </g:each>
</p>

<p style="font-size: 12pt; font-weight: bold"> Genomic Medicine MDT application: ${form?.uniqueRef}</p>

<p style="font-size: 12pt">Your request for genomic sequencing was reviewed at the Genomic Medicine MDT held on August 9th, with recruitment to the HICF Whole Genome Sequencing Project approved as summarised in the table below:</p>

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
        <th width="25%"><span style="font-weight: normal;font-size: 12pt"> Further notes</span></th>
        <th width="75%"><span style="font-weight: normal;font-size: 12pt"> ${form?.note}</span></th>
    </tr>

</table>

<p style="font-size: 12pt">Samples (blood or a minimum of 3µg DNA) should be submitted to Dr John Taylor at the Regional Genetics Laboratory, Churchill Hospital within 3 months of the date of this letter. For inclusion in this programme, specific written consent for whole genome analysis is a requirement and the project’s own consent forms (MGAC) are available for your use if needed.</p>

<p style="font-size: 12pt">If you would like to discuss this further, please contact the GM-MDT coordinator, Jude Craft (Judith.Craft@ouh.nhs.uk), who will arrange appropriate input and assistance.</p>

<p style="font-size: 12pt">Yours sincerely,</p>

<rendering:inlineJpeg bytes="${julian_knight_signature}" height="72px"/>

<p>
    <span style="font-size: 12pt">Professor Julian Knight</span><br/>
    <span style="font-size: 12pt">Chair, GM-MDT</span><br/>
    <span style="font-size: 12pt">Professor of Genomic Medicine and Honorary Consultant Physician</span><br/>
</p>

<p>
    <span style="font-size: 12pt">cc. Prof. Jenny Taylor,</span><br/>
    <span style="font-size: 12pt">Wellcome Trust Centre for Human Genetics</span><br/>
    <span style="font-size: 12pt">Oxford University</span><br/>
</p>

<div class='footer'>
    <rendering:inlineJpeg bytes="${ox_brc_logos}" height="74px"/>
</div>

</body>
</html>