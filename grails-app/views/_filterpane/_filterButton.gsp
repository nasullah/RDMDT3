<a href="javascript:void(0);"
   onclick="grailsFilterPane.toggleElement('${filterPaneId}');
   return false;"
   class="${styleClass} btn btn-info"
   onmouseup="updateHiddenFields()"
   style="${style}"><i class="glyphicon glyphicon-filter"></i> ${text}</a>

<g:javascript plugin="jquery" library="jquery" />
<script>

function updateHiddenFields(){
   var month = $("#month").val();
   var year = $("#year").val();
   $("#selectedMonth").val(month);
   $("#selectedYear").val(year);
}

</script>