@var id = id!"chosen-select";
@var name = name!;
@var width = width!"100%";
@var class = class!;
@var onchange = onchange!;
<select class="chosen-select ${class}" name="${name}" id="${id}" onchange="${onchange}">
	${tagBody!}
</select>
<script>
$(function(){
	$("#${id}").chosen({width: "${width}",search_contains: true}); 
});
</script>