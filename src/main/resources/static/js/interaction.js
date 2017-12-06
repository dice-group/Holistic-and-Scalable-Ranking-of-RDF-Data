$(document).ready(function() {
	$("#classesSelction").hide();

});

function printclass(classelemenent) {
	var content = "<label> <input type=\"radio\" class=\"option-input radio\" "
			+ "name=\"example\" />" + classelemenent + "</label>"

}

function getClassList() {
	// get the form data using another method
	var answer = document.getElementById("#selectedDataset");
	datasetName = answer[answer.selectedIndex].value

	$.ajax({
		url : "/getClass",// servlet URL that gets
		type : "POST",// request type, can be GET
		data : {
			datasetname : datasetName,// data to be sent
		},
		dataType : "json",// type of data returned
		success : function(data, status, xhr) {
			printDocument(data);
		}
	})
}
};